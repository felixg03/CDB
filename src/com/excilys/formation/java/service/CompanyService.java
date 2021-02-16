package com.excilys.formation.java.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.excilys.formation.java.model.Company;
import com.excilys.formation.java.database.DAOCompany;

public class CompanyService {
	
	private DAOCompany daoCompany = DAOCompany.getInstance();
	
	
	public List<Company> getListCompanies() {
		return daoCompany.requestListCompanies();
	}
}
