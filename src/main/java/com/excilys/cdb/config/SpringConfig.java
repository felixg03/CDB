package com.excilys.cdb.config;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration
@ComponentScan( basePackages = 
				{ "com.excilys.cdb.DAOs", "com.excilys.cdb.services", 
				  "com.excilys.cdb.controller", "com.excilys.cdb.views", 
				  "com.excilys.cdb.controller.servlets" } )

public class SpringConfig extends AbstractContextLoaderInitializer {
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfig.class);
		return rootContext;
	}
	
	@Bean//( destroyMethod = "close" )
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource( new HikariConfig( "/hikariConfig.properties" ) );
	}
	
	
}
