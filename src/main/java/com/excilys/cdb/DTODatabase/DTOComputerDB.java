package com.excilys.cdb.DTODatabase;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity( name = "dtoComputerDB" )
@Table( name = "computer" )
public class DTOComputerDB {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( updatable = false, nullable = false )
	@Basic( optional = false )
	public Long id;
	
	@Column( nullable = false )
	@Basic( optional = false )
	public String name;
	
	
	public LocalDate introduced;
	public LocalDate discontinued;
	
	@Basic( optional = true )
	@JoinColumn( name = "company_id", nullable = true )
	@ManyToOne
	public DTOCompanyDB dtoCompanyDB;
	
	
	
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
	public DTOCompanyDB getDtoCompanyDB() {
		return dtoCompanyDB;
	}
	
	
	
	public String getDTOCompanyDBToString() {
		if ( dtoCompanyDB == null ) {
			return null;
		}
		else {
			return dtoCompanyDB.toString();
		}
	}
	
	@Override
	public String toString() {
		String idString = null;
		String introducedString = null;
		String discontinuedString = null;
		
		if (id != null) {
			idString = id.toString();
		}
		if (introduced != null) {
			introducedString = introduced.toString();
		}
		if (discontinued != null) {
			discontinuedString = discontinued.toString();
		}
		
		return idString + " | " + name + " | " + introducedString + " | " + discontinuedString + " | " + getDTOCompanyDBToString();
	}
	
}
