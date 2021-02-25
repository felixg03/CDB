package com.excilys.cdb.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DTOs.DTOCompany;
import com.excilys.cdb.models.Company;

// Follows singleton pattern
public final class DAOCompany {
	
	// GENERAL ATTRIBUTES
	private static DAOCompany instance;
	private DBConnection databaseConnection = DBConnection.getInstance();
	private ResultSet resultSet;
	private PreparedStatement query;
	private Connection connection;
	
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
 	
 	
	public ResultSet getResultSet() {
		return resultSet;
	}
	
	
	// METHODS
	public List<Company> requestListCompanies(int offset) {
		this.databaseConnection.openConnection();
		List<Company> listCompanies = new ArrayList<Company>();
		
		try {
			this.connection = databaseConnection.getConnection();
			this.query = connection.prepareStatement(
										QUERY_LIST_10_COMPANIES
						 		   );
			
			this.query.setInt(1, offset);
			this.resultSet = this.query.executeQuery();
			listCompanies = this.getListCompaniesFromResultSet();
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			this.databaseConnection.closeConnection();
		}
		
		return listCompanies;
	}
	
	
	
	
	public List<Company> requestListCompanies() {
		this.databaseConnection.openConnection();
		List<Company> listCompanies = new ArrayList<>();
		
		try {
			this.connection = databaseConnection.getConnection();
			this.query = connection.prepareStatement(
						 			  QUERY_LIST_COMPANIES
						 		   );
			this.resultSet = this.query.executeQuery();
			listCompanies = this.getListCompaniesFromResultSet();
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			this.databaseConnection.closeConnection();
		}
		
		return listCompanies;
	}
	
	
	
	// TOOLS
	private List<Company> getListCompaniesFromResultSet() throws SQLException {
		List<Company> listCompaniesToReturn = new ArrayList<>();
		
		while(this.resultSet.next()) {
			 long id = this.resultSet.getLong(1);
			 String name = this.resultSet.getString(2);
			 
			 Company company = new Company(id, name);
			 listCompaniesToReturn.add(company);
		}
		
		return listCompaniesToReturn;
	}
}
