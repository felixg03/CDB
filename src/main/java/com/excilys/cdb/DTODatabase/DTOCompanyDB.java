package com.excilys.cdb.DTODatabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity( name = "company" )
@Table( name = "company" )
public class DTOCompanyDB {
	
	@Id
	@Column( updatable = false, nullable = false )
	public Long id;
	
	@Column( nullable = true )
	public String name;
	
	
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
