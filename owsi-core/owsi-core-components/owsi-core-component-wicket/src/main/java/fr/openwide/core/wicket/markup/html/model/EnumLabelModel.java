package fr.openwide.core.wicket.markup.html.model;

import org.apache.wicket.Component;
import org.apache.wicket.model.ComponentModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Classes;

/**
 * Give translation for a given enum. Resource key is built as in EnumLabel.
 * 
 * This model needs to be wrapped by component to be used.
 * 
 * @deprecated Use fr.openwide.core.wicket.more.rendering.EnumRenderer instead.<ul>
 * <li>{@code new EnumLabelModel(enumValueModel)} should become {@code EnumRenderer.get().asModel(enumValueModel)}
 * <li>{@code new EnumLabelModel(enumValueModel, nullKeyValue)} should become
 * 	{@code EnumRenderer.get().nullsAsResourceKey(nullKeyValue).asModel(enumValueModel)}
 * <li>{@code new EnumLabelModel(enumValue)} should become {@code EnumRenderer.get().asModel(new Model<>(enumValue))}
 * <li>{@code new EnumLabelModel(enumValue, nullKeyValue)} should become
 * 	{@code EnumRenderer.get().nullsAsResourceKey(nullKeyValue).asModel(new Model<>(enumValue))}
 */
@Deprecated
public class EnumLabelModel<E extends Enum<?>> extends ComponentModel<String> {

	private static final long serialVersionUID = -7290192562513833670L;

	private IModel<E> enumValueModel;

	private IModel<String> nullKeyModel;

	public EnumLabelModel(E enumValue) {
		this(Model.of(enumValue), null);
	}

	public EnumLabelModel(E enumValue, String nullKeyValue) {
		this(Model.of(enumValue), Model.of(nullKeyValue));
	}

	public EnumLabelModel(IModel<E> enumValueModel) {
		this(enumValueModel, null);
	}

	public EnumLabelModel(IModel<E> enumValueModel, IModel<String> nullKeyModel) {
		super();
		this.enumValueModel = enumValueModel;
		this.nullKeyModel = nullKeyModel;
	}
	
	@Override
	protected String getObject(Component component) {
		if (enumValueModel != null && enumValueModel.getObject() != null) {
			return component.getString(Classes.simpleName(enumValueModel.getObject().getClass()) + "." + enumValueModel.getObject().name());
		} else if (nullKeyModel != null && nullKeyModel.getObject() != null) {
			return component.getString(nullKeyModel.getObject());
		} else {
			return "";
		}
	}

}
