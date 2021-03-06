package com.excilys.cdb.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTOs.DTOCompany;
import com.excilys.cdb.database.DAOCompany;
import com.excilys.cdb.models.Company;

// Singleton pattern
public final class CompanyService {
	
	private static CompanyService instance;
	private DAOCompany daoCompany = DAOCompany.getInstance();
	
	public static CompanyService getInstance() {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}
	
	public List<Company> getListCompanies(int offset) {
		return daoCompany.requestListCompanies(offset);
	}
	
	
	public List<Company> getListCompanies() {
		return daoCompany.requestListCompanies();
	}
	
	public void callCompanyDeletion( long companyId ) {
		daoCompany.requestCompanyDeletion( companyId );
	}
}
