package com.excilys.cdb.config;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@ComponentScan( basePackages = 
				{ "com.excilys.cdb.DAOs", "com.excilys.cdb.services", 
				  "com.excilys.cdb.controller", "com.excilys.cdb.views", 
				  "com.excilys.cdb.config",
				  "com.excilys.cdb.controller.springMvcControllers",
				  "com.excilys.cdb.springMvcControllers.requestVariablesContainers"} )

public class WebConfig implements WebMvcConfigurer { // extends AbstractContextLoaderInitializer {
	
//	@Override
//	protected WebApplicationContext createRootApplicationContext() {
//		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//		rootContext.register(SpringConfig.class);
//		return rootContext;
//	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/jspViews/");
		bean.setSuffix(".jsp");
		return bean;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	@Bean
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource( new HikariConfig( "/hikariConfig.properties" ) );
	}
	
	@Bean
	@Scope( value = ConfigurableBeanFactory.SCOPE_PROTOTYPE )
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}
	
}
