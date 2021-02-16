package com.excilys.formation.java.database;

import java.sql.*;

// Follows singleton java pattern
public final class DBConnection {
	
	private static DBConnection instance;
	private Connection connection;
	
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public void initializeConnection() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/computer-database-db"
				  , "admincdb"
				  , "qwerty1234"
				);
		}
		catch(SQLException sqlEx) { // Handle better
			sqlEx.printStackTrace();
		}	
	}
	
}