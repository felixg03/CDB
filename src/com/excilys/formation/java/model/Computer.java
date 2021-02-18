package com.excilys.formation.java.model;

import java.time.LocalDate;

public class Computer {
	
	private static int counter;
	
	private long id = 0;
	private String name = null;
	private LocalDate introduced = null;
	private LocalDate discontinued = null;
	private long companyId = 0;
	
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
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	@Override
	public String toString() {
		return new String(this.id
					   + " | " 
					   + this.name
					   + " | " 
					   + this.introduced
					   + " | " 
					   + this.discontinued
					   + " | " 
					   + this.companyId
					   );
	}
}
