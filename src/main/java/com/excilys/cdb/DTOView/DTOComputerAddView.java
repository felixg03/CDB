package com.excilys.cdb.DTOView;

public final class DTOComputerAddView {
	
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
	
	@Override
	public String toString() {
		return new String(name + " | " + introduced + " | " + discontinued + " | " + companyId);
	}
}
