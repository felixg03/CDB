package com.excilys.cdb.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.zaxxer.hikari.HikariDataSource;;

@Repository
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public final class DAOCompany {
	
	@Autowired
	private HikariDataSource hikariDataSource;
	
	// STRING QUERIES
	private static final String QUERY_LIST_10_COMPANIES = 
    "SELECT id, name FROM company ORDER BY id LIMIT 10 OFFSET ?"; 
	private static final String QUERY_LIST_COMPANIES =
	"SELECT id, name FROM company ORDER BY id";
	
	private static final String QUERY_DELETE_COMPUTERS_OF_A_COMPANY =
	"DELETE FROM computer WHERE company_id = ?";
	private static final String QUERY_DELETE_COMPANY =
	"DELETE FROM company WHERE id = ?";
	
	
	
	
	// METHODS
	public List<Company> requestListCompanies(int offset) {
		
		List<Company> listCompanies = new ArrayList<Company>();
		
		try (Connection connection = hikariDataSource.getConnection();
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
		
		try ( Connection connection = hikariDataSource.getConnection(); 
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
		
		Connection connection = null;
		PreparedStatement deleteComputersOfACompany = null;
		PreparedStatement deleteCompany = null;
		
		try {
			
			connection = hikariDataSource.getConnection();
			
			deleteComputersOfACompany = connection.prepareStatement( QUERY_DELETE_COMPUTERS_OF_A_COMPANY );
			deleteCompany = connection.prepareStatement( QUERY_DELETE_COMPANY );
			connection.setAutoCommit( false );
			
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
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlEx) {
				// TODO Auto-generated catch block
				sqlEx.printStackTrace();
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
