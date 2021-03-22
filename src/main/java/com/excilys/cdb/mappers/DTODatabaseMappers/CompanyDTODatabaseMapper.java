package com.excilys.cdb.mappers.DTODatabaseMappers;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTODatabase.DTOCompanyDB;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;

public class CompanyDTODatabaseMapper {
	
	public static Company convertToCompany( DTOCompanyDB dtoCompanyDB ) {
		return new CompanyBuilder().setId(dtoCompanyDB.id)
								   .setName(dtoCompanyDB.name)
								   .build();
	}
	
	public static List<Company> convertToListCompany ( List<DTOCompanyDB> listDTOCompanyDB ) {
		List<Company> listCompany = new ArrayList<>();
		listDTOCompanyDB.forEach( dtoCompanyDB -> listCompany.add( convertToCompany( dtoCompanyDB ) ) );
		return listCompany;
	}
	
	public static DTOCompanyDB convertToDTOCompanyDB( Company company ) {
		DTOCompanyDB dtoCompanyDB = new DTOCompanyDB();
		dtoCompanyDB.id = company.getId();
		dtoCompanyDB.name = company.getName();
		return dtoCompanyDB;
	}
	
	public static List<DTOCompanyDB> convertToListDTOCompanyDB ( List<Company> listCompany ) {
		List<DTOCompanyDB> listDTOCompanyDB = new ArrayList<>();
		listCompany.forEach( company -> listDTOCompanyDB.add( convertToDTOCompanyDB( company ) ) );
		return listDTOCompanyDB;
	}
}
