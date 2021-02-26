package com.excilys.cdb.models;

public class Company {
	
	/* #####################################
	 * ####       						####
	 * ####			 BUILDER			####
	 * ####								####
	 * #####################################
	 */
	public static class CompanyBuilder {

		private long id;
		private String name;

		public CompanyBuilder() {

		}

		public CompanyBuilder setId(long id) {
			this.id = id;

			return this;
		}

		public CompanyBuilder setName(String name) {
			this.name = name;

			return this;
		}

		public Company build() {
			Company company = new Company();
			company.id = this.id;
			company.name = this.name;
			return company;
		}

	}
	
	
	
	/* #####################################
	 * ####       						####
	 * ####		COMPANY CLASS			####
	 * ####								####
	 * #####################################
	 */
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

	public Company() {}

	@Override
	public String toString() {
		return new String(this.id
					   + " | " 
					   + this.name
					   );
	}
	
}