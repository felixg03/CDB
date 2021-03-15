package com.excilys.cdb.DTOs.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTOs.DTOComputerAdd;
import com.excilys.cdb.DTOs.DTOComputerDashboard;
import com.excilys.cdb.DTOs.DTOComputerEdit;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;

public class ComputerDTOMapper {
	
	private static DateTimeFormatter dateTimeFormatter = 
	DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
	
	
	
	/*
	 * ##########################
	 * ###					  ###
	 * ###   DTOComputerAdd   ###
	 * ###					  ###
	 * ##########################
	 */
	public static DTOComputerAdd convertToDTOComputerAddComputer( Computer computer ) {
		String name = computer.getName();
		String introduced = parseLocalDateToString( computer
											       .getIntroduced() );
		String discontinued = parseLocalDateToString( computer
				 							         .getDiscontinued() );
		String companyId = String.valueOf( computer.getCompany().getId() );
		
		DTOComputerAdd dtoComputerAdd = new DTOComputerAdd();
		
		dtoComputerAdd.name = name;
		dtoComputerAdd.introduced = introduced;
		dtoComputerAdd.discontinued = discontinued;
		dtoComputerAdd.companyId = companyId;
		
		return dtoComputerAdd;
	}
	
	public static Computer convertToComputer(DTOComputerAdd dtoComputerAdd) {
		
		Computer computer = null;
		
		String name = dtoComputerAdd.name;
		LocalDate introduced = parseStringToLocalDate( dtoComputerAdd
													  .introduced );
		LocalDate discontinued = parseStringToLocalDate( dtoComputerAdd
				 									    .discontinued );
		String companyIdString = dtoComputerAdd.companyId;
		
		
		if (companyIdString != null) {
			
			Company company = new CompanyBuilder().setId( Long.valueOf( companyIdString ) )
					  							  .build();
			computer =  new ComputerBuilder().setName( name )
											 .setIntroduced( introduced )
											 .setDiscontinued( discontinued )
											 .setCompany( company )
											 .build();
		}
		else {
			computer = new ComputerBuilder().setName( name )
											.setIntroduced( introduced )
											.setDiscontinued( discontinued )
											.build();
		}
		
		return computer;
	}
	
