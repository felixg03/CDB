package com.excilys.cdb.controller;

import java.util.InputMismatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.views.ViewCompany;
import com.excilys.cdb.views.ViewComputer;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class CliController {

	private ViewComputer viewComputer;
	private ViewCompany viewCompany;

	private CompanyService companyService;
	private ComputerService computerService;
	
	
	@Autowired
	public CliController(ViewComputer viewComputer, ViewCompany viewCompany, CompanyService companyService,
			ComputerService computerService) {
		super();
		this.viewComputer = viewComputer;
		this.viewCompany = viewCompany;
		this.companyService = companyService;
		this.computerService = computerService;
	}



	public boolean action(int input) throws NumberFormatException, InvalidComputerIdException, InputMismatchException {

		int next, offset = 0;
		switch (input) {

		// DISPLAY LIST COMPUTERS
		case 1:
			do {
				next = viewComputer.displayListComputers(computerService.getListComputers(offset));

				if (next == 1 && offset >= 10) {
					offset -= 10;
				}

				else if (next == 2) {
					offset += 10;
				}

			} while (next == 1 || next == 2);

			break;

		// DISPLAY LIST COMPANIES
		case 2:
			do {
				next = viewCompany.displayListCompanies(companyService.getListCompanies(offset));

				if (next == 1 && offset >= 10)
					offset -= 10;
				else if (next == 2)
					offset += 10;

			} while (next == 1 || next == 2);

			break;

		// DISPLAY ONE COMPUTER DETAILS
		case 3:
			try {
				long computerIdToShowDetails = viewComputer.getComputerId(input); // throws NumberFormatException

				viewComputer // throws InvalidComputerIdException
						.displayOneComputerDetails(computerService.getOneComputer(computerIdToShowDetails));
			} catch (Exception e) {
				if (e.getClass() == NumberFormatException.class) {
					throw (NumberFormatException) e;
				} else if (e.getClass() == InvalidComputerIdException.class) {
					throw (InvalidComputerIdException) e;
				}
			}

			break;

		// CREATE COMPUTER
		case 4:
			try {
				Computer computerToCreate = viewComputer.getComputerToCreate();
				computerService.createComputer(computerToCreate);

				viewComputer.displayResultComputerCreation();
			} catch (InputMismatchException inputMismatchEx) {
				throw inputMismatchEx;
			}
			break;

		// UPDATE COMPUTER
		/*
		 * !!!!!!!!!!!!!!!! !!! BROKEN !!! !!!!!!!!!!!!!!!!
		 */

		/*
		 * case 5: Computer computerToUpdate = viewComputer .getComputerInfoToUpdate();
		 * 
		 * 
		 * 
		 * // BROKEN HERE AT LINE BELOW: // Broken because Update function has been
		 * commented // because it has to be refactored
		 * 
		 * model.getComputerService() .getResultComputerUpdate(computerToUpdate);
		 * 
		 * viewComputer.displayResultComputerUpdate();
		 * 
		 * break;
		 */

		// DELETE COMPUTER
		case 6:
			long computerIdToDelete = viewComputer.getComputerId(input);

			computerService.deleteComputer(computerIdToDelete);

			viewComputer.displayResultComputerDeletion();

			break;

		// DELETE COMPANY
		case 7:
			companyService.callCompanyDeletion(viewCompany.getCompanyId());
			break;

		// EXIT APPLICATION
		case 8:
			return false;

		}

		return true; // default
	}
}
