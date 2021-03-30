package com.excilys.cdb.mappers.DTORestMappers;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTORest.DTOCompanyRest;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;;

public class CompanyDTORestMapper {
	
	public static Company convertToCompany( DTOCompanyRest dtoCompanyRest ) {
		if ( dtoCompanyRest != null ) {
			return new CompanyBuilder().setId( parseToLong( dtoCompanyRest.id ) )
					   .setName( dtoCompanyRest.name )
					   .build();
		}
		else {
			return null;
		}
		
	}
	
	public static List<Company> convertToListCompany( List<DTOCompanyRest> listDtoCompanyRest ) {
		List<Company> listCompany = new ArrayList<>();
		listDtoCompanyRest.forEach( dto -> listCompany.add( convertToCompany( dto ) ) );
		return listCompany;
	}
	
	private static Long parseToLong( String input ) {
		if ( input == null ) {
			return null;
		}
		else {
			return Long.valueOf( input );
		}
	}
	
	
	
	
	
	
	public static DTOCompanyRest convertToDTOCompanyRest( Company company ) {
		if ( company != null ) {
			DTOCompanyRest dtoCompanyRest = new DTOCompanyRest();
			dtoCompanyRest.id = parseToString( company.getId() );
			dtoCompanyRest.name = company.getName();
			return dtoCompanyRest;
		}
		else {
			return null;
		}
	}
	
	
	public static List<DTOCompanyRest> convertToListDTOCompanyRest( List<Company> listCompany ) {
		List<DTOCompanyRest> listDtoCompanyRest = new ArrayList<>();
		listCompany.forEach( company -> listDtoCompanyRest.add( convertToDTOCompanyRest( company ) ) );
		return listDtoCompanyRest;
	}
	
	private static String parseToString( Long input ) {
		if ( input == null ) {
			return null;
		}
		else {
			return input.toString();
		}
	}
}
