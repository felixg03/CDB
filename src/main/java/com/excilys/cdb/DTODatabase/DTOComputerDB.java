package com.excilys.cdb.DTODatabase;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity( name = "computer" )
@Table( name = "computer" )
public class DTOComputerDB {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	@Column( updatable = false, nullable = false )
	public long id;
	public String name;
	public LocalDate introduced;
	public LocalDate discontinued;
	
	@ManyToOne
	@Column( name = "company_id" )
	public long companyId;
	
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public long getCompanyId() {
		return companyId;
	}
	
	
	
}
