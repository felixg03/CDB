package com.excilys.cdb.service;

public class Model {
	
	private ComputerService computerService = new ComputerService();
	private CompanyService companyService = new CompanyService();
	
	public ComputerService getComputerService() {
		return computerService;
	}
	public CompanyService getCompanyService() {
		return companyService;
	}
}