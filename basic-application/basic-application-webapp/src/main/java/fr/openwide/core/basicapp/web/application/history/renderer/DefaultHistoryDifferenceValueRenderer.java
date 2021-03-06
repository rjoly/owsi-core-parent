package fr.openwide.core.basicapp.web.application.history.renderer;

import java.util.Locale;
import java.util.Map;

import org.apache.wicket.model.IModel;

import com.google.common.base.Function;
import com.google.common.base.Optional;

import fr.openwide.core.basicapp.core.business.history.model.HistoryDifference;
import fr.openwide.core.basicapp.core.util.binding.Bindings;
import fr.openwide.core.commons.util.fieldpath.FieldPath;
import fr.openwide.core.jpa.more.business.history.model.embeddable.HistoryValue;
import fr.openwide.core.wicket.more.rendering.Renderer;
import fr.openwide.core.wicket.more.util.model.Models;
import fr.openwide.core.wicket.more.util.model.Models.MapModelBuilder;

public final class DefaultHistoryDifferenceValueRenderer extends AbstractHistoryRenderer<HistoryDifference> {
	private static final long serialVersionUID = 1L;
	
	private static final Renderer<HistoryDifference> BEFORE = new DefaultHistoryDifferenceValueRenderer(
			Bindings.historyDifference().before());
	
	private static final Renderer<HistoryDifference> AFTER = new DefaultHistoryDifferenceValueRenderer(
			Bindings.historyDifference().after());
	
	private static final String DEFAULT_KEY = "history.difference.common.default.value";
	
	public static Renderer<HistoryDifference> before() {
		return BEFORE;
	}
	
	public static Renderer<HistoryDifference> after() {
		return AFTER;
	}
	
	private final Renderer<HistoryDifference> valueRenderer;
	
	private DefaultHistoryDifferenceValueRenderer(Function<HistoryDifference, HistoryValue> valueFunction) {
		this.valueRenderer = HistoryValueRenderer.get().onResultOf(valueFunction).orBlank();
	}

	@Override
	public String render(HistoryDifference difference, Locale locale) {
		FieldPath path = difference.getAbsolutePath();
		String pathResourceKeyPart = getFieldPathKeyPart(path);
		IModel<?> dataModel = Models.dataMap()
				.put("current", valueRenderer.asModel(Models.transientModel(difference)))
				.put("children", createChildrenMap(difference))
				.build();
		String entityResourceKeyPart = getEntityResourceKeyPart(difference);
		
		Optional<String> result = getStringOptional(
				JOINER.join(HISTORY_DIFFERENCE_ROOT, entityResourceKeyPart, pathResourceKeyPart, ".value"),
				locale, dataModel
		);
		if (!result.isPresent()) {
			result = getStringOptional(JOINER.join(HISTORY_DIFFERENCE_ROOT, ".common", pathResourceKeyPart, ".value"), locale, dataModel);
		}
		
		if (result.isPresent()) {
			return result.get();
		} else {
			return getString(DEFAULT_KEY, locale, dataModel);
		}
	}

	private IModel<Map<String, Object>> createChildrenMap(HistoryDifference difference) {
		MapModelBuilder<String, Object> builder = Models.dataMap();
		for (HistoryDifference child : difference.getDifferences()) {
			FieldPath path = child.getRelativePath();
			if (!path.isItem()) {
				builder = builder.put(path.toString().replace(".", ""), valueRenderer.asModel(Models.transientModel(child)));
			}
		}
		return builder.build();
	}
}