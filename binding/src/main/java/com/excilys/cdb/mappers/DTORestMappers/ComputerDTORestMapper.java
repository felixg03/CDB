package com.excilys.cdb.mappers.DTORestMappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTORest.DTOComputerRest;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;

public class ComputerDTORestMapper {
	
	
	private static DateTimeFormatter dateTimeFormatter = 
			DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
	
	
	
	
	
	public static Computer convertToComputer( DTOComputerRest dtoComputerRest ) {
		if ( dtoComputerRest != null ) {
			return new ComputerBuilder().setId( parseToLong( dtoComputerRest.id ) )
					.setName( dtoComputerRest.name )
					.setIntroduced( parseStringToLocalDate( dtoComputerRest.introduced ) )
					.setDiscontinued( parseStringToLocalDate( dtoComputerRest.discontinued ) )
					.setCompany( CompanyDTORestMapper.convertToCompany( dtoComputerRest.dtoCompanyRest ) )
					.build();
		}
		else {
			return null;
		}
	}
	
	
	
	public static List<Computer> convertToListComputer( List<DTOComputerRest> listDTOComputerRest ) {
		List<Computer> listComputer = new ArrayList<>();
		listDTOComputerRest.forEach( dtoComputerRest -> listComputer.add( convertToComputer( dtoComputerRest ) ) );
		return listComputer;
	}
	
	
	private static Long parseToLong( String input ) {
		if ( input == null ) {
			return null;
		}
		else {
			return Long.valueOf( input );
		}
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
	
	
	
	
	
	
	
	
	
	public static DTOComputerRest convertToDTOComputerRest( Computer computer ) {
		if ( computer != null ) {
			DTOComputerRest dtoComputerRest = new DTOComputerRest();
			dtoComputerRest.id = parseToString( computer.getId() );
			dtoComputerRest.name = computer.getName();
			dtoComputerRest.introduced = parseLocalDateToString( computer.getIntroduced() );
			dtoComputerRest.discontinued = parseLocalDateToString( computer.getDiscontinued() );
			dtoComputerRest.dtoCompanyRest = CompanyDTORestMapper.convertToDTOCompanyRest( computer.getCompany() );
			return dtoComputerRest;
		}
		else {
			return null;
		}
	}
	
	
	
	public static List<DTOComputerRest> convertToListDTOComputerRest( List<Computer> listComputer ) {
		List<DTOComputerRest> listDtoComputerRest = new ArrayList<>();
		listComputer.forEach( computer -> listDtoComputerRest.add( convertToDTOComputerRest( computer ) ) );
		return listDtoComputerRest;
	}
	
	private static String parseToString( Long input ) {
		if ( input == null ) {
			return null;
		}
		else {
			return input.toString();
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
}
