package com.excilys.formation.java.database;

import java.sql.ResultSet;

// Follows singleton pattern
public final class DAOComputer {
	
	private static DAOComputer instance;
	private DBConnection databaseConnection = DBConnection.getInstance();
	// private ResultSet resultSet;

	public static DAOComputer getInstance() {
		if (instance == null) {
			instance = new DAOComputer();
		}
		return instance;
	}
	
	/*public ResultSet getResultSet() {
		return resultSet;
	}*/
	
	
	
}
