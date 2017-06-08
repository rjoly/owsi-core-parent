package fr.openwide.core.basicapp.web.application;

import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.resource.loader.ClassStringResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableList;

import fr.openwide.core.basicapp.core.business.common.model.PostalCode;
import fr.openwide.core.basicapp.core.business.history.model.atomic.HistoryEventType;
import fr.openwide.core.basicapp.core.business.user.model.BasicUser;
import fr.openwide.core.basicapp.core.business.user.model.TechnicalUser;
import fr.openwide.core.basicapp.core.business.user.model.User;
import fr.openwide.core.basicapp.core.business.user.model.UserGroup;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationBasicUserDescriptionPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationBasicUserPortfolioPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationTechnicalUserDescriptionPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationTechnicalUserPortfolioPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationUserGroupDescriptionPage;
import fr.openwide.core.basicapp.web.application.administration.page.AdministrationUserGroupPortfolioPage;
import fr.openwide.core.basicapp.web.application.common.converter.PostalCodeConverter;
import fr.openwide.core.basicapp.web.application.common.renderer.AuthorityRenderer;
import fr.openwide.core.basicapp.web.application.common.renderer.UserGroupRenderer;
import fr.openwide.core.basicapp.web.application.common.renderer.UserRenderer;
import fr.openwide.core.basicapp.web.application.common.template.MainTemplate;
import fr.openwide.core.basicapp.web.application.common.template.styles.StylesLessCssResourceReference;
import fr.openwide.core.basicapp.web.application.common.template.styles.application_access.ApplicationAccessLessCssResourceReference;
import fr.openwide.core.basicapp.web.application.common.template.styles.notification.NotificationLessCssResourceReference;
import fr.openwide.core.basicapp.web.application.console.notification.demo.page.ConsoleNotificationDemoIndexPage;
import fr.openwide.core.basicapp.web.application.history.renderer.HistoryValueRenderer;
import fr.openwide.core.basicapp.web.application.navigation.page.HomePage;
import fr.openwide.core.basicapp.web.application.navigation.page.MaintenancePage;
import fr.openwide.core.basicapp.web.application.profile.page.ProfilePage;
import fr.openwide.core.basicapp.web.application.referencedata.page.ReferenceDataPage;
import fr.openwide.core.basicapp.web.application.resources.application.BasicApplicationApplicationResources;
import fr.openwide.core.basicapp.web.application.resources.business.BasicApplicationBusinessResources;
import fr.openwide.core.basicapp.web.application.resources.common.BasicApplicationCommonResources;
import fr.openwide.core.basicapp.web.application.resources.console.BasicApplicationConsoleResources;
import fr.openwide.core.basicapp.web.application.resources.enums.BasicApplicationEnumResources;
import fr.openwide.core.basicapp.web.application.resources.navigation.BasicApplicationNavigationResources;
import fr.openwide.core.basicapp.web.application.resources.notifications.BasicApplicationNotificationResources;
import fr.openwide.core.basicapp.web.application.security.login.page.SignInPage;
import fr.openwide.core.basicapp.web.application.security.password.page.SecurityPasswordCreationPage;
import fr.openwide.core.basicapp.web.application.security.password.page.SecurityPasswordExpirationPage;
import fr.openwide.core.basicapp.web.application.security.password.page.SecurityPasswordRecoveryPage;
import fr.openwide.core.basicapp.web.application.security.password.page.SecurityPasswordResetPage;
import fr.openwide.core.infinispan.service.IInfinispanClusterService;
import fr.openwide.core.jpa.more.business.history.model.embeddable.HistoryValue;
import fr.openwide.core.jpa.security.business.authority.model.Authority;
import fr.openwide.core.spring.property.service.IPropertyService;
import fr.openwide.core.wicket.more.application.CoreWicketAuthenticatedApplication;
import fr.openwide.core.wicket.more.console.common.model.ConsoleMenuSection;
import fr.openwide.core.wicket.more.console.navigation.page.ConsoleAccessDeniedPage;
import fr.openwide.core.wicket.more.console.navigation.page.ConsoleLoginFailurePage;
import fr.openwide.core.wicket.more.console.navigation.page.ConsoleLoginSuccessPage;
import fr.openwide.core.wicket.more.console.navigation.page.ConsoleSignInPage;
import fr.openwide.core.wicket.more.console.template.ConsoleConfiguration;
import fr.openwide.core.wicket.more.console.template.style.ConsoleLessCssResourceReference;
import fr.openwide.core.wicket.more.link.descriptor.parameter.CommonParameters;
import fr.openwide.core.wicket.more.markup.html.pages.monitoring.DatabaseMonitoringPage;
import fr.openwide.core.wicket.more.rendering.BooleanRenderer;
import fr.openwide.core.wicket.more.rendering.EnumRenderer;
import fr.openwide.core.wicket.more.rendering.LocaleRenderer;
import fr.openwide.core.wicket.more.security.page.LoginFailurePage;
import fr.openwide.core.wicket.more.security.page.LoginSuccessPage;
import fr.openwide.core.wicket.more.util.convert.HibernateProxyAwareConverterLocator;

public class BasicApplicationApplication extends CoreWicketAuthenticatedApplication {
	
