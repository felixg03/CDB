package com.excilys.cdb.controller;

import java.util.InputMismatchException;

import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.Model;
import com.excilys.cdb.views.ViewCompany;
import com.excilys.cdb.views.ViewComputer;
import com.excilys.cdb.views.ViewPrincipal;

public class CliController {
	
	private Model model;
	private ViewPrincipal viewPrincipal;
	private ViewComputer viewComputer;
	private ViewCompany viewCompany;
	
	public CliController(Model model, ViewPrincipal viewPrincipal) {
		super();
		this.model = model;
		this.viewPrincipal = viewPrincipal;
		this.viewComputer = this.viewPrincipal.getViewComputer();
		this.viewCompany = this.viewPrincipal.getViewCompany();
	}
	
	public boolean action(int input) throws NumberFormatException
										  , InvalidComputerIdException
										  , InputMismatchException {
		
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
					

					if (next == 1 && offset >= 10) offset -= 10;
					else if (next == 2) offset += 10;
					
				} while (next == 1 || next == 2);
				
			break;
			
		// DISPLAY LIST COMPANIES
		case 2: do {
					next = viewCompany
						  .displayListCompanies(
								model
							    .getCompanyService()
								.getListCompanies(offset)
						   );
					
					if (next == 1 && offset >= 10) offset -= 10;
					else if (next == 2) offset += 10;
					
				} while (next == 1 || next == 2);
				
			break;	
			
		
		// DISPLAY ONE COMPUTER DETAILS
		case 3: try {
					long computerIdToShowDetails = viewComputer.getComputerId(input); // throws NumberFormatException
					
					viewComputer						// throws InvalidComputerIdException
					.displayOneComputerDetails(
							model
							.getComputerService()
							.getOneComputerDetails(computerIdToShowDetails)
					);
				}
				catch (Exception e) {
					if (e.getClass() == NumberFormatException.class) {
						throw (NumberFormatException) e;
					}
					else if (e.getClass() == InvalidComputerIdException.class) {
						throw (InvalidComputerIdException) e;
					}
				}
				
			break;	
			
		
		// CREATE COMPUTER
		case 4: try {
					Computer computerToCreate = viewComputer
							   .getComputerToCreate();
					model.getComputerService()
					.callComputerCreationInDaoComputer(computerToCreate);
					
					viewComputer.displayResultComputerCreation();
				}
				catch (InputMismatchException inputMismatchEx) {
					throw inputMismatchEx;
				}
			break;	
		
		
		// UPDATE COMPUTER
		/*
		 * !!!!!!!!!!!!!!!!
		 * !!!  BROKEN  !!!
		 * !!!!!!!!!!!!!!!!
		 */
		
		/*
		case 5: Computer computerToUpdate = viewComputer
											.getComputerInfoToUpdate();
				
				
				
				// BROKEN HERE AT LINE BELOW:
				// Broken because Update function has been commented 
				// because it has to be refactored
				
				model.getComputerService()
					 .getResultComputerUpdate(computerToUpdate);
					 
				viewComputer.displayResultComputerUpdate();
				
			break;	
		*/
		
		// DELETE COMPUTER
		case 6: long computerIdToDelete = viewComputer
										 .getComputerId(input);
				
				model.getComputerService()
					 .getResultComputerDeletion(computerIdToDelete);
				
				viewComputer.displayResultComputerDeletion();
				
			break;	
		
			
		// EXIT APPLICATION
		case 7: return false;
		
		}
		
		return true; // default
	}
}
