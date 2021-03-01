package com.excilys.cdb.DTOs.mappers;

import java.time.LocalDate;

import com.excilys.cdb.DTOs.DTOComputerWithoutId;
import com.excilys.cdb.DTOs.DTOComputerId;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer.ComputerBuilder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ComputerDTOMapper {
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	/*
	 * ################################
	 * ###   DTOComputerWithoutId   ###
	 * ################################
	 */
	public static DTOComputerWithoutId convertComputerToDTOComputerWithoutId(Computer computer) {
		String name = computer.getName();
		String introduced = LocalDateToString(computer
											 .getIntroduced());
		String discontinued = LocalDateToString(computer
				 							   .getDiscontinued());
		String company = computer.getCompany().toString();
		
		return new DTOComputerWithoutId(name
									 , introduced
									 , discontinued
									 , company);
	}
	
	public static Computer convertDTOComputerWithoutIdToComputer(DTOComputerWithoutId dtoComputerWithoutId) {
		String name = dtoComputerWithoutId.name;
		LocalDate introduced = parseStringToLocalDate(dtoComputerWithoutId
													 .introduced);
		LocalDate discontinued = parseStringToLocalDate(dtoComputerWithoutId
				 									   .discontinued);
		long companyId = Long.valueOf(dtoComputerWithoutId
									 .company);					// !! COMPANY INTO LONG COMPANY_ID !!
		Company company = new CompanyBuilder().setId(companyId)
											  .build();
		
		return new ComputerBuilder().setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
	}
	
	public static List<DTOComputerWithoutId> convertListComputerToListDTOComputerWithoutId(List<Computer> listComputer) {
		List<DTOComputerWithoutId> listDTOCompanyWithoutId = new ArrayList<>();
		for(Computer computer : listComputer) {
			listDTOCompanyWithoutId.add(convertComputerToDTOComputerWithoutId(computer));
		}
		return listDTOCompanyWithoutId;
	}
	
	
	
	/*
	 * #########################
	 * ###   DTOComputerId   ###
	 * #########################
	 */
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
	
	
	
	
	/*
	 * ##############
	 * ##  TOOLS   ##
	 * ##############
	 */
	private static Company getCompanyFromCompanyString(String companyString) {
		String[] companyAttributes = companyString.split(" | ");
		System.out.println();
		for (String s : companyAttributes) {
			System.out.print(s + " ");
		}
		System.out.println();
		Long companyId = Long.valueOf(companyAttributes[0]);
		String companyName = companyAttributes[1];
		
		return new CompanyBuilder().setId(companyId)
								   .setName(companyName)
								   .build();
	}
	
	private static LocalDate parseStringToLocalDate(String localDateString) {
		if (localDateString == null) {
			return null;
		}
		else if (localDateString == "") {
			return null;
		}
		else {
			return LocalDate.parse(localDateString
								 , dateTimeFormatter);
		}
	}
	
	private static String LocalDateToString(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		else {
			return localDate.toString();
		}
	}
}
 