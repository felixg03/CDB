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
	public Long id;
	
	@Column( nullable = false )
	public String name;
	
	@Column( nullable = true )
	public LocalDate introduced;
	
	@Column( nullable = true )
	public LocalDate discontinued;
	
	@Column( name = "company_id", nullable = true )
	public Long companyId;
	
	
	
	public Long getId() {
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
	public Long getCompanyId() {
		return companyId;
	}
	
	
	
}
