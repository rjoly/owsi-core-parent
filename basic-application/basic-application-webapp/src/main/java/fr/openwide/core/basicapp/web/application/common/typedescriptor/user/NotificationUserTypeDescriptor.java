package fr.openwide.core.basicapp.web.application.common.typedescriptor.user;

import fr.openwide.core.basicapp.core.business.user.model.User;
import fr.openwide.core.basicapp.web.application.common.typedescriptor.AbstractGenericEntityChildTypeDescriptor;
import fr.openwide.core.basicapp.web.application.common.util.ResourceKeyGenerator;

public abstract class NotificationUserTypeDescriptor<U extends User> extends
		AbstractGenericEntityChildTypeDescriptor<UserTypeDescriptor<U>, U> {

	private static final long serialVersionUID = -349656773642244352L;

	public static final NotificationUserTypeDescriptor<? extends User> USER = new NotificationUserTypeDescriptor<User>(UserTypeDescriptor.USER) {
		private static final long serialVersionUID = 1L;
		
		@Override
		protected Object readResolve() {
			return USER;
		}
	};

	private NotificationUserTypeDescriptor(UserTypeDescriptor<U> typeDescriptor) {
		super(typeDescriptor);
	}
	
	public ResourceKeyGenerator resourceKeyGenerator() {
		return typeDescriptor.resourceKeyGenerator().withPrefix("notification.panel");
	}
}
