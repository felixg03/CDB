package com.excilys.cdb.DAOImpl.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;

public class CompanyRowMapper implements RowMapper<Company> {
	
	@Override
	public Company mapRow( ResultSet resultSet, int rowNum ) throws SQLException {
		return new CompanyBuilder().setId(resultSet.getLong("id"))
								   .setName(resultSet.getString("name"))
								   .build();
	}
}
