package com.excilys.cdb.database;

import java.sql.*;

// Follows singleton java pattern
public final class DBConnection {
	
	private static DBConnection instance;
	private Connection connection;
	private final String databaseURL = "jdbc:mysql://localhost/computer-database-db";
	private final String databaseAdmin = "admincdb";
	private final String databasePassword = "qwerty1234";
	
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					databaseURL
				  , databaseAdmin
				  , databasePassword
				);
		}
		catch(SQLException | ClassNotFoundException e) { // Handle better
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
}