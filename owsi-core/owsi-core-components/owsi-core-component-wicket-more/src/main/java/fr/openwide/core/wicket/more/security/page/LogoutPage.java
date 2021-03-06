package fr.openwide.core.wicket.more.security.page;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;

import fr.openwide.core.wicket.more.application.CoreWicketAuthenticatedApplication;
import fr.openwide.core.wicket.more.link.descriptor.IPageLinkDescriptor;
import fr.openwide.core.wicket.more.markup.html.CoreWebPage;


public class LogoutPage extends CoreWebPage {
	
	private static final long serialVersionUID = -1336719504268894384L;
	
	public LogoutPage() {
		this(CoreWicketAuthenticatedApplication.get().getSignInPageLinkDescriptor());
	}

	protected LogoutPage(IPageLinkDescriptor signInPageLinkDescriptor) {
		if(AuthenticatedWebSession.exists()) {
			AuthenticatedWebSession.get().invalidate();
		}
		
		throw signInPageLinkDescriptor.newRestartResponseException();
	}

}
