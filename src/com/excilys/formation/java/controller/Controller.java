package com.excilys.formation.java.controller;

import com.excilys.formation.java.service.Model;
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
		
		case 1: view
				.displayListComputers(
						model
						.getComputerService()
						.getListComputers()
				);
			break;
			
		case 2: view
				.displayListCompanies(
						model
					    .getCompanyService()
						.getListCompanies()
				);
			break;	
		
		
		case 3: long computerId = view.getComputerId();
			
				view
				.displayOneComputerDetails(
						model
						.getComputerService()
						.getOneComputerDetails(computerId)
				);
			break;	
		
		case 4: view
				.displayResultComputerCreation(
						model
						.getComputerService()
						.getResultComputerCreation()
				);
			break;	
		
		case 5: view.displayResultComputerUpdate(
						model
						.getComputerService()
						.getResultComputerUpdate()
				);
			break;	
		
		case 6: view.displayResultComputerDeletion(
						model
						.getComputerService()
						.getResultComputerDeletion()
				);
			break;	
		
		case 7: return false;
		
		}
		
		return true; // default
	}
}