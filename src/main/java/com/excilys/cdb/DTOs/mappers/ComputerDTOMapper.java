package com.excilys.cdb.DTOs.mappers;

import java.time.LocalDate;

import com.excilys.cdb.DTOs.DTOComputerCreation;
import com.excilys.cdb.models.Computer;
import java.time.format.DateTimeFormatter;

public class ComputerDTOMapper {
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static DTOComputerCreation convertComputerToDTOComputerCreation(Computer computer) {
		String name = computer.getName();
		String introduced = computer.getIntroduced().toString();
		String discontinued = computer.getDiscontinued().toString();
		String companyId = String.valueOf(computer.getCompanyId());
		
		return new DTOComputerCreation(name, introduced, discontinued, companyId);
	}
	
	public static Computer convertDTOComputerCreationToComputer(DTOComputerCreation dtoComputerCreation) {
		String name = dtoComputerCreation.getName();
		LocalDate introduced = LocalDate.parse(dtoComputerCreation.getIntroduced(), dateTimeFormatter);
		LocalDate discontinued = LocalDate.parse(dtoComputerCreation.getDiscontinued(), dateTimeFormatter);
		long companyId = Long.valueOf(dtoComputerCreation.getCompanyId());
		
		return new Computer(0, name, introduced, discontinued, companyId);
	}
}
