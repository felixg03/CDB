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
	
	private static final String QUERY_DELETE_COMPUTERS_OF_A_COMPANY =
	"DELETE FROM computer WHERE company_id = ?";
	private static final String QUERY_DELETE_COMPANY =
	"DELETE FROM company WHERE id = ?";
	
	
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
		
		try (Connection connection = databaseConnection.openAndGetAConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement( QUERY_LIST_10_COMPANIES ) ) {
			
			preparedStatement.setInt( 1, offset );
			try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
				listCompanies = this.getListCompaniesFromResultSet( resultSet );
			}
			
		}
		catch ( SQLException sqlException ) {
			sqlException.printStackTrace();
		}
		
		return listCompanies;
	}
	
	
	
	
	public List<Company> requestListCompanies() {
		
		List<Company> listCompanies = new ArrayList<>();
		
		try ( Connection connection = databaseConnection.openAndGetAConnection(); 
			  PreparedStatement preparedStatement = connection.prepareStatement( QUERY_LIST_COMPANIES ) ) {
			
			try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
				listCompanies = this.getListCompaniesFromResultSet( resultSet );
			}
			
		}
		catch ( SQLException sqlException ) {
			sqlException.printStackTrace();
		}
		
		return listCompanies;
	}
	
	
	public void requestCompanyDeletion( long companyId ) {
		
		Connection connection = databaseConnection.openAndGetAConnection();
		
		try (PreparedStatement deleteComputersOfACompany = connection.prepareStatement( QUERY_DELETE_COMPUTERS_OF_A_COMPANY );
			 PreparedStatement deleteCompany = connection.prepareStatement( QUERY_DELETE_COMPANY ) ) {
			
			connection.setAutoCommit(false);
			
			deleteComputersOfACompany.setLong( 1, companyId );
			deleteComputersOfACompany.executeUpdate();
			
			deleteCompany.setLong( 1, companyId );
			deleteCompany.executeUpdate();
			
			connection.commit();
		}
		catch ( SQLException sqlEx ) {
			sqlEx.printStackTrace();
			try {
				connection.rollback();
			}
			catch ( SQLException sqlException ) {
				sqlException.printStackTrace();
			}
			
		}
	}
	
	
	// TOOLS
	private List<Company> getListCompaniesFromResultSet ( ResultSet resultSet ) throws SQLException {
		
		List<Company> listCompaniesToReturn = new ArrayList<>();
		
		while ( resultSet.next() ) {
			 long id = resultSet.getLong(1);
			 String name = resultSet.getString(2);
			 
			 Company company = new CompanyBuilder().setId(id)
					 							   .setName(name)
					 							   .build();
			 listCompaniesToReturn.add( company );
		}
		
		return listCompaniesToReturn;
	}
}
