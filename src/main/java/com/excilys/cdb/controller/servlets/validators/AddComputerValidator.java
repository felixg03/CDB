package com.excilys.cdb.controller.servlets.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.cdb.DTOs.DTOComputerAdd;
import com.excilys.cdb.customExceptions.InvalidUserInputException;

public class AddComputerValidator {
	private static final int MAX_COMPANYID = 44;
	private String nameRegex = "^[a-zA-Z].*"; // Name has to start by a UpperCase or LowerCase letter.
											  // The rest is filled has the user wishes
	private static DateTimeFormatter dateTimeFormatter = 
	DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public void validateComputerName(String name) 
						throws InvalidUserInputException {
		if (name == null || name == "") {
			throw new InvalidUserInputException(
			"You must enter a computer name");
		}
		else if (!name.matches(nameRegex)) {
			throw new InvalidUserInputException(
			"The computer name must start with a letter");
		}
	}
	
	public void validateComputerDates(String introducedString
									, String discontinuedString) 
									throws InvalidUserInputException
										 , DateTimeParseException {
		
		if (introducedString != null && discontinuedString != null 
		 && introducedString != "" && discontinuedString != "") {
			
			LocalDate introduced = LocalDate
								  .parse(introducedString
					 				   , dateTimeFormatter);
			LocalDate discontinued = LocalDate
					  				.parse(discontinuedString
					  					 , dateTimeFormatter);
			
			if (introduced.isAfter(discontinued)) {
				throw new InvalidUserInputException(
				"Introduced date must anterior to discontinued date");
			}
		}
	}
	
	public void validateCompanyId(String companyIdString) 
						throws InvalidUserInputException {
		
		if (companyIdString != null) {
			long companyId = Long.valueOf(companyIdString);
			if (companyId < 1 || companyId > MAX_COMPANYID) {
				throw new InvalidUserInputException("Invalid company");
			}
		}
	}
	
	public void validate(DTOComputerAdd dtoComputerAddComputer) 
										throws InvalidUserInputException {
		try {
			if ( dtoComputerAddComputer == null ) throw new NullPointerException();
			
			validateComputerName(dtoComputerAddComputer.name);
			validateComputerDates(dtoComputerAddComputer.introduced
								, dtoComputerAddComputer.discontinued);
			validateCompanyId(dtoComputerAddComputer.companyId);
		}
		catch ( NullPointerException npe ) {
			npe.printStackTrace();
		}
	}
}
