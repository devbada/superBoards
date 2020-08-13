package kr.or.devbada.freeBoards.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * DataSource Configuration
 * @author minam.cho
 * @since August 04, 2020
 */
@Slf4j
@Configuration
public class DataSourceConfig {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.userpass}")
	private String userpass;
	
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	@Primary
	public HikariDataSource dataSource() {
		log.info("##### driverClassName : " + driverClassName);
		log.info("##### url : " + url);
		
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.driverClassName(driverClassName)
				.url(url)
				.username(username)
				.password(userpass)
				.build();
	}
	
	@Bean(name = "jasyptStringEncryptor")
	public PooledPBEStringEncryptor environmentPBEConfig() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	    config.setAlgorithm("PBEWithMD5AndDES");
	    config.setPassword("PASSWORD_KEY");
	    config.setPoolSize("1");
	    config.setKeyObtentionIterations("1000");
	    config.setProviderName("SunJCE");
	    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	    config.setStringOutputType("base64");
	    encryptor.setConfig(config);
		return encryptor;
	}
}