package fr.openwide.core.basicapp.core.business.user.dao;

import fr.openwide.core.basicapp.core.business.user.model.User;
import fr.openwide.core.basicapp.core.business.user.model.UserGroup;
import fr.openwide.core.jpa.security.business.person.dao.IGenericUserGroupDao;

public interface IUserGroupDao extends IGenericUserGroupDao<UserGroup, User> {

}
