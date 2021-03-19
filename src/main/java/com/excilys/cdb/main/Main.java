package com.excilys.cdb.main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.config.WebConfig;
import com.excilys.cdb.views.ViewPrincipal;

public class Main {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
			new AnnotationConfigApplicationContext( WebConfig.class );
		
		final ViewPrincipal viewPrincipal = context.getBean( ViewPrincipal.class );
		
		
	    // logger.info("Example log from {}", Main.class.getSimpleName());
		
		viewPrincipal.displayCli();
		
		context.close();
	}

}
