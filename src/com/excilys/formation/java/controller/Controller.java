package com.excilys.formation.java.controller;

import com.excilys.formation.java.model.Model;
import com.excilys.formation.java.view.View;

public class Controller {
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}
}
