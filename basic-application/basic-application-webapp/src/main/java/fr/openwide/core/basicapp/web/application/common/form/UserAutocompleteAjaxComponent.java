package fr.openwide.core.basicapp.web.application.common.form;

import java.util.List;

import org.apache.wicket.model.IModel;

import fr.openwide.core.basicapp.core.business.user.model.User;
import fr.openwide.core.basicapp.core.business.user.search.IUserSearchQuery;
import fr.openwide.core.basicapp.web.application.common.form.impl.UserChoiceRenderer;
import fr.openwide.core.wicket.more.markup.html.form.AutocompleteAjaxComponent;

public class UserAutocompleteAjaxComponent extends AutocompleteAjaxComponent<User> {

	private static final long serialVersionUID = -7717935272455937918L;

	public UserAutocompleteAjaxComponent(String id, IModel<User> userModel) {
		super(id, userModel);
		setChoiceRenderer(UserChoiceRenderer.get());
	}

	@Override
	public List<User> getValues(String term) {
		return getBean(IUserSearchQuery.class).nameAutocomplete(term).fullList();
	}

	@Override
	public User getValueOnSearchFail(String input) {
		return null;
	}
}
