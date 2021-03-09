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
	
	private DBConnection() {
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
	}
	
	public Connection openAndGetAConnection() throws SQLException {
		return ds.getConnection();
		
	}
}