	public static final String NAME = "BasicApplicationApplication";
	
	@Autowired
	private IPropertyService propertyService;

	@Autowired
	public IInfinispanClusterService infinispanClusterService;
	
	public static BasicApplicationApplication get() {
		final Application application = Application.get();
		if (application instanceof BasicApplicationApplication) {
			return (BasicApplicationApplication) application;
		}
		throw new WicketRuntimeException("There is no BasicApplicationApplication attached to current thread " +
				Thread.currentThread().getName());
	}
	
	@Override
	public void init() {
		super.init();
		
		// si on n'est pas en développement, on précharge les feuilles de styles pour éviter la ruée et permettre le remplissage du cache
		if (!propertyService.isConfigurationTypeDevelopment()) {
			preloadStyleSheets(
					ConsoleLessCssResourceReference.get(),
					NotificationLessCssResourceReference.get(),
					ApplicationAccessLessCssResourceReference.get(),
					StylesLessCssResourceReference.get()
			);
		}

		getResourceSettings().getStringResourceLoaders().addAll(
				0, // Override the keys in existing resource loaders with the following 
				ImmutableList.of(
						new ClassStringResourceLoader(BasicApplicationApplicationResources.class),
						new ClassStringResourceLoader(BasicApplicationBusinessResources.class),
						new ClassStringResourceLoader(BasicApplicationCommonResources.class),
						new ClassStringResourceLoader(BasicApplicationConsoleResources.class),
						new ClassStringResourceLoader(BasicApplicationEnumResources.class),
						new ClassStringResourceLoader(BasicApplicationNavigationResources.class),
						new ClassStringResourceLoader(BasicApplicationNotificationResources.class)
				)
		);
	}
	
	@Override
	protected IConverterLocator newConverterLocator() {
		ConverterLocator converterLocator = new ConverterLocator();
		
		converterLocator.set(Authority.class, AuthorityRenderer.get());
		converterLocator.set(User.class, UserRenderer.get());
		converterLocator.set(TechnicalUser.class, UserRenderer.get());
		converterLocator.set(BasicUser.class, UserRenderer.get());
		converterLocator.set(UserGroup.class, UserGroupRenderer.get());
		
		converterLocator.set(Locale.class, LocaleRenderer.get());
		converterLocator.set(Boolean.class, BooleanRenderer.withPrefix("common.boolean.yesNo"));
		
		converterLocator.set(HistoryValue.class, HistoryValueRenderer.get());
		converterLocator.set(HistoryEventType.class, EnumRenderer.get());
		
		converterLocator.set(PostalCode.class, PostalCodeConverter.get());
		
		return new HibernateProxyAwareConverterLocator(converterLocator);
	}

	@Override
	protected void mountApplicationPages() {
		
		// Sign in
		mountPage("/login/", getSignInPageClass());
		mountPage("/login/failure/", LoginFailurePage.class);
		mountPage("/login/success/", LoginSuccessPage.class);
		
		mountPage("/security/password/recovery/", SecurityPasswordRecoveryPage.class);
		mountPage("/security/password/expiration/", SecurityPasswordExpirationPage.class);
		mountParameterizedPage("/security/password/reset/", SecurityPasswordResetPage.class);
		mountParameterizedPage("/security/password/creation/", SecurityPasswordCreationPage.class);
		
		// Console sign in
		mountPage("/console/login/", ConsoleSignInPage.class);
		mountPage("/console/login/failure/", ConsoleLoginFailurePage.class);
		mountPage("/console/login/success/", ConsoleLoginSuccessPage.class);
		mountPage("/console/access-denied/", ConsoleAccessDeniedPage.class);
		
		// Profile
		mountPage("/profile/", ProfilePage.class);
		
		// Maintenance
		mountPage("/maintenance/", MaintenancePage.class);
		
		// Administration
		mountPage("/administration/basic-user/", AdministrationBasicUserPortfolioPage.class);
		mountParameterizedPage("/administration/basic-user/${" + CommonParameters.ID + "}/", AdministrationBasicUserDescriptionPage.class);
		mountPage("/administration/technical-user/", AdministrationTechnicalUserPortfolioPage.class);
		mountParameterizedPage("/administration/technical-user/${" + CommonParameters.ID + "}/", AdministrationTechnicalUserDescriptionPage.class);
		mountPage("/administration/user-group/", AdministrationUserGroupPortfolioPage.class);
		mountParameterizedPage("/administration/user-group/${" + CommonParameters.ID + "}/", AdministrationUserGroupDescriptionPage.class);
		
		// Reference data
		mountPage("/reference-data/", ReferenceDataPage.class);
		
		// Console
		ConsoleConfiguration consoleConfiguration = ConsoleConfiguration.build("console", propertyService);
		consoleConfiguration.mountPages(this);
		
		ConsoleMenuSection notificationMenuSection = new ConsoleMenuSection("notificationsMenuSection", "console.notifications",
				"notifications", ConsoleNotificationDemoIndexPage.class);
		consoleConfiguration.addMenuSection(notificationMenuSection);
		
		mountPage("/console/notifications/", ConsoleNotificationDemoIndexPage.class);
		
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
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	public Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}
	
}
