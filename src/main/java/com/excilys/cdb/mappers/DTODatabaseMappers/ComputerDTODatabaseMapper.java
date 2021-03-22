package com.excilys.cdb.mappers.DTODatabaseMappers;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTODatabase.DTOComputerDB;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;

public class ComputerDTODatabaseMapper {
	
	public static Computer convertToComputer( DTOComputerDB dtoComputerDB ) {
		
		Company company = new CompanyBuilder().setId( dtoComputerDB.id ).build();
		
		return new ComputerBuilder().setId( dtoComputerDB.id )
									.setName( dtoComputerDB.name )
									.setIntroduced( dtoComputerDB.introduced )
									.setDiscontinued( dtoComputerDB.discontinued )
									.setCompany( company )
									.build();
	}
	
	public static List<Computer> convertToListComputer( List<DTOComputerDB> listDTOComputerDB ) {
		List<Computer> listComputer = new ArrayList<>();
		listDTOComputerDB.forEach( dtoComputerDB -> listComputer.add( convertToComputer( dtoComputerDB ) ) );
		return listComputer;
	}
	
	
	
	public static DTOComputerDB convertToDTOComputerDB( Computer computer ) {
		DTOComputerDB dtoComputerDB = new DTOComputerDB();
		dtoComputerDB.id = computer.getId();
		dtoComputerDB.name = computer.getName();
		dtoComputerDB.introduced = computer.getIntroduced();
		dtoComputerDB.discontinued = computer.getDiscontinued();
		Company company = computer.getCompany();
		
		if ( company != null ) {
			dtoComputerDB.companyId = company.getId();
		}
		else {
			dtoComputerDB.companyId = 0;
		}
		
		return dtoComputerDB;
	}
	
	public static List<DTOComputerDB> convertToListDTOComputerDB( List<Computer> listComputer ) {
		List<DTOComputerDB> listDTOComputerDB = new ArrayList<>();
		listComputer.forEach( computer -> listDTOComputerDB.add( convertToDTOComputerDB( computer ) ) );
		return listDTOComputerDB;
	}
}
