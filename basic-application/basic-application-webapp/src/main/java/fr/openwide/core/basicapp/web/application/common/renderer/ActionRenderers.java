package fr.openwide.core.basicapp.web.application.common.renderer;

import fr.openwide.core.wicket.more.markup.html.bootstrap.label.model.BootstrapColor;
import fr.openwide.core.wicket.more.markup.html.bootstrap.label.model.IBootstrapColor;
import fr.openwide.core.wicket.more.markup.html.bootstrap.label.renderer.BootstrapRenderer;

public final class ActionRenderers {

	public static <T> BootstrapRenderer<T> constant(final String resourceKey, final String icon, final IBootstrapColor color) {
		return constant(resourceKey, resourceKey, icon, color);
	}

	public static <T> BootstrapRenderer<T> constant(final String labelResourceKey, final String tooltipResourceKey, final String icon, final IBootstrapColor color) {
		return BootstrapRenderer.constant(labelResourceKey, tooltipResourceKey, icon, color);
	}

	public static <T> BootstrapRenderer<T> add() {
		return constant("common.action.add", "fa fa-fw fa-plus", BootstrapColor.PRIMARY);
	}

	public static <T> BootstrapRenderer<T> remove() {
		return constant("common.action.remove", "fa fa-fw fa-times", BootstrapColor.DANGER);
	}

	public static <T> BootstrapRenderer<T> delete() {
		return constant("common.action.delete", "fa fa-fw fa-trash-o", BootstrapColor.DANGER);
	}

	public static <T> BootstrapRenderer<T> enable() {
		return constant("common.action.enable", "fa fa-fw fa-toggle-on", BootstrapColor.SUCCESS);
	}

	public static <T> BootstrapRenderer<T> disable() {
		return constant("common.action.disable", "fa fa-fw fa-toggle-off", BootstrapColor.DANGER);
	}

	public static <T> BootstrapRenderer<T> view() {
		return constant("common.action.view", "fa fa-fw fa-search", BootstrapColor.PRIMARY);
	}

}
