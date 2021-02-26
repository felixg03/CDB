package com.excilys.cdb.DTOs.mappers;

import java.time.LocalDate;

import com.excilys.cdb.DTOs.DTOComputerCreation;
import com.excilys.cdb.DTOs.DTOComputerId;
import com.excilys.cdb.models.Computer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ComputerDTOMapper {
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final int COMPUTER_ID_TO_BE_FILLED_IN_THE_DAO = 0;
	
	
	public static DTOComputerCreation convertComputerToDTOComputerCreation(Computer computer) {
		String name = computer.getName();
		String introduced = computer
						   .getIntroduced()
						   .toString();
		String discontinued = computer
							 .getDiscontinued()
							 .toString();
		String companyId = String.valueOf(
								computer.getCompanyId());
		
		return new DTOComputerCreation(name
									 , introduced
									 , discontinued
									 , companyId);
	}
	
	public static Computer convertDTOComputerCreationToComputer(DTOComputerCreation dtoComputerCreation) {
		String name = dtoComputerCreation.getName();
		LocalDate introduced = LocalDate.parse(
									  dtoComputerCreation
									 .getIntroduced()
									, dateTimeFormatter);
		LocalDate discontinued = LocalDate.parse(
									  dtoComputerCreation
									 .getIntroduced()
									, dateTimeFormatter);
		long companyId = Long.valueOf(
								dtoComputerCreation
							   .getCompanyId());
		
		return new Computer(COMPUTER_ID_TO_BE_FILLED_IN_THE_DAO
						  , name
						  , introduced
						  , discontinued
						  , companyId);
	}
	
	
	
	
	public static DTOComputerId convertComputerToDTOComputerId(Computer computer) {
		return new DTOComputerId(String.valueOf(
									computer.getId()));
	}
	public static List<DTOComputerId> convertListComputerToListDTOComputerId(List<Computer> listComputer) {
		List<DTOComputerId> listDTOComputerId = new ArrayList<>();
		for(Computer computer : listComputer) {
			listDTOComputerId.add(convertComputerToDTOComputerId(computer));
		}
		return listDTOComputerId;
	}
}
