package com.excilys.cdb.main;

import com.excilys.cdb.service.Model;
import com.excilys.cdb.controller.Controller;
import com.excilys.cdb.view.View;

public class Main {

	public static void main(String[] args) {
		final Model model = new Model();
		final View view = new View(model);
		final Controller controller = new Controller(model, view);
		
		view.setController(controller);
		
		view.displayCli();
	}

}
