package kr.or.devbada.freeBoards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * Resource Configuration
 * @author minam.cho
 * @since March 20, 2020
 */
@Component
@PropertySource(value = {
		"classpath:datasource.properties"
})
public class ResourceConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertSourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
		return propertySourcesPlaceholderConfigurer;
	}
}