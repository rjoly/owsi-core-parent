package fr.openwide.core.spring.config.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Active la prise en compte des annotations {@link ApplicationDescription} et {@link ConfigurationLocations}.
 * 
 * @see ApplicationConfigurerBeanFactoryPostProcessor
 */
@Configuration
@ConfigurationLocations(locations = {
		"classpath:owsi-core-component-spring.properties"
})
public class CoreConfigurationLocationsAnnotationConfig {

	@Bean
	public static ApplicationConfigurerBeanFactoryPostProcessor applicationConfigurer() {
		return new ApplicationConfigurerBeanFactoryPostProcessor();
	}

}
