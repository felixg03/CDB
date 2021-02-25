package com.excilys.cdb.DTOs.mappers;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTOs.DTOCompany;
import com.excilys.cdb.models.Company;

public class CompanyDTOMapper {
	
	
	public static Company convertDTOCompanyToCompany(DTOCompany dtoCompany) {
		return new Company(Long.valueOf(dtoCompany.id), dtoCompany.name);
	}
	
	
	public static List<Company> convertListDTOCompanyToListCompany(List<DTOCompany> listDTOCompany) {
		List<Company> listCompanyToReturn = new ArrayList<>();
		
		for (DTOCompany dtoCompany : listDTOCompany) {
			listCompanyToReturn.add(convertDTOCompanyToCompany(dtoCompany));
		}
		return listCompanyToReturn;
	}
	
	
	
	
	public static DTOCompany convertCompanyToDTOCompany(Company company) {
		return new DTOCompany(String.valueOf(company.getId()), company.getName());
	}
	
	
	public static List<DTOCompany> convertListCompanyToListDTOCompany(List<Company> listCompany) {
		List<DTOCompany> listDTOCompanyToReturn = new ArrayList<>();
		
		for (Company company : listCompany) {
			listDTOCompanyToReturn.add(convertCompanyToDTOCompany(company));
		}
		return listDTOCompanyToReturn;
	}
}
