package com.excilys.cdb.DTOs.mappers;

import java.time.LocalDate;

import com.excilys.cdb.DTOs.DTOComputerCreation;
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
	 * ###############################
	 * ###   DTOComputerCreation   ###
	 * ###############################
	 */
	public static DTOComputerCreation convertComputerToDTOComputerCreation(Computer computer) {
		String name = computer.getName();
		String introduced = computer
						   .getIntroduced()
						   .toString();
		String discontinued = computer
							 .getDiscontinued()
							 .toString();
		String company = computer.getCompany().toString();
		
		return new DTOComputerCreation(name
									 , introduced
									 , discontinued
									 , company);
	}
	
	public static Computer convertDTOComputerCreationToComputer(DTOComputerCreation dtoComputerCreation) {
		String name = dtoComputerCreation.name;
		LocalDate introduced = LocalDate.parse(
									  dtoComputerCreation
									 .introduced
									, dateTimeFormatter);
		LocalDate discontinued = LocalDate.parse(
									  dtoComputerCreation
									 .discontinued
									, dateTimeFormatter);
		long companyId = Long.valueOf(dtoComputerCreation
									 .companyId);
		Company company = new CompanyBuilder().setId(companyId)
											  .build();
		
		return new ComputerBuilder().setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
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
}
