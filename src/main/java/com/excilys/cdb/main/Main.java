package com.excilys.cdb.main;


import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import com.excilys.cdb.views.ViewPrincipal;
import com.excilys.cdb.controller.CliController;
import com.excilys.cdb.services.Model;

public class Main {
	
	private static final Logger logger 
    = (Logger) LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		final Model model = new Model();
		final ViewPrincipal view = new ViewPrincipal(model);
		final CliController controller = new CliController(model, view);
		
		view.setController(controller);
		
	    // logger.info("Example log from {}", Main.class.getSimpleName());
		
		view.displayCli();
	}

}
