package fr.openwide.core.basicapp.init.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.openwide.core.basicapp.core.config.spring.BasicApplicationCoreCommonConfig;
import fr.openwide.core.basicapp.init.BasicApplicationInitPackage;
import fr.openwide.core.spring.config.spring.annotation.ConfigurationLocations;

@Configuration
@Import({
	BasicApplicationCoreCommonConfig.class
})
@ConfigurationLocations(
		locations = {
				"classpath:configuration-init.properties",
				"classpath:configuration-init-${user}.properties"
		},
		order = 1 // Permet de surcharger la configuration du core
)
@ComponentScan(
		basePackageClasses = {
			BasicApplicationInitPackage.class
		}
)
public class BasicApplicationInitConfig {
}