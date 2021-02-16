package com.excilys.formation.java.main;

import com.excilys.formation.java.model.Model;
import com.excilys.formation.java.controller.Controller;
import com.excilys.formation.java.view.View;

public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
		
		view.setController(controller);
		
		view.display_cli();
	}

}
