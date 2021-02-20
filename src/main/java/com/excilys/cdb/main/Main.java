package com.excilys.cdb.main;

import com.excilys.cdb.service.Model;
import com.excilys.cdb.controller.Controller;
import com.excilys.cdb.view.ViewPrincipal;

public class Main {

	public static void main(String[] args) {
		final Model model = new Model();
		final ViewPrincipal view = new ViewPrincipal(model);
		final Controller controller = new Controller(model, view);
		
		view.setController(controller);
		
		view.displayCli();
	}

}
