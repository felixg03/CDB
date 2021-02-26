package com.excilys.cdb.DTOs;

public class DTOComputerCreation {
	
	public final String name;
	public final String introduced;
	public final String discontinued;
	public final String companyId;
	
	
	
	public String getName() {
		return name;
	}



	public String getIntroduced() {
		return introduced;
	}



	public String getDiscontinued() {
		return discontinued;
	}



	public String getCompanyId() {
		return companyId;
	}



	public DTOComputerCreation(String name
							 , String introduced
							 , String discontinued
							 , String companyId) {
		
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
}
