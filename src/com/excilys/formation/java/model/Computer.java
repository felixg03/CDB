package com.excilys.formation.java.model;

import java.time.LocalDate;

public class Computer {
	
	private static int counter;
	
	private long id;
	private String name;
	private LocalDate introduced = null;
	private LocalDate discontinued = null;
	private long company_id;
	
	// GETTERS AND SETTERS
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		Computer.counter = counter;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued)
				throws IllegalArgumentException 
	{
		if (this.discontinued.compareTo(this.introduced) > 0) {
			this.discontinued = discontinued;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	
	
	
	
	
	
}
