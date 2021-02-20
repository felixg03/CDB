package com.excilys.cdb.controller;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.Model;
import com.excilys.cdb.view.ViewPrincipal;
import com.excilys.cdb.view.ViewComputer;
import com.excilys.cdb.view.ViewCompany;

public class Controller {
	
	private Model model;
	private ViewPrincipal viewPrincipal;
	private ViewComputer viewComputer;
	private ViewCompany viewCompany;
	
	public Controller(Model model, ViewPrincipal viewPrincipal) {
		super();
		this.model = model;
		this.viewPrincipal = viewPrincipal;
		this.viewComputer = this.viewPrincipal.getViewComputer();
		this.viewCompany = this.viewPrincipal.getViewCompany();
	}
	
	public boolean action(int input) {
		
		int next, offset = 0;
		switch (input) {
		
		// DISPLAY LIST COMPUTERS
		case 1: do {
					next = viewComputer
						  .displayListComputers(
								  model
								  .getComputerService()
								  .getListComputers(offset)
					);
					

					if (next == 0 && offset >= 10) offset -= 10;
					else if (next == 1) offset += 10;
					
				} while (next == 0 || next == 1);
				
			break;
			
		// DISPLAY LIST COMPANIES
		case 2: do {
					next = viewCompany
						  .displayListCompanies(
								model
							    .getCompanyService()
								.getListCompanies(offset)
						   );
					
					if (next == 0 && offset >= 10) offset -= 10;
					else if (next == 1) offset += 10;
					
				} while (next == 0 || next == 1);
				
			break;	
			
		
		// DISPLAY ONE COMPUTER DETAILS
		case 3: long computerIdToShowDetails = viewComputer.getComputerId(input);
			
				viewComputer
				.displayOneComputerDetails(
						model
						.getComputerService()
						.getOneComputerDetails(computerIdToShowDetails)
				);
			break;	
			
		
		// CREATE COMPUTER
		case 4: Computer computerToCreate = viewComputer
										   .getComputerToCreate();
			
				viewComputer
				.displayResultComputerCreation(
						model
						.getComputerService()
						.getResultComputerCreation(computerToCreate)
				);
			break;	
		
		
		// UPDATE COMPUTER
		case 5: Computer computerToUpdate = viewComputer
											.getComputerInfoToUpdate();
				
				viewComputer
				.displayResultComputerUpdate(
						model
						.getComputerService()
						.getResultComputerUpdate(computerToUpdate)
				);
			break;	
		
		
		// DELETE COMPUTER
		case 6: long computerIdToDelete = viewComputer.getComputerId(input);
		
				viewComputer
				.displayResultComputerDeletion(
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
