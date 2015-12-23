package fr.openwide.core.wicket.more.markup.html.repeater.data.table.builder.action.builder;

import org.apache.wicket.model.IModel;

import fr.openwide.core.jpa.more.business.sort.ISort;
import fr.openwide.core.wicket.more.markup.html.action.IAjaxOneParameterAjaxAction;
import fr.openwide.core.wicket.more.markup.html.bootstrap.label.renderer.BootstrapLabelRenderer;
import fr.openwide.core.wicket.more.markup.html.factory.IOneParameterComponentFactory;
import fr.openwide.core.wicket.more.markup.html.factory.IOneParameterModelFactory;
import fr.openwide.core.wicket.more.markup.html.repeater.data.table.builder.action.builder.fluid.IActionColumnConfirmActionBuilderStepContent;
import fr.openwide.core.wicket.more.markup.html.repeater.data.table.builder.action.builder.fluid.IActionColumnConfirmActionBuilderStepEndContent;
import fr.openwide.core.wicket.more.markup.html.repeater.data.table.builder.action.builder.fluid.IActionColumnConfirmActionBuilderStepNo;
import fr.openwide.core.wicket.more.markup.html.repeater.data.table.builder.action.builder.fluid.IActionColumnConfirmActionBuilderStepOnclick;
import fr.openwide.core.wicket.more.markup.html.repeater.data.table.builder.action.builder.fluid.IActionColumnConfirmActionBuilderStepStart;
import fr.openwide.core.wicket.more.markup.html.repeater.data.table.builder.action.state.IActionColumnAddedConfirmActionState;
import fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.bootstrap.confirm.component.AjaxConfirmLink;
import fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.bootstrap.confirm.component.AjaxConfirmLinkBuilder;

public class ActionColumnConfirmActionBuilder<T, S extends ISort<?>> implements
		IActionColumnConfirmActionBuilderStepStart<T, S>, IActionColumnConfirmActionBuilderStepContent<T, S>,
		IActionColumnConfirmActionBuilderStepEndContent<T, S>, IActionColumnConfirmActionBuilderStepNo<T, S>,
		IActionColumnConfirmActionBuilderStepOnclick<T, S> {

	private final ActionColumnBuilder<T, S> actionColumnBuilder;

	private final BootstrapLabelRenderer<? super T> renderer;

	private AjaxConfirmLinkBuilder<T> ajaxConfirmLinkBuilder;

	public ActionColumnConfirmActionBuilder(ActionColumnBuilder<T, S> actionColumnBuilder, BootstrapLabelRenderer<? super T> renderer) {
		this.actionColumnBuilder = actionColumnBuilder;
		this.renderer = renderer;
		ajaxConfirmLinkBuilder = (AjaxConfirmLinkBuilder<T>) AjaxConfirmLink.<T>build();
	}

	@Override
	public IActionColumnConfirmActionBuilderStepContent<T, S> title(IModel<String> titleModel) {
		ajaxConfirmLinkBuilder.title(titleModel);
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepContent<T, S> title(IOneParameterModelFactory<IModel<T>, String> titleModelFactory) {
		ajaxConfirmLinkBuilder.title(titleModelFactory);
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepOnclick<T, S> deleteConfirmation() {
		ajaxConfirmLinkBuilder.deleteConfirmation();
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepEndContent<T, S> content(IModel<String> contentModel) {
		ajaxConfirmLinkBuilder.content(contentModel);
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepEndContent<T, S> content(IOneParameterModelFactory<IModel<T>, String> contentModelFactory) {
		ajaxConfirmLinkBuilder.content(contentModelFactory);
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepEndContent<T, S> keepMarkup() {
		ajaxConfirmLinkBuilder.keepMarkup();
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepEndContent<T, S> cssClassNamesModel(IModel<String> cssClassNamesModel) {
		ajaxConfirmLinkBuilder.cssClassNamesModel(cssClassNamesModel);
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepNo<T, S> yes(IModel<String> yesLabelModel) {
		ajaxConfirmLinkBuilder.yes(yesLabelModel);
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepOnclick<T, S> yesNo() {
		ajaxConfirmLinkBuilder.yesNo();
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepOnclick<T, S> confirm() {
		ajaxConfirmLinkBuilder.confirm();
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepOnclick<T, S> validate() {
		ajaxConfirmLinkBuilder.validate();
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepOnclick<T, S> save() {
		ajaxConfirmLinkBuilder.save();
		return this;
	}

	@Override
	public IActionColumnConfirmActionBuilderStepOnclick<T, S> no(IModel<String> noLabelModel) {
		ajaxConfirmLinkBuilder.no(noLabelModel);
		return this;
	}

	@Override
	public IActionColumnAddedConfirmActionState<T, S> onClick(IAjaxOneParameterAjaxAction<IModel<T>> onClick) {
		ajaxConfirmLinkBuilder.onClick(onClick);
		IOneParameterComponentFactory<AjaxConfirmLink<T>, IModel<T>> factory = ajaxConfirmLinkBuilder.factory();
		ajaxConfirmLinkBuilder = null;
		return actionColumnBuilder.addConfirmAction(renderer, factory);
	}

}
