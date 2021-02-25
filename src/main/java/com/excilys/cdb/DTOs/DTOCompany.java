package com.excilys.cdb.DTOs;

import java.time.LocalDate;

public class DTOCompany {
	public final String id;
	public final String name;
	
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public DTOCompany(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
