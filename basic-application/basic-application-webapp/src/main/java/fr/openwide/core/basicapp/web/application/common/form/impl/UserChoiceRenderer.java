package fr.openwide.core.basicapp.web.application.common.form.impl;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import fr.openwide.core.basicapp.core.business.user.model.User;
import fr.openwide.core.basicapp.web.application.common.renderer.UserRenderer;
import fr.openwide.core.wicket.more.rendering.GenericEntityIdStringRenderer;

public final class UserChoiceRenderer extends ChoiceRenderer<User> {
	private static final long serialVersionUID = 1L;
	
	private static final UserChoiceRenderer INSTANCE = new UserChoiceRenderer();
	public static UserChoiceRenderer get() {
		return INSTANCE;
	}
	
	private UserChoiceRenderer() {
	}
	
	@Override
	public Object getDisplayValue(User user) {
		return user != null ? UserRenderer.get().render(user, null) : "";
	}
	
	@Override
	public String getIdValue(User user, int index) {
		return GenericEntityIdStringRenderer.get().render(user);
	}
}