package com.excilys.cdb.mappers.DTOViewMappers;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTOs.DTOView.DTOCompanyView;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;;

public class CompanyDTOViewMapper {
	
	
	public static Company convertDTOCompanyToCompany(DTOCompanyView dtoCompany) {
		return new CompanyBuilder().setId(Long.valueOf(
											dtoCompany.id))
								   .setName(dtoCompany.name)
								   .build();
	}
	
	
	public static List<Company> convertListDTOCompanyToListCompany(List<DTOCompanyView> listDTOCompany) {
		List<Company> listCompanyToReturn = new ArrayList<>();
		
		for (DTOCompanyView dtoCompany : listDTOCompany) {
			listCompanyToReturn.add(convertDTOCompanyToCompany(dtoCompany));
		}
		return listCompanyToReturn;
	}
	
	
	
	
	public static DTOCompanyView convertCompanyToDTOCompany(Company company) {
		DTOCompanyView dtoCompany = new DTOCompanyView();
		dtoCompany.id = String.valueOf(company.getId());
		dtoCompany.name = company.getName();
		return dtoCompany;
	}
	
	
	public static List<DTOCompanyView> convertListCompanyToListDTOCompany(List<Company> listCompany) {
		List<DTOCompanyView> listDTOCompanyToReturn = new ArrayList<>();
		
		for (Company company : listCompany) {
			listDTOCompanyToReturn.add(convertCompanyToDTOCompany(company));
		}
		return listDTOCompanyToReturn;
	}
}
