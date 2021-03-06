package fr.openwide.core.test.jpa.security.business.person.service;

import java.util.List;

import fr.openwide.core.jpa.exception.SecurityServiceException;
import fr.openwide.core.jpa.exception.ServiceException;
import fr.openwide.core.jpa.security.business.person.service.IGenericUserGroupService;
import fr.openwide.core.test.jpa.security.business.person.model.MockUser;
import fr.openwide.core.test.jpa.security.business.person.model.MockUserGroup;

public interface IMockUserGroupService extends IGenericUserGroupService<MockUserGroup, MockUser> {

	List<MockUser> listUsersByUserGroup(MockUserGroup group) throws ServiceException, SecurityServiceException;

}
