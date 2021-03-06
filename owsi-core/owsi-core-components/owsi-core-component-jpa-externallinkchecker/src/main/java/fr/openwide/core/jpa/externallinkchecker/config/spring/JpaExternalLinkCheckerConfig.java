package fr.openwide.core.jpa.externallinkchecker.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import fr.openwide.core.jpa.config.spring.provider.JpaPackageScanProvider;
import fr.openwide.core.jpa.externallinkchecker.business.JpaExternalLinkCheckerBusinessPackage;

@Configuration
@Import(JpaExternalLinkCheckerApplicationPropertyRegistryConfig.class)
@ComponentScan(basePackageClasses = { JpaExternalLinkCheckerBusinessPackage.class })
@EnableAspectJAutoProxy
public class JpaExternalLinkCheckerConfig {
	
	@Bean
	public JpaPackageScanProvider jpaPackageScanProvider() {
		return new JpaPackageScanProvider(
				JpaExternalLinkCheckerBusinessPackage.class.getPackage()
		);
	}

}
