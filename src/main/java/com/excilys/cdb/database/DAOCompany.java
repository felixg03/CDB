package com.excilys.cdb.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;

// Follows singleton pattern
public final class DAOCompany {
	
	private static DAOCompany instance;
	private DBConnection databaseConnection = DBConnection
											.getInstance();
	private ResultSet resultSet;
	private static final String QUERY_LIST_COMPANIES = 
    "SELECT id, name FROM company ORDER BY id LIMIT 10 OFFSET ?"; 
	
 	public static DAOCompany getInstance() {
		if (instance == null) {
			instance = new DAOCompany();
		}
		return instance;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	
	public List<Company> requestListCompanies(int offset) {
		databaseConnection.openConnection();
		List<Company> listCompanies = new ArrayList<Company>();
		
		try (Connection connection = databaseConnection.getConnection();
			 PreparedStatement query = connection
						 			   .prepareStatement(
						 					  QUERY_LIST_COMPANIES
						 			   );
			)
		{
			
			query.setInt(1, offset);
			this.resultSet = query.executeQuery();
			listCompanies = this
							.getListCompaniesFromResultSet(
									this.resultSet
							);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return listCompanies;
	}
	
	public List<Company> getListCompaniesFromResultSet(ResultSet resultSetArgument) throws SQLException {
		List<Company> listCompaniesToReturn = new ArrayList<Company>();
		
		while(resultSetArgument.next()) {
			 long id = resultSetArgument.getLong(1);
			 String name = resultSetArgument.getString(2);
			 
			 Company company = new Company(id, name);
			 listCompaniesToReturn.add(company);
		}
		
		return listCompaniesToReturn;
	}
}
