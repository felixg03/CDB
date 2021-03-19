package com.excilys.cdb.DAOImpl.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;;

public class ComputerRowMapper implements RowMapper<Computer> {
	
	@Override
	public Computer mapRow( ResultSet resultSet, int rowNum ) throws SQLException {
		Company company = new CompanyBuilder().setId( resultSet.getLong( "company.id" ) )
											  .setName( resultSet.getString( "company.name" ) )
											  .build();
		return new ComputerBuilder().setId( resultSet.getLong( "computer.id" ) )
									.setName( resultSet.getString( "computer.name" ) )
									.setIntroduced( parseDateIntoLocalDate( resultSet.getDate( "computer.introduced" ) ) )
									.setDiscontinued( parseDateIntoLocalDate( resultSet.getDate( "computer.discontinued") ) )
									.setCompany( company )
									.build();
	}
	
	private LocalDate parseDateIntoLocalDate( Date date ) throws SQLException {
		LocalDate localDate = null;
		if ( date != null ) {
			localDate = date.toLocalDate();
		}
		return localDate;
	}

}
