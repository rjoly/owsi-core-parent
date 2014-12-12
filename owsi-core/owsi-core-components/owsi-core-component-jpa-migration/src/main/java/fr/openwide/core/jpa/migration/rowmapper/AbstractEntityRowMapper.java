package fr.openwide.core.jpa.migration.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.openwide.core.jpa.migration.monitor.ProcessorMonitorContext;

public abstract class AbstractEntityRowMapper<T> extends AbstractRowMapper<RowResult> {

	private final T results;

	protected AbstractEntityRowMapper(T results) {
		this.results = results;
	}

	@Override
	public final RowResult mapRow(ResultSet rs, int rowNum) throws SQLException {
		RowResult result = doMapRow(rs, rowNum);
		switch (result) {
		case DONE:
			ProcessorMonitorContext.get().getDoneItems().incrementAndGet();
			break;
		case DONE_WITH_ERROR:
			ProcessorMonitorContext.get().getFailedItems().incrementAndGet();
			break;
		case FAILED:
			ProcessorMonitorContext.get().getFailedItems().incrementAndGet();
			break;
		default:
			break;
		}
		return result;
	}

	public T getResults() {
		return results;
	}

	protected abstract RowResult doMapRow(ResultSet rs, int rowNum) throws SQLException;

}
