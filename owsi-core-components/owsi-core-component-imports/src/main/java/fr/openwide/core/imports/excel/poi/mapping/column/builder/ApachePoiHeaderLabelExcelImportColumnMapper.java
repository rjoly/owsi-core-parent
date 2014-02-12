package fr.openwide.core.imports.excel.poi.mapping.column.builder;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;

import fr.openwide.core.commons.util.functional.SerializableFunction;
import fr.openwide.core.imports.excel.event.IExcelImportEventHandler;
import fr.openwide.core.imports.excel.event.exception.ExcelImportHeaderLabelMappingException;
import fr.openwide.core.imports.excel.location.IExcelImportNavigator;
import fr.openwide.core.imports.excel.mapping.column.builder.IExcelImportColumnMapper;

/*package*/ class ApachePoiHeaderLabelExcelImportColumnMapper implements IExcelImportColumnMapper<Sheet, Row, Cell> {
	
	private final String expectedHeaderLabel;
	
	private final Predicate<? super String> predicate;

	private final int indexAmongMatchedColumns;
	
	private final boolean optional;

	/**
	 * @param indexAmongMatchedColumns The 0-based index of this column among the columns matching the given <code>predicate</code>.
	 */
	public ApachePoiHeaderLabelExcelImportColumnMapper(String expectedHeaderLabel, Predicate<? super String> predicate, int indexAmongMatchedColumns, boolean optional) {
		super();
		Validate.notNull(predicate, "predicate must not be null");
		
		this.expectedHeaderLabel = expectedHeaderLabel;
		this.predicate = predicate;
		this.indexAmongMatchedColumns = indexAmongMatchedColumns;
		this.optional = optional;
	}
	
	@Override
	public Function<? super Row, Cell> map(Sheet sheet, IExcelImportNavigator<Sheet, Row, Cell> navigator, IExcelImportEventHandler eventHandler) throws ExcelImportHeaderLabelMappingException {
		int matchedColumnsCount = 0;
		Row headersRow = sheet.getRow(sheet.getFirstRowNum());
		
		if (headersRow != null) {
			Iterator<Cell> iterator = headersRow.cellIterator();
			
			while (iterator.hasNext()) {
				Cell cell = iterator.next();
				String cellValue = StringUtils.trimToNull(cell.getStringCellValue());
				if (predicate.apply(cellValue)) {
					if (matchedColumnsCount == indexAmongMatchedColumns) {
						final int index = cell.getColumnIndex();
						return new SerializableFunction<Row, Cell>() {
							private static final long serialVersionUID = 1L;
							@Override
							public Cell apply(Row row) {
								return row == null ? null : row.getCell(index);
							}
						};
					} else {
						++matchedColumnsCount;
					}
				}
			}
		}
		
		if (!optional) {
			eventHandler.headerLabelMappingError(expectedHeaderLabel, indexAmongMatchedColumns, navigator.getLocation(sheet, headersRow, null));
		}
		
		return Functions.constant(null);
	}
}
