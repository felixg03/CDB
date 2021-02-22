package com.excilys.cdb.main;


import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import com.excilys.cdb.service.Model;
import com.excilys.cdb.controller.Controller;
import com.excilys.cdb.view.ViewPrincipal;

public class Main {
	
	private static final Logger logger 
    = (Logger) LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		final Model model = new Model();
		final ViewPrincipal view = new ViewPrincipal(model);
		final Controller controller = new Controller(model, view);
		
		view.setController(controller);
		
	    // logger.info("Example log from {}", Main.class.getSimpleName());
		
		view.displayCli();
	}

}
