package com.excilys.formation.java.view;

import com.excilys.formation.java.model.Model;
import com.excilys.formation.java.controller.Controller;

public class View {
	
	private Model model;
	private Controller controller;
	
	public View(Model model) {
		super();
		this.model = model;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void display_cli() {
		
	}
}
