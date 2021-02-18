package com.excilys.cdb.controller;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.Model;
import com.excilys.cdb.view.View;

public class Controller {
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	public boolean action(int input) {
		
		int next, offset = 0;
		switch (input) {
		
		// DISPLAY LIST COMPUTERS
		case 1: do {
					next = view
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
					next = view
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
