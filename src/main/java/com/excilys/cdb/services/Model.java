package com.excilys.cdb.services;

public final class Model {
	private static Model instance;
	private ComputerService computerService = new ComputerService();
	private CompanyService companyService = new CompanyService();
	
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}
	
	public ComputerService getComputerService() {
		return computerService;
	}
	public CompanyService getCompanyService() {
		return companyService;
	}
}
