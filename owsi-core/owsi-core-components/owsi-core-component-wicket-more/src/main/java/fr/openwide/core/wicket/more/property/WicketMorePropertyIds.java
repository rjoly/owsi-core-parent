package fr.openwide.core.wicket.more.property;

import java.util.concurrent.TimeUnit;

import org.apache.wicket.Application;

import fr.openwide.core.spring.property.model.AbstractPropertyIds;
import fr.openwide.core.spring.property.model.ImmutablePropertyId;
import fr.openwide.core.spring.property.model.ImmutablePropertyIdTemplate;

public final class WicketMorePropertyIds extends AbstractPropertyIds {
	
	private WicketMorePropertyIds() {
	}

	/*
	 * Mutable Properties
	 */
	
	// None
	
	/*
	 * Immutable Properties
	 */
	
	public static final ImmutablePropertyId<String> WICKET_DEFAULT_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SCHEME = immutable("wicket.backgroundThreadContextBuilder.url.scheme");
	public static final ImmutablePropertyId<String> WICKET_DEFAULT_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVER_NAME = immutable("wicket.backgroundThreadContextBuilder.url.serverName");
	public static final ImmutablePropertyId<Integer> WICKET_DEFAULT_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVER_PORT = immutable("wicket.backgroundThreadContextBuilder.url.serverPort");
	public static final ImmutablePropertyId<String> WICKET_DEFAULT_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVLET_PATH = immutable("wicket.backgroundThreadContextBuilder.url.servletPath");

	public static final ImmutablePropertyIdTemplate<String> WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SCHEME_TEMPLATE =
			immutableTemplate("wicket.%s.backgroundThreadContextBuilder.url.scheme");
	public static ImmutablePropertyId<String> wicketBackgroundThreadContextBuilderUrlScheme(Application application) {
		return WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SCHEME_TEMPLATE.create(application.getName());
	}
	public static final ImmutablePropertyIdTemplate<String> WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVER_NAME_TEMPLATE =
			immutableTemplate("wicket.%s.backgroundThreadContextBuilder.url.serverName");
	public static ImmutablePropertyId<String> wicketBackgroundThreadContextBuilderUrlServerName(Application application) {
		return WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVER_NAME_TEMPLATE.create(application.getName());
	}
	public static final ImmutablePropertyIdTemplate<Integer> WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVER_PORT_TEMPLATE =
			immutableTemplate("wicket.%s.backgroundThreadContextBuilder.url.serverPort");
	public static ImmutablePropertyId<Integer> wicketBackgroundThreadContextBuilderUrlServerPort(Application application) {
		return WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVER_PORT_TEMPLATE.create(application.getName());
	}
	public static final ImmutablePropertyIdTemplate<String> WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVLET_PATH_TEMPLATE =
			immutableTemplate("wicket.%s.backgroundThreadContextBuilder.url.servletPath");
	public static ImmutablePropertyId<String> wicketBackgroundThreadContextBuilderUrlServletPath(Application application) {
		return WICKET_APPLICATION_BACKGROUND_THREAD_CONTEXT_BUILDER_URL_SERVLET_PATH_TEMPLATE.create(application.getName());
	}

	
	public static final ImmutablePropertyId<String> WICKET_DISK_DATA_STORE_PATH = immutable("wicket.diskDataStore.path");
	public static final ImmutablePropertyId<Integer> WICKET_DISK_DATA_STORE_IN_MEMORY_CACHE_SIZE = immutable("wicket.diskDataStore.inMemoryCacheSize");
	public static final ImmutablePropertyId<Integer> WICKET_DISK_DATA_STORE_MAX_SIZE_PER_SESSION = immutable("wicket.diskDataStore.maxSizePerSession");
	
	public static final ImmutablePropertyId<Integer> GLOBAL_FEEDBACK_AUTOHIDE_DELAY_VALUE = immutable("globalFeedback.autohide.delay.value");
	public static final ImmutablePropertyId<TimeUnit> GLOBAL_FEEDBACK_AUTOHIDE_DELAY_UNIT = immutable("globalFeedback.autohide.delay.unit");
	public static final ImmutablePropertyId<Integer> CONSOLE_GLOBAL_FEEDBACK_AUTOHIDE_DELAY_VALUE = immutable("console.globalFeedback.autohide.delay.value");
	public static final ImmutablePropertyId<TimeUnit> CONSOLE_GLOBAL_FEEDBACK_AUTOHIDE_DELAY_UNIT = immutable("console.globalFeedback.autohide.delay.unit");
	
	public static final ImmutablePropertyId<Integer> AUTOCOMPLETE_LIMIT = immutable("autocomplete.limit");
}
