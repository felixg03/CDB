package com.excilys.cdb.DTOView;

public final class DTOComputerAddView {
	
	public String name = null;
	public String introduced = null;
	public String discontinued = null;
	public String companyId = null;
	
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
	
	@Override
	public String toString() {
		return name + " | " + introduced + " | " + discontinued + " | " + companyId;
	}
}
