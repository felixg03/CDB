package com.excilys.cdb.DTORest;

public class DTOComputerRest {
	
	public String id = null;
	public String name = null;
	public String introduced = null;
	public String discontinued = null;
	public DTOCompanyRest dtoCompanyRest = null;
	
	@Override
	public String toString() {
		return "DTOComputerRest id = " + id
			 + "\nname: " + name
			 + "\nintroduced: " + introduced
			 + "\ndiscontinued: " + discontinued
			 + "\n\n"
			 + dtoCompanyRest;
	}
}
