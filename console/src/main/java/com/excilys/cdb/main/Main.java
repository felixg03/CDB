package com.excilys.cdb.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.consoleConfig.ConsoleContextConfig;
import com.excilys.cdb.views.ViewPrincipal;


public class Main {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
			new AnnotationConfigApplicationContext( ConsoleContextConfig.class );
		
		final ViewPrincipal viewPrincipal = context.getBean( ViewPrincipal.class );
		
		
	    // logger.info("Example log from {}", Main.class.getSimpleName());
		
		viewPrincipal.displayCli();
		
		context.close();
	}

}