	public static List<DTOComputerAdd> convertToListDTOComputerAdd( List<Computer> listComputer ) {
		List<DTOComputerAdd> listDTOComputerAddComputer = new ArrayList<>();
		for ( Computer computer : listComputer ) {
			listDTOComputerAddComputer.add( convertToDTOComputerAddComputer( computer ) );
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
	
	public static DTOComputerDashboard convertToDTOComputerDashbord( Computer computer ) {
		String id = String.valueOf( computer.getId() );
		String name = computer.getName();
		String introduced = parseLocalDateToString( computer
											       .getIntroduced() );
		String discontinued = parseLocalDateToString( computer
				 							         .getDiscontinued() );
		String companyName = computer.getCompany().getName();
		
		
		DTOComputerDashboard dtoComputerDashboard = new DTOComputerDashboard();
		
		dtoComputerDashboard.id = id;
		dtoComputerDashboard.name = name;
		dtoComputerDashboard.introduced = introduced;
		dtoComputerDashboard.discontinued = discontinued;
		dtoComputerDashboard.companyName = companyName;
		
		return dtoComputerDashboard;
	}
	
	
	public static Computer convertToComputer( DTOComputerDashboard dtoComputerDashboard ) {
		
		long id = Long.valueOf( dtoComputerDashboard.id );
		String name = dtoComputerDashboard.name;
		LocalDate introduced = parseStringToLocalDate( dtoComputerDashboard
													  .introduced );
		LocalDate discontinued = parseStringToLocalDate( dtoComputerDashboard
				 									    .discontinued );
		String companyName = dtoComputerDashboard.companyName;
		
		
		
		Company company = new CompanyBuilder().setName( companyName )
											  .build();
		
		return new ComputerBuilder().setId( id )
									.setName( name )
									.setIntroduced( introduced )
									.setDiscontinued( discontinued )
									.setCompany( company )
									.build();
	}

	
	public static List<DTOComputerDashboard> convertToListDTOComputerDashboard( List<Computer> listComputer ) {
		List<DTOComputerDashboard> listDTOComputerDashboard = new ArrayList<>();
		for ( Computer computer : listComputer ) {
			listDTOComputerDashboard.add( convertToDTOComputerDashbord( computer ) );
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
	
	
	public static DTOComputerEdit convertToDTOComputerEdit( Computer computer ) {
		
		DTOComputerEdit dtoComputerEdit = new DTOComputerEdit();
		
		dtoComputerEdit.id = String.valueOf( computer.getId() );
		dtoComputerEdit.name = computer.getName();
		dtoComputerEdit.introduced = parseLocalDateToString( computer
			       											.getIntroduced() );
		dtoComputerEdit.discontinued = parseLocalDateToString( computer
		         											  .getDiscontinued() );
		putCompanyIdIntoDTOComputerEdit( dtoComputerEdit, computer );
		
		return dtoComputerEdit;
	}
	
	
	public static Computer convertToComputer( DTOComputerEdit dtoComputerEdit ) {
		
		Computer computer = null;
		
		long id = Long.valueOf( dtoComputerEdit.id );
		String name = dtoComputerEdit.name;
		LocalDate introduced = parseStringToLocalDate( dtoComputerEdit
													  .introduced );
		LocalDate discontinued = parseStringToLocalDate( dtoComputerEdit
				 									    .discontinued );
		String companyIdString = dtoComputerEdit.companyId;
		
		if ( companyIdString != null && companyIdString != "" ) {
			
			Company company = new CompanyBuilder().setId( Long.valueOf( companyIdString ) )
					  							  .build();
			computer =  new ComputerBuilder().setId(id)
											 .setName( name )
											 .setIntroduced( introduced )
											 .setDiscontinued( discontinued )
											 .setCompany( company )
											 .build();
		}
		else {
			computer = new ComputerBuilder().setId(id)
											.setName( name )
											.setIntroduced( introduced )
											.setDiscontinued( discontinued )
											.build();
		}
		
		return computer;
	}
	
	

	public static List<DTOComputerEdit> convertToListDTOComputerEdit( List<Computer> listComputer ) {
		List<DTOComputerEdit> listDTOComputerEdit = new ArrayList<>();
		for ( Computer computer : listComputer ) {
			listDTOComputerEdit.add( convertToDTOComputerEdit( computer ) );
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
	
	
	public static List<Computer> convertToListComputer ( List<Object> listDTOObjects ) {
		List<Computer> listComputer = new ArrayList<>();
		String stringObjectClassName = listDTOObjects.get(0).getClass().getSimpleName();
		
		
		switch (stringObjectClassName) {
		
		case ( "DTOComputerAdd" ):
			for ( Object dtoObject : listDTOObjects ) {
				listComputer.add( convertToComputer( (DTOComputerAdd) dtoObject ) );
			}
		
			break;
		
			
		case ( "DTOComputerDashboard" ):
			for ( Object dtoObject : listDTOObjects ) {
				listComputer.add( convertToComputer( (DTOComputerDashboard) dtoObject ) );
			}
		
			break;
			
			
		case ( "DTOComputerEdit" ):
			for ( Object dtoObject : listDTOObjects ) {
				listComputer.add( convertToComputer( (DTOComputerEdit) dtoObject ) );
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
	
	private static void putCompanyIdIntoDTOComputerEdit( DTOComputerEdit dtoComputerEdit, Computer computer ) {
		if ( computer.getCompany() == null ) {
			dtoComputerEdit.companyId = null;
		}
		else {
			dtoComputerEdit.companyId = String.valueOf( computer.getCompany().getId() );
		}
	}
}
 