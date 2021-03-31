package com.excilys.cdb.models;

public class Company {
	
	/* #####################################
	 * ####       						####
	 * ####			 BUILDER			####
	 * ####								####
	 * #####################################
	 */
	public static class CompanyBuilder {

		private Long id;
		private String name;

		public CompanyBuilder() {

		}

		public CompanyBuilder setId(Long id) {
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
	

	private Company() {}
	
	private Long id;
	private String name;
	
	// GETTERS AND SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
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