package com.excilys.cdb.database;

import java.sql.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// Follows singleton java pattern
public final class DBConnection {
	
	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
	
	private static DBConnection instance;
	private final String databaseURL = "jdbc:mysql://localhost/computer-database-db";
	private final String databaseAdmin = "admincdb";
	private final String databasePassword = "qwerty1234";
	
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection openAndGetAConnection() {
		Connection connection = null;
		try {
			config.setDriverClassName( "com.mysql.cj.jdbc.Driver" );
			config.setJdbcUrl( databaseURL );
	        config.setUsername( databaseAdmin );
	        config.setPassword( databasePassword );
	        config.addDataSourceProperty( "cachePrepStmts" , "true" );
	        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
	        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
	        config.setConnectionTimeout( 34000 );
	        config.setIdleTimeout( 28_740_000 );
	        config.setMaxLifetime( 28_740_000 );
	        ds = new HikariDataSource( config );
	        
			
//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				connection = DriverManager.getConnection(
//											databaseURL
//										  , databaseAdmin
//										  , databasePassword
//										);
//			}
//			catch(SQLException | ClassNotFoundException e) {
//				e.printStackTrace();
//			}
			

			connection = ds.getConnection();
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return connection;
	}
}