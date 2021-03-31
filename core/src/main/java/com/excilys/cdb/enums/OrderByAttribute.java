package com.excilys.cdb.enums;

public enum OrderByAttribute {
	
	ID("id"), 
	NAME("name"), 
	INTRODUCED("introduced"), 
	DISCONTINUED("discontinued"), 
	COMPANY_ID("dtoCompanyDB.id"), 
	COMPANY_NAME("dtoCompanyDB.name");
	
	private String orderByAttributeString;
	
	OrderByAttribute(String orderByAttributeString) {
		this.orderByAttributeString = orderByAttributeString;
	}
	
	public String getOrderByAttributeString() {
		return this.orderByAttributeString;
	}
}