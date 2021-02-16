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
	
	public boolean action(int input) {
		
		switch (input) {
		
		case 1:
			break;
			
		case 2:
			break;	
		
		case 3:
			break;	
		
		case 4:
			break;	
		
		case 5:
			break;	
		
		case 6:
			break;	
		
		case 7:
			return false;
		}
		
		return true; // default
	}
}
