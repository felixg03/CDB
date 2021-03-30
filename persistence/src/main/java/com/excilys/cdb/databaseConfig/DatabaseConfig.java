package com.excilys.cdb.databaseConfig;

import java.sql.SQLException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableJpaRepositories( basePackages = "com.excilys.cdb.repositoryInterfaces"
					  , transactionManagerRef = "transactionManager" )
@ComponentScan( { "com.excilys.cdb.repositoryInterfaces",  "com.excilys.cdb.databaseConfig" } )
public class DatabaseConfig {

	@Bean
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource( new HikariConfig( "/hikariConfig.properties" ) );
	}

	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getHikariDataSource());
		em.setPackagesToScan("com.excilys.cdb.DTOs.DTODatabase");
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());
		return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
    
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//    	return new PersistenceExceptionTranslationPostProcessor();
//    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return hibernateProperties;
    }
}