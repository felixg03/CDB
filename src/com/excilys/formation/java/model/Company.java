package com.excilys.formation.java.model;

import java.security.Timestamp;

public class Company {
	
	private long id;
	private String name;
	
	// GETTERS AND SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return new String(this.id
					   + " | " 
					   + this.name
					   );
	}
	
}