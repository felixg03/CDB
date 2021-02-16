package com.excilys.formation.java.main;

import com.excilys.formation.java.service.Model;
import com.excilys.formation.java.controller.Controller;
import com.excilys.formation.java.view.View;

public class Main {

	public static void main(String[] args) {
		final Model model = new Model();
		final View view = new View(model);
		final Controller controller = new Controller(model, view);
		
		view.setController(controller);
		
		view.displayCli();
	}

}
