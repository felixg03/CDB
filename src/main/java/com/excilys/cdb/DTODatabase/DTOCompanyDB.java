package com.excilys.cdb.DTODatabase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity( name = "company" )
@Table( name = "company" )
public class DTOCompanyDB {
	
	@Id
	public long id;
	public String name;
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
