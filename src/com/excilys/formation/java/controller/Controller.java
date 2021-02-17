package com.excilys.formation.java.controller;

import com.excilys.formation.java.model.Computer;
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
		
		// DISPLAY LIST COMPUTERS
		case 1: view
				.displayListComputers(
						model
						.getComputerService()
						.getListComputers()
				);
			break;
			
		// DISPLAY LIST COMPANIES
		case 2: view
				.displayListCompanies(
						model
					    .getCompanyService()
						.getListCompanies()
				);
			break;	
			
		
		// DISPLAY ONE COMPUTER DETAILS
		case 3: long computerIdToShowDetails = view.getComputerId(input);
			
				view
				.displayOneComputerDetails(
						model
						.getComputerService()
						.getOneComputerDetails(computerIdToShowDetails)
				);
			break;	
		
		// CREATE COMPUTER
		case 4: Computer computerToCreate = view
										   .getComputerToCreate();
			
				view
				.displayResultComputerCreation(
						model
						.getComputerService()
						.getResultComputerCreation(computerToCreate)
				);
			break;	
		
		
		// UPDATE COMPUTER
		case 5: Computer computerToUpdate = view
											.getComputerInfoToUpdate();
				
				view.displayResultComputerUpdate(
						model
						.getComputerService()
						.getResultComputerUpdate(computerToUpdate)
				);
			break;	
		
		
		// DELETE COMPUTER
		case 6: long computerIdToDelete = view.getComputerId(input);
		
				view.displayResultComputerDeletion(
						model
						.getComputerService()
						.getResultComputerDeletion(computerIdToDelete)
				);
			break;	
		
			
		// EXIT APPLICATION
		case 7: return false;
		
		}
		
		return true; // default
	}
}
