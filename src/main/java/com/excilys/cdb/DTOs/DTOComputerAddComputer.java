package com.excilys.cdb.DTOs;

public final class DTOComputerAddComputer {
	
	public String name;
	public String introduced;
	public String discontinued;
	public String companyId;
	
	
	
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



	public DTOComputerAddComputer(String name
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
