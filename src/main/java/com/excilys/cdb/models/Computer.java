package com.excilys.cdb.models;

import java.time.LocalDate;

public class Computer {
	
	
	
	/* #####################################
	 * ####       						####
	 * ####			 BUILDER			####
	 * ####								####
	 * #####################################
	 */
	public static class ComputerBuilder {

		private long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;

		public ComputerBuilder() {

		}

		public ComputerBuilder setId(long id) {
			this.id = id;

			return this;
		}

		public ComputerBuilder setName(String name) {
			this.name = name;

			return this;
		}

		public ComputerBuilder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			
			return this;

		}

		public ComputerBuilder setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			
			return this;
		}
		
		public ComputerBuilder setCompany(Company company) {
			this.company = company;

			return this;
		}

		public Computer build() {
			Computer computer = new Computer();
			computer.id = this.id;
			computer.company = this.company;
			computer.name = this.name;
			computer.introduced = this.introduced;
			computer.discontinued = this.discontinued;
			return computer;
		}

	}
	
	
	
	/* #####################################
	 * ####       						####
	 * ####		COMPUTER CLASS			####
	 * ####								####
	 * #####################################
	 */
	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;
	
	// GETTERS AND SETTERS
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
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
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
					   + this.company
					   );
	}
}
