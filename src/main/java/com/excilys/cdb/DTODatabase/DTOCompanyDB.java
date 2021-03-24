package com.excilys.cdb.DTODatabase;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity( name = "dtoCompanyDB" )
@Table( name = "company" )
public class DTOCompanyDB {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Long id;
	public String name;
	
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	
	@Override
	public String toString() {
		String idString = null;
		if ( id != null ) {
			idString = id.toString();
		}
		
		return idString + " | " + name;
	}
}
