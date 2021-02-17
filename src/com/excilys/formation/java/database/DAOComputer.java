package com.excilys.formation.java.database;

import com.excilys.formation.java.model.Company;
import com.excilys.formation.java.model.Computer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Follows singleton pattern
public final class DAOComputer {
	
	private static DAOComputer instance;
	private DBConnection databaseConnection = DBConnection.getInstance();
	private ResultSet resultSet;

	public static DAOComputer getInstance() {
		if (instance == null) {
			instance = new DAOComputer();
		}
		return instance;
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	
	
	public List<Computer> requestListComputer() {
		
		databaseConnection.openConnection();
		Connection connection = databaseConnection.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement(
										"SELECT * FROM computer"
										);

			this.resultSet = query.executeQuery();
			
			
			List<Computer> listComputers = new ArrayList<Computer>();
			
			while(this.resultSet.next()) {
				 long id = this.resultSet.getLong(1);
				 String name = this.resultSet.getString(2);
				 LocalDate introduced;
				 LocalDate discontinued;
				 long companyId = this.resultSet.getLong(5);
				 
				 if (this.resultSet.getDate(3) != null) {
					 introduced = this.resultSet
					 							.getDate(3)
					 							.toLocalDate();
				 }
				 else {
					 introduced = null;
				 }
				 
				 if (this.resultSet.getDate(4) != null) {
					 discontinued = this.resultSet
					 							.getDate(4)
					 							.toLocalDate();
				 }
				 else {
					 discontinued = null;
				 }
				 
				 Computer computer = new Computer(id, name, introduced, discontinued, companyId);
				 listComputers.add(computer);
			}
			
			 return listComputers;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			databaseConnection.closeConnection();
		}
		return null; // Not very clean but dunno what else to do
	}
	
}
