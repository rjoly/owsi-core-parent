package fr.openwide.core.basicapp.web.application.administration.model;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.ImmutableMap;

import fr.openwide.core.basicapp.core.business.user.model.User;
import fr.openwide.core.basicapp.core.business.user.model.UserGroup;
import fr.openwide.core.basicapp.core.business.user.search.IUserGroupSearchQuery;
import fr.openwide.core.basicapp.core.business.user.search.UserGroupSort;
import fr.openwide.core.jpa.more.business.search.query.ISearchQuery;
import fr.openwide.core.wicket.more.markup.html.sort.model.CompositeSortModel;
import fr.openwide.core.wicket.more.markup.html.sort.model.CompositeSortModel.CompositingStrategy;
import fr.openwide.core.wicket.more.model.AbstractSearchQueryDataProvider;
import fr.openwide.core.wicket.more.model.GenericEntityModel;

public class UserGroupDataProvider extends AbstractSearchQueryDataProvider<UserGroup, UserGroupSort> {

	private static final long serialVersionUID = 7805366114079528005L;

	private final IModel<User> userModel;

	private final IModel<String> nameModel = new Model<String>();

	private final CompositeSortModel<UserGroupSort> sortModel = new CompositeSortModel<UserGroupSort>(
			CompositingStrategy.LAST_ONLY,
			ImmutableMap.of(
					UserGroupSort.NAME, UserGroupSort.NAME.getDefaultOrder(),
					UserGroupSort.ID, UserGroupSort.ID.getDefaultOrder()
			),
			ImmutableMap.of(
					UserGroupSort.ID, UserGroupSort.ID.getDefaultOrder()
			)
	);

	public UserGroupDataProvider(IModel<User> userModel) {
		super();
		this.userModel = userModel;
	}

	public IModel<String> getNameModel() {
		return nameModel;
	}
	
	@Override
	public IModel<UserGroup> model(UserGroup userGroup) {
		return GenericEntityModel.of(userGroup);
	}

	public CompositeSortModel<UserGroupSort> getSortModel() {
		return sortModel;
	}

	@Override
	protected ISearchQuery<UserGroup, UserGroupSort> getSearchQuery() {
		return createSearchQuery(IUserGroupSearchQuery.class)
				.user(userModel.getObject())
				.name(nameModel.getObject())
				.sort(sortModel.getObject());
	}

	@Override
	public void detach() {
		super.detach();
		userModel.detach();
		nameModel.detach();
		sortModel.detach();
	}

}
