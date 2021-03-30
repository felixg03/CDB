package com.excilys.cdb.userInputValidators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.DTOs.DTOView.DTOComputerAddView;
import com.excilys.cdb.DTOs.DTOView.DTOComputerEditView;
import com.excilys.cdb.customExceptions.InvalidUserInputException;
import com.excilys.cdb.services.CompanyService;

@Component
public class AddOrEditComputerValidator {

	private CompanyService companyService;
	private String nameRegex = "^[a-zA-Z].*"; // Name has to start by a UpperCase or LowerCase letter.
												// The rest is filled has the user wishes
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	@Autowired
	public AddOrEditComputerValidator(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	

	public void validateComputerName( String name ) throws InvalidUserInputException {
		if ( name == null || name == "" ) {
			throw new InvalidUserInputException( "You must enter a computer name" );
		} else if ( !name.matches( nameRegex ) ) {
			throw new InvalidUserInputException( "The computer name must start with a letter" );
		}
	}

	public void validateComputerDates( String introducedString, String discontinuedString )
			throws InvalidUserInputException, DateTimeParseException {

		if ( introducedString != null && discontinuedString != null && introducedString != ""
				&& discontinuedString != "" ) {

			LocalDate introduced = LocalDate.parse( introducedString, dateTimeFormatter );
			LocalDate discontinued = LocalDate.parse( discontinuedString, dateTimeFormatter );

			if ( introduced.isAfter( discontinued ) ) {
				throw new InvalidUserInputException( "Introduced date must anterior to discontinued date" );
			}
		}
	}

	public void validateCompanyId( String companyIdString ) throws InvalidUserInputException {
		
		if ( companyIdString != null && companyIdString != "" ) {
			long companyId = Long.valueOf( companyIdString );
			
			if ( !companyService.checkCompanyId( companyId ) ) {
				throw new InvalidUserInputException( "Invalid company" );
			}
		}
	}

	public void validate( DTOComputerAddView dtoComputerAddComputer ) throws InvalidUserInputException {
		try {
			validateComputerName( dtoComputerAddComputer.name );
			validateComputerDates( dtoComputerAddComputer.introduced, dtoComputerAddComputer.discontinued );
			validateCompanyId( dtoComputerAddComputer.companyId );
		} catch ( NullPointerException npe ) {
			npe.printStackTrace();
		}
	}
	
	public void validate( DTOComputerEditView dtoComputerEdit ) throws InvalidUserInputException {
		try {
			validateComputerName( dtoComputerEdit.name );
			validateComputerDates( dtoComputerEdit.introduced, dtoComputerEdit.discontinued );
			validateCompanyId( dtoComputerEdit.companyId);
		} catch ( NullPointerException npe ) {
			npe.printStackTrace();
		}
	}
}
