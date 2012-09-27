package fr.openwide.core.basicapp.web.application;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;

import fr.openwide.core.basicapp.web.application.administration.page.AdministrationUserDescriptionPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationUserGroupDescriptionPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationUserGroupPortfolioPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationUserPortfolioPage;
import fr.openwide.core.basicapp.web.application.common.template.MainTemplate;
import fr.openwide.core.basicapp.web.application.navigation.page.HomePage;
import fr.openwide.core.basicapp.web.application.navigation.page.SignInPage;
import fr.openwide.core.basicapp.web.application.navigation.util.LinkUtils;
import fr.openwide.core.wicket.more.application.CoreWicketAuthenticatedApplication;
import fr.openwide.core.wicket.more.console.template.ConsoleConfiguration;
import fr.openwide.core.wicket.more.markup.html.pages.monitoring.DatabaseMonitoringPage;
import fr.openwide.core.wicket.more.security.page.LoginFailurePage;
import fr.openwide.core.wicket.more.security.page.LoginSuccessPage;

public class BasicApplicationApplication extends CoreWicketAuthenticatedApplication {

	@Override
	protected void mountApplicationPages() {
		
		// Sign in
		mountPage("/login/", getSignInPageClass());
		mountPage("/login/failure/", LoginFailurePage.class);
		mountPage("/login/success/", LoginSuccessPage.class);
		
		// Navigation
		mountPage("/home/", HomePage.class);
		
		// Administration
		mountPage("/administration/user/", AdministrationUserPortfolioPage.class);
		mountPage("/administration/user/${" + LinkUtils.ID_PARAMETER + "}/", AdministrationUserDescriptionPage.class);
		mountPage("/administration/user-group/", AdministrationUserGroupPortfolioPage.class);
		mountPage("/administration/user-group/${" + LinkUtils.ID_PARAMETER + "}/", AdministrationUserGroupDescriptionPage.class);
		
		// Console
		ConsoleConfiguration consoleConfiguration = ConsoleConfiguration.build("console");
		consoleConfiguration.mountPages(this);
		
		// Monitoring
		mountPage("/monitoring/db-access/", DatabaseMonitoringPage.class);
	}

	@Override
	protected void mountApplicationResources() {
		mountStaticResourceDirectory("/application", MainTemplate.class);
	}

	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return BasicApplicationSession.class;
	}

	@Override
	public Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}
}
