package com.excilys.cdb.DTOs;

public class DTOComputerDashboard {
	public String id;
	public String name;
	public String introduced;
	public String discontinued;
	public String companyId;
	public String companyName;
	
	public String getId() {
		return this.id;
	}
	public String getName() { 
		return this.name;
	}
	public String getIntroduced() {
		return this.introduced;
	}
	public String getDiscontinued() {
		return this.discontinued;
	}
	public String getCompanyId() {
		return this.companyName;
	}
	public String getCompanyName() {
		return this.companyName;
	}
	
	public DTOComputerDashboard(String id, String name, String introduced, String discontinued, String companyId, String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}
}
