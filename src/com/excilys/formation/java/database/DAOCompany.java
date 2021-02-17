package com.excilys.formation.java.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import com.excilys.formation.java.model.Company;

// Follows singleton pattern
public final class DAOCompany {
	
	private static DAOCompany instance;
	private DBConnection databaseConnection = DBConnection.getInstance();
	private ResultSet resultSet;
	
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
		Connection connection = databaseConnection.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement(
										"SELECT * FROM company ORDER BY id LIMIT 10 OFFSET ?"
										);
			query.setInt(1, offset);
			
			this.resultSet = query.executeQuery();
			
			
			List<Company> listCompanies = new ArrayList<Company>();
			
			while(this.resultSet.next()) {
				 long id = this.resultSet.getLong(1);
				 String name = this.resultSet.getString(2);
				 
				 Company company = new Company(id, name);
				 listCompanies.add(company);
			}
			
			 return listCompanies;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			databaseConnection.closeConnection();
		}
		return null;  // Not very clean but dunno what else to do
	}
}
