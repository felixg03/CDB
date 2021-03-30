package com.excilys.cdb.mappers.DTODatabaseMappers;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTOs.DTODatabase.DTOComputerDB;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;

public class ComputerDTODatabaseMapper {
	
	
	
	public static Computer convertToComputer( DTOComputerDB dtoComputerDB ) {
		
		
		return new ComputerBuilder().setId( dtoComputerDB.id )
									.setName( dtoComputerDB.name )
									.setIntroduced( dtoComputerDB.introduced )
									.setDiscontinued( dtoComputerDB.discontinued )
									.setCompany( getCompanyFromDTOComputerDB( dtoComputerDB ) )
									.build();
	}
	
	
	
	public static List<Computer> convertToListComputer( List<DTOComputerDB> listDTOComputerDB ) {
		List<Computer> listComputer = new ArrayList<>();
		listDTOComputerDB.forEach( dtoComputerDB -> listComputer.add( convertToComputer( dtoComputerDB ) ) );
		return listComputer;
	}
	
	
	
	private static Company getCompanyFromDTOComputerDB( DTOComputerDB dtoComputerDB ) {
		if ( dtoComputerDB.dtoCompanyDB == null ) {
			return null;
		}
		else {
			return CompanyDTODatabaseMapper.convertToCompany( dtoComputerDB.dtoCompanyDB );
		}
	}
	
	
	
	
	public static DTOComputerDB convertToDTOComputerDB( Computer computer ) {
		DTOComputerDB dtoComputerDB = new DTOComputerDB();
		dtoComputerDB.id = computer.getId();
		dtoComputerDB.name = computer.getName();
		dtoComputerDB.introduced = computer.getIntroduced();
		dtoComputerDB.discontinued = computer.getDiscontinued();
		dtoComputerDB.dtoCompanyDB = CompanyDTODatabaseMapper.convertToDTOCompanyDB( computer.getCompany() );
		
		return dtoComputerDB;
	}
	
	public static List<DTOComputerDB> convertToListDTOComputerDB( List<Computer> listComputer ) {
		List<DTOComputerDB> listDTOComputerDB = new ArrayList<>();
		listComputer.forEach( computer -> listDTOComputerDB.add( convertToDTOComputerDB( computer ) ) );
		return listDTOComputerDB;
	}
	
}
