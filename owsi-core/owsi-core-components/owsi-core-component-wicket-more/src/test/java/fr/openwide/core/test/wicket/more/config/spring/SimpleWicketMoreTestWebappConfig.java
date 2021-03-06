package fr.openwide.core.test.wicket.more.config.spring;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.openwide.core.test.wicket.more.application.WicketMoreTestApplication;
import fr.openwide.core.wicket.more.config.spring.AbstractWebappConfig;

/**
 * Stub.
 */
@Configuration
@Import({
	WicketMoreTestCoreCommonConfig.class
})
public class SimpleWicketMoreTestWebappConfig extends AbstractWebappConfig {

	@Override
	@Bean
	public WebApplication application() {
		return new WicketMoreTestApplication();
	}

}
