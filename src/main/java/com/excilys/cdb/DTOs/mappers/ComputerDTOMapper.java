package com.excilys.cdb.DTOs.mappers;

import java.time.LocalDate;

import com.excilys.cdb.DTOs.DTOComputerAddComputer;
import com.excilys.cdb.DTOs.DTOComputerDashboard;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer.ComputerBuilder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ComputerDTOMapper {
	
	private static DateTimeFormatter dateTimeFormatter = 
	DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	/*
	 * #################################
	 * ###   DTOComputerAddComputer  ###
	 * #################################
	 */
	public static DTOComputerAddComputer convertToDTOComputerAddComputer(Computer computer) {
		String name = computer.getName();
		String introduced = parseLocalDateToString(computer
											 .getIntroduced());
		String discontinued = parseLocalDateToString(computer
				 							   .getDiscontinued());
		String companyId = String.valueOf(computer.getCompany().getId());
		
		return new DTOComputerAddComputer(name
									 , introduced
									 , discontinued
									 , companyId);
	}
	
	public static Computer convertToComputer(DTOComputerAddComputer dtoComputerWithoutId) {
		String name = dtoComputerWithoutId.name;
		LocalDate introduced = parseStringToLocalDate(dtoComputerWithoutId
													 .introduced);
		LocalDate discontinued = parseStringToLocalDate(dtoComputerWithoutId
				 									   .discontinued);
		long companyId = parseStringToLong(dtoComputerWithoutId.companyId);
		Company company = new CompanyBuilder().setId(companyId)
											  .build();
		
		return new ComputerBuilder().setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
	}
	
	public static List<DTOComputerAddComputer> convertToListDTOComputerAddComputer(List<Computer> listComputer) {
		List<DTOComputerAddComputer> listDTOComputerAddComputer = new ArrayList<>();
		for(Computer computer : listComputer) {
			listDTOComputerAddComputer.add(convertToDTOComputerAddComputer(computer));
		}
		return listDTOComputerAddComputer;
	}
	
	
	/*
	 * ##################################
	 * ###							  ###
	 * ###	  DTOComputerDashboard	  ###							  
	 * ###							  ###
	 * ##################################
	 */
	public static DTOComputerDashboard convertToDTOComputerDashbord(Computer computer) {
		String name = computer.getName();
		String introduced = parseLocalDateToString(computer
											 .getIntroduced());
		String discontinued = parseLocalDateToString(computer
				 							   .getDiscontinued());
		String companyName = computer.getCompany().getName();
		
		return new DTOComputerDashboard(name
									 , introduced
									 , discontinued
									 , companyName);
	}
	
	public static List<DTOComputerDashboard> convertToListDTOComputerDashboard(List<Computer> listComputer) {
		List<DTOComputerDashboard> listDTOComputerDashboard = new ArrayList<>();
		for(Computer computer : listComputer) {
			listDTOComputerDashboard.add(convertToDTOComputerDashbord(computer));
		}
		return listDTOComputerDashboard;
	}
	
	public static Computer convertToComputer(DTOComputerDashboard dtoComputerDashboard) {
		String name = dtoComputerDashboard.name;
		LocalDate introduced = parseStringToLocalDate(dtoComputerDashboard
													 .introduced);
		LocalDate discontinued = parseStringToLocalDate(dtoComputerDashboard
				 									   .discontinued);
		String companyName = dtoComputerDashboard.companyName;
		Company company = new CompanyBuilder().setName(companyName)
											  .build();
		
		return new ComputerBuilder().setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
	}
	
	public static List<Computer> convertToListComputer(List<DTOComputerDashboard> listDTOComputerDashboard) {
		List<Computer> listComputer = new ArrayList<>();
		for(DTOComputerDashboard dtoComputerDashboard : listDTOComputerDashboard) {
			listComputer.add(convertToComputer(dtoComputerDashboard));
		}
		return listComputer;
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
	
	private static String parseLocalDateToString(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		else {
			return localDate.toString();
		}
	}
	
	private static long parseStringToLong(String StringOfALong) {
		if (StringOfALong == "" || StringOfALong == null) {
			return 0;
		}
		else {
			return Long.valueOf(StringOfALong);
		}
	}
}
 