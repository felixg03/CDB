package com.excilys.cdb.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;;

// Follows singleton pattern
public final class DAOCompany {
	
	// GENERAL ATTRIBUTES
	private static DAOCompany instance;
	private DBConnection databaseConnection = DBConnection.getInstance(); 
	
	// STRING QUERIES
	private static final String QUERY_LIST_10_COMPANIES = 
    "SELECT id, name FROM company ORDER BY id LIMIT 10 OFFSET ?"; 
	private static final String QUERY_LIST_COMPANIES =
	"SELECT id, name FROM company ORDER BY id";
	
	
	// GETTERS AND SETTERS
 	public static DAOCompany getInstance() {
		if (instance == null) {
			instance = new DAOCompany();
		}
		return instance;
	}
	
	
	// METHODS
	public List<Company> requestListCompanies(int offset) {
		
		List<Company> listCompanies = new ArrayList<Company>();
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
																QUERY_LIST_10_COMPANIES
						 		   							 );
			preparedStatement.setInt(1, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			listCompanies = this.getListCompaniesFromResultSet(resultSet);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return listCompanies;
	}
	
	
	
	
	public List<Company> requestListCompanies() {
		
		List<Company> listCompanies = new ArrayList<>();
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
												 			  QUERY_LIST_COMPANIES
												 		    );
			ResultSet resultSet = preparedStatement.executeQuery();
			listCompanies = this.getListCompaniesFromResultSet(resultSet);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return listCompanies;
	}
	
	
	
	// TOOLS
	private List<Company> getListCompaniesFromResultSet(ResultSet resultSet) throws SQLException {
		
		List<Company> listCompaniesToReturn = new ArrayList<>();
		
		while(resultSet.next()) {
			 long id = resultSet.getLong(1);
			 String name = resultSet.getString(2);
			 
			 Company company = new CompanyBuilder().setId(id)
					 							   .setName(name)
					 							   .build();
			 listCompaniesToReturn.add(company);
		}
		
		return listCompaniesToReturn;
	}
}
