package com.excilys.cdb.config;


import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
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
				{ "com.excilys.cdb.controller", "com.excilys.cdb.controller.springMvcControllers"
				, "com.excilys.cdb.controller.springMvcControllers.variables"
				, "com.excilys.cdb.controller.springMvcControllers.validators", "com.excilys.cdb.DAOs"
				, "com.excilys.cdb.services", "com.excilys.cdb.views" } )
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
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}
	
	
	
	
	/*		+--------------------+
	 * 		|					 |
	 * 		|	  Hibernate		 |
	 * 		|					 |
	 * 		+--------------------+
	 */
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(getHikariDataSource());

        sessionFactory.setPackagesToScan("com.excilys.cdb.DTOView");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return hibernateProperties;
    }
    
    
    
    // Useful ?
    @Bean
    public EntityManager getEntityManager() {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.excilys.cdb.DTOView");
    	return emf.createEntityManager();
    }
}
