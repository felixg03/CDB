package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.database.DAOCompany;
import com.excilys.cdb.models.Company;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public final class CompanyService {
	
	@Autowired
	private DAOCompany daoCompany;
	
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
