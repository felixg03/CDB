package com.excilys.cdb.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.database.DAOCompany;

public class CompanyService {
	
	private DAOCompany daoCompany = DAOCompany.getInstance();
	
	
	public List<Company> getListCompanies(int offset) {
		return daoCompany.requestListCompanies(offset);
	}
}
