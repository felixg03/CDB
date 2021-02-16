package com.excilys.formation.java.model;

import java.sql.Connection;

// Follows singleton java pattern
public final class DAODatabase {
	
	private static DAODatabase instance;
	private Connection DBConnection;
	
	public static DAODatabase getInstance() {
		if (instance == null) {
			instance = new DAODatabase();
		}
		return instance;
	}
	
	
}
