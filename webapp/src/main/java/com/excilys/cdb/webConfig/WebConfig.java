package com.excilys.cdb.webConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



@EnableWebMvc
@Configuration
@ComponentScan( basePackages = 
				{ "com.excilys.cdb.restController", "com.excilys.cdb.services"
				, "com.excilys.cdb.userInputValidators", "com.excilys.cdb.repositoryInterfaces"
				, "com.excilys.cdb.databaseConfig" } )
@EnableJpaRepositories( basePackages = { "com.excilys.cdb.repositoryInterfaces" } )
public class WebConfig implements WebMvcConfigurer { // extends AbstractContextLoaderInitializer {
	

//	protected WebApplicationContext createRootApplicationContext() {
//		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//		rootContext.register(WebConfig.class);
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
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}
	
	
}
