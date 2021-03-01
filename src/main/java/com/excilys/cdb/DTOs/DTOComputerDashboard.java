package com.excilys.cdb.DTOs;

public class DTOComputerDashboard {
	public String name;
	public String introduced;
	public String discontinued;
	public String companyName;
	
	public String getName() {
		return name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public String getCompanyName() {
		return companyName;
	}
	
	public DTOComputerDashboard(String name, String introduced, String discontinued, String companyName) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyName = companyName;
	}
}
