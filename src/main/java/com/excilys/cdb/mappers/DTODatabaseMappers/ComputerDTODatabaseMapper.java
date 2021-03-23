package com.excilys.cdb.mappers.DTODatabaseMappers;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTODatabase.DTOComputerDB;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;
import com.excilys.cdb.models.CustomPage;

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
	
//	public static CustomPage<Computer> convertToPageComputer( CustomPage<DTOComputerDB> pageDTOComputerDB ) {
//		CustomPage<Computer> pageComputer = new CustomPage<>( pageDTOComputerDB.getSize(), pageDTOComputerDB.getNumber() );
//		pageComputer.setContent( convertToListComputer( pageDTOComputerDB.getContent() ) );
//		return pageComputer;
//	}
	
	
	
	
	
	
	
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
			dtoComputerDB.companyId = null;
		}
		
		return dtoComputerDB;
	}
	
	public static List<DTOComputerDB> convertToListDTOComputerDB( List<Computer> listComputer ) {
		List<DTOComputerDB> listDTOComputerDB = new ArrayList<>();
		listComputer.forEach( computer -> listDTOComputerDB.add( convertToDTOComputerDB( computer ) ) );
		return listDTOComputerDB;
	}
	
//	public static CustomPage<DTOComputerDB> convertToPageDTOComputerDB( CustomPage<Computer> pageComputer ) {
//		CustomPage<DTOComputerDB> pageDTOComputerDB = new CustomPage<>( pageComputer.getSize(), pageComputer.getNumber() );
//		pageDTOComputerDB.setContent( convertToListDTOComputerDB( pageComputer.getContent() ) );
//		return pageDTOComputerDB;
//	}
}
