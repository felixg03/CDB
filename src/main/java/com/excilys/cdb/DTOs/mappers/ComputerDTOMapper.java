package com.excilys.cdb.DTOs.mappers;

import java.time.LocalDate;

import com.excilys.cdb.DTOs.DTOComputerAdd;
import com.excilys.cdb.DTOs.DTOComputerDashboard;
import com.excilys.cdb.DTOs.DTOComputerEdit;
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
	 * ##########################
	 * ###					  ###
	 * ###   DTOComputerAdd   ###
	 * ###					  ###
	 * ##########################
	 */
	public static DTOComputerAdd convertToDTOComputerAddComputer(Computer computer) {
		String name = computer.getName();
		String introduced = parseLocalDateToString(computer
											 .getIntroduced());
		String discontinued = parseLocalDateToString(computer
				 							   .getDiscontinued());
		String companyId = String.valueOf(computer.getCompany().getId());
		
		DTOComputerAdd dtoComputerAdd = new DTOComputerAdd();
		
		dtoComputerAdd.name = name;
		dtoComputerAdd.introduced = introduced;
		dtoComputerAdd.discontinued = discontinued;
		dtoComputerAdd.companyId = companyId;
		
		return dtoComputerAdd;
	}
	
	public static Computer convertToComputer(DTOComputerAdd dtoComputerAddEdit) {
		String name = dtoComputerAddEdit.name;
		LocalDate introduced = parseStringToLocalDate(dtoComputerAddEdit
													 .introduced);
		LocalDate discontinued = parseStringToLocalDate(dtoComputerAddEdit
				 									   .discontinued);
		long companyId = parseStringToLong(dtoComputerAddEdit.companyId);
		Company company = new CompanyBuilder().setId(companyId)
											  .build();
		
		return new ComputerBuilder().setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
	}
	
	public static List<DTOComputerAdd> convertToListDTOComputerAdd(List<Computer> listComputer) {
		List<DTOComputerAdd> listDTOComputerAddComputer = new ArrayList<>();
		for(Computer computer : listComputer) {
			listDTOComputerAddComputer.add(convertToDTOComputerAddComputer(computer));
		}
		return listDTOComputerAddComputer;
	}
	
	/*
	public static List<Computer> convertToListComputer(List<DTOComputerAdd> listDTOComputerAdd) {
		List<Computer> listComputer = new ArrayList<>();
		for(DTOComputerAdd dtoComputerAdd : listDTOComputerAdd) {
			listComputer.add(convertToComputer(dtoComputerAdd));
		}
		return listComputer;
	}
	*/
	
	
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
		String companyName = computer.getCompany().getName();
		
		
		DTOComputerDashboard dtoComputerDashboard = new DTOComputerDashboard();
		
		dtoComputerDashboard.id = id;
		dtoComputerDashboard.name = name;
		dtoComputerDashboard.introduced = introduced;
		dtoComputerDashboard.discontinued = discontinued;
		dtoComputerDashboard.companyName = companyName;
		
		return dtoComputerDashboard;
	}
	
	
	public static Computer convertToComputer(DTOComputerDashboard dtoComputerDashboard) {
		
		long id = Long.valueOf(dtoComputerDashboard.id);
		String name = dtoComputerDashboard.name;
		LocalDate introduced = parseStringToLocalDate(dtoComputerDashboard
													 .introduced);
		LocalDate discontinued = parseStringToLocalDate(dtoComputerDashboard
				 									   .discontinued);
		String companyName = dtoComputerDashboard.companyName;
		
		
		
		Company company = new CompanyBuilder().setName(companyName)
											  .build();
		
		return new ComputerBuilder().setId(id)
									.setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
	}

	
	public static List<DTOComputerDashboard> convertToListDTOComputerDashboard(List<Computer> listComputer) {
		List<DTOComputerDashboard> listDTOComputerDashboard = new ArrayList<>();
		for(Computer computer : listComputer) {
			listDTOComputerDashboard.add(convertToDTOComputerDashbord(computer));
		}
		return listDTOComputerDashboard;
	}
	
	/*
	public static List<Computer> convertToListComputer(List<DTOComputerDashboard> listDTOComputerDashboard) {
		List<Computer> listComputer = new ArrayList<>();
		for(DTOComputerDashboard dtoComputerDashboard : listDTOComputerDashboard) {
			listComputer.add(convertToComputer(dtoComputerDashboard));
		}
		return listComputer;
	}
	*/
	
	
	
	
	/*
	 * ##################################
	 * ###						      ###
	 * ###		 DTOComputerEdit	  ###
	 * ###							  ###
	 * ################################## 	  
	 * 
	 */
	
	
	public static DTOComputerEdit convertToDTOComputerEdit(Computer computer) {
		String id = String.valueOf(computer.getId());
		String name = computer.getName();
		String introduced = parseLocalDateToString(computer
											 .getIntroduced());
		String discontinued = parseLocalDateToString(computer
				 							   .getDiscontinued());
		String companyId = String.valueOf(computer.getCompany().getId());
		
		DTOComputerEdit dtoComputerEdit = new DTOComputerEdit();
		
		dtoComputerEdit.id = id;
		dtoComputerEdit.name = name;
		dtoComputerEdit.introduced = introduced;
		dtoComputerEdit.discontinued = discontinued;
		dtoComputerEdit.companyId = companyId;
		
		return dtoComputerEdit;
	}
	
	
	public static Computer convertToComputer(DTOComputerEdit dtoComputerEdit) {
		
		long id = Long.valueOf(dtoComputerEdit.id);
		String name = dtoComputerEdit.name;
		LocalDate introduced = parseStringToLocalDate(dtoComputerEdit
													 .introduced);
		LocalDate discontinued = parseStringToLocalDate(dtoComputerEdit
				 									   .discontinued);
		long companyId = Long.valueOf(dtoComputerEdit.companyId);
		
		Company company = new CompanyBuilder().setId(companyId)
											  .build();
		
		return new ComputerBuilder().setId(id)
									.setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
	}
	
	

	public static List<DTOComputerEdit> convertToListDTOComputerEdit(List<Computer> listComputer) {
		List<DTOComputerEdit> listDTOComputerEdit = new ArrayList<>();
		for(Computer computer : listComputer) {
			listDTOComputerEdit.add(convertToDTOComputerEdit(computer));
		}
		return listDTOComputerEdit;
	}
	
	/*
	public static List<Computer> convertToListComputer(List<DTOComputerEdit> listDTOComputerEdit) {
		List<Computer> listComputer = new ArrayList<>();
		for(DTOComputerEdit dtoComputerEdit : listDTOComputerEdit) {
			listComputer.add(convertToComputer(dtoComputerEdit));
		}
		return listComputer;
	}
	*/
	
	
	public static List<Computer> convertToListComputer(List<Object> listDTOObjects) {
		List<Computer> listComputer = new ArrayList<>();
		String stringObjectClassName = listDTOObjects.get(0).getClass().getSimpleName();
		
		
		switch (stringObjectClassName) {
		
		case ("DTOComputerAdd"):
			for(Object dtoObject : listDTOObjects) {
				listComputer.add(convertToComputer((DTOComputerAdd) dtoObject));
			}
		
			break;
		
			
		case ("DTOComputerDashboard"):
			for(Object dtoObject : listDTOObjects) {
				listComputer.add(convertToComputer((DTOComputerDashboard) dtoObject));
			}
		
			break;
			
			
		case ("DTOComputerEdit"):
			for(Object dtoObject : listDTOObjects) {
				listComputer.add(convertToComputer((DTOComputerEdit) dtoObject));
			}
		
			break;
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
 