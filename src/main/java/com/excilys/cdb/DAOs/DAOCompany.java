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
import org.springframework.transaction.annotation.Transactional;

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
	
	private static final String QUERY_CHECK_IF_COMPUTER_ID_EXISTS = 
	"SELECT EXISTS(SELECT 1 FROM company WHERE company.id = ?)";
	private static final String QUERY_DELETE_COMPUTERS_OF_A_COMPANY =
	"DELETE FROM computer WHERE computer.company_id = ?";
	private static final String QUERY_DELETE_COMPANY =
	"DELETE FROM company WHERE company.id = ?";
	
	
	
	public List<Company> requestListCompanies() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		return jdbcTemplate.query( QUERY_LIST_COMPANIES, new CompanyRowMapper() );
	}
	
	
	
	@Transactional
	public void requestCompanyDeletion( long companyId ) {
		if ( this.isCompanyIdInDatabase( companyId ) ) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
			jdbcTemplate.update( QUERY_DELETE_COMPUTERS_OF_A_COMPANY, companyId );
			jdbcTemplate.update( QUERY_DELETE_COMPANY, companyId );
		}
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
	
	
	
	public boolean isCompanyIdInDatabase( long companyId ) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		return jdbcTemplate.queryForObject( QUERY_CHECK_IF_COMPUTER_ID_EXISTS, Boolean.class, companyId );
	}
	
	
	
	/*
	 * 		+---------------------------+
	 * 		|							|
	 * 		|	OLD CLI VIEW METHODS	|
	 * 		|							|
	 * 		+---------------------------+
	 */
	
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
	
}
