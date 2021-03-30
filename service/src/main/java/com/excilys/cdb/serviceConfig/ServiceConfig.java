package com.excilys.cdb.serviceConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan( basePackages = { "com.excilys.cdb.services"
							   , "com.excilys.cdb.repositoryInterfaces" } )
@EnableJpaRepositories( basePackages = { "com.excilys.cdb.repositoryInterfaces" } )
public class ServiceConfig {
	
	
}