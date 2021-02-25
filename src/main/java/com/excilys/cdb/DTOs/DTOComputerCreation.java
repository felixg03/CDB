package com.excilys.cdb.DTOs;

import java.time.LocalDate;

import com.excilys.cdb.models.Computer;

public class DTOComputerCreation {
	
	private final String name;
	private final String introduced;
	private final String discontinued;
	private final String companyId;
	
	
	
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
