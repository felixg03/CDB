package com.excilys.cdb.DTOs;

public class DTOComputerWithoutId {
	
	public final String name;
	public final String introduced;
	public final String discontinued;
	public final String company;
	
	
	
	public String getName() {
		return name;
	}



	public String getIntroduced() {
		return introduced;
	}



	public String getDiscontinued() {
		return discontinued;
	}



	public String getCompany() {
		return company;
	}



	public DTOComputerWithoutId(String name
							 , String introduced
							 , String discontinued
							 , String company) {
		
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
}
