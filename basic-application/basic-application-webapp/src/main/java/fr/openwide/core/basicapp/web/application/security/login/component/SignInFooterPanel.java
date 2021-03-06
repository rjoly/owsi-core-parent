package fr.openwide.core.basicapp.web.application.security.login.component;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import fr.openwide.core.basicapp.core.business.user.model.User;
import fr.openwide.core.basicapp.core.security.service.ISecurityManagementService;
import fr.openwide.core.basicapp.web.application.common.typedescriptor.user.UserTypeDescriptor;

public class SignInFooterPanel<U extends User> extends Panel {

	private static final long serialVersionUID = -7042210777928535702L;
	
	@SpringBean
	private ISecurityManagementService securityManagementService;
	
	public SignInFooterPanel(String wicketId, UserTypeDescriptor<U> typeDescriptor) {
		super(wicketId);
		
		boolean passwordRecoveryEnabled = securityManagementService.getOptions(typeDescriptor.getEntityClass())
				.isPasswordUserRecoveryEnabled();
		
		add(
				typeDescriptor.securityTypeDescriptor().passwordRecoveryPageLinkDescriptor().link("passwordRecovery")
						.setVisibilityAllowed(passwordRecoveryEnabled)
		);
	}

}
