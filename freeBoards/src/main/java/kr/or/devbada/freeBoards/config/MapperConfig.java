package kr.or.devbada.freeBoards.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Mapper Configuration
 * @author minam.cho
 * @since March 20, 2020
 */
@Configuration
@MapperScan("kr.or.devbada")
public class MapperConfig {
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(HikariDataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("kr.or.devbada");
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:kr/or/devbada/*/mapper/*.xml"));
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}