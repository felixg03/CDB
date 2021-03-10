package com.excilys.cdb.main;


import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.config.SpringConfig;
import com.excilys.cdb.views.ViewPrincipal;

import ch.qos.logback.classic.Logger;

public class Main {
	
	private static final Logger logger 
    = (Logger) LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
			new AnnotationConfigApplicationContext( SpringConfig.class );
		
		final ViewPrincipal viewPrincipal = context.getBean( ViewPrincipal.class );
		
		
	    // logger.info("Example log from {}", Main.class.getSimpleName());
		
		viewPrincipal.displayCli();
		
		context.close();
	}

}
