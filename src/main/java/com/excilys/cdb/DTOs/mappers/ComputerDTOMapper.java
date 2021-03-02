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
		String id = String.valueOf(computer.getId());
		String name = computer.getName();
		String introduced = parseLocalDateToString(computer
											 .getIntroduced());
		String discontinued = parseLocalDateToString(computer
				 							   .getDiscontinued());
		String companyId = String.valueOf(computer.getCompany().getId());
		String companyName = computer.getCompany().getName();
		
		return new DTOComputerDashboard(id
									  , name
									  , introduced
									  , discontinued
									  , companyId
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
		long id = Long.valueOf(dtoComputerDashboard.id);
		String name = dtoComputerDashboard.name;
		LocalDate introduced = parseStringToLocalDate(dtoComputerDashboard
													 .introduced);
		LocalDate discontinued = parseStringToLocalDate(dtoComputerDashboard
				 									   .discontinued);
		long companyId = parseStringToLong(dtoComputerDashboard.companyId);
		String companyName = dtoComputerDashboard.companyName;
		Company company = new CompanyBuilder().setId(companyId)
											  .setName(companyName)
											  .build();
		
		return new ComputerBuilder().setId(id)
									.setName(name)
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
 