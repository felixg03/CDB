package com.excilys.cdb.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.DAOs.rowMappers.CompanyRowMapper;
import com.excilys.cdb.models.Company;
import com.zaxxer.hikari.HikariDataSource;

@Repository
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public final class DAOCompany {
	
	private HikariDataSource hikariDataSource;
	
	
	
	@Autowired
	public DAOCompany(HikariDataSource hikariDataSource) {
		super();
		this.hikariDataSource = hikariDataSource;
	}


	// STRING QUERIES
	private static final String QUERY_LIST_10_COMPANIES = 
    "SELECT id, name FROM company ORDER BY id LIMIT 10 OFFSET ?"; 
	private static final String QUERY_LIST_COMPANIES =
	"SELECT id, name FROM company ORDER BY id";
	private static final String QUERY_GET_COMPANY_ID =
	"SELECT id FROM company WHERE id = ?";
	
	private static final String QUERY_DELETE_COMPUTERS_OF_A_COMPANY =
	"DELETE FROM computer WHERE company_id = ?";
	private static final String QUERY_DELETE_COMPANY =
	"DELETE FROM company WHERE id = ?";
	
	
	
	
	
	
	// METHODS
	
	// Old method of CLI View
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
		
		try ( Connection connection = hikariDataSource.getConnection() ) {
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
			listCompanies = jdbcTemplate.query( QUERY_LIST_COMPANIES, new CompanyRowMapper() );
			
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
	
	public boolean requestCheckCompanyId( long companyId ) {
		boolean companyIdIsPresent = false;
		try ( Connection connection = hikariDataSource.getConnection();
			  PreparedStatement preparedStatement = connection.prepareStatement( QUERY_GET_COMPANY_ID ) ) {
			
			preparedStatement.setLong( 1, companyId );
			ResultSet resultSet = preparedStatement.executeQuery(); 
			
			if ( resultSet.next() && resultSet.getLong(1) == companyId ) {
				companyIdIsPresent = true;
			}
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		
		return companyIdIsPresent;
	}
	
	
	// TOOLS
	private List<Company> getListCompaniesFromResultSet ( ResultSet resultSet ) throws SQLException {
		
		List<Company> listCompaniesToReturn = new ArrayList<>();
		CompanyRowMapper companyRowMapper = new CompanyRowMapper();
		while ( resultSet.next() ) {
			 listCompaniesToReturn.add( companyRowMapper.mapRow( resultSet, resultSet.getRow() ) ) ;
		}
		
		return listCompaniesToReturn;
	}
}
