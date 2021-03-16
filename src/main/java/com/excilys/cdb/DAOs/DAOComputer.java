package com.excilys.cdb.DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.DAOs.rowMappers.ComputerRowMapper;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;
import com.excilys.cdb.models.Page;
import com.zaxxer.hikari.HikariDataSource;



@Repository
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public final class DAOComputer {
	

	private HikariDataSource hikariDataSource;
	
	
	
	@Autowired
	public DAOComputer(HikariDataSource hikariDataSource) {
		super();
		this.hikariDataSource = hikariDataSource;
	}
	
	

	// STRING QUERIES
	private final static String QUERY_LIST_10_COMPUTERS = 
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT 10 OFFSET ?";
	private final static String QUERY_PAGE_COMPUTERS = 
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT ? OFFSET ?";
	private final static String QUERY_PAGE_COMPUTERS_ORDERED_BY_COMPUTER_NAME = 
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id ORDER BY computer.name LIMIT ? OFFSET ?";
	private final static String QUERY_PAGE_COMPUTERS_ORDERED_BY_INTRODUCED_DATE =
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM "
  + "computer LEFT JOIN company ON company.id = computer.company_id ORDER BY computer.introduced LIMIT ? OFFSET ?";
	private final static String QUERY_PAGE_COMPUTERS_ORDERED_BY_DISCONTINUED_DATE =
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM "
  + "computer LEFT JOIN company ON company.id = computer.company_id ORDER BY computer.discontinued LIMIT ? OFFSET ?";
	private final static String QUERY_PAGE_COMPUTERS_ORDERED_BY_COMPANY_NAME =
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM "
  + "computer LEFT JOIN company ON company.id = computer.company_id ORDER BY computer.company_id LIMIT ? OFFSET ?";
	private final static String QUERY_LIST_COMPUTER_SEARCH =
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM "
  + "computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ?";
	private final static String QUERY_LIST_COMPUTERS = 
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id";
	private final static String QUERY_ONE_COMPUTER = 
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
	private final static String QUERY_COMPUTER_EDITION =
	"UPDATE computer SET computer.name = ?, computer.introduced = ?, computer.discontinued = ?, computer.company_id = ? "
  + "WHERE computer.id = ?";
	private final static String QUERY_COMPUTER_DELETION = 
	"DELETE FROM computer WHERE computer.id = ?";
	
	private final static String QUERY_CHECK_IF_COMPUTER_ID_EXISTS = 
	"SELECT EXISTS(SELECT 1 FROM computer WHERE computer.id = ?)";
	private final static String QUERY_GET_NUMBER_OF_COMPUTERS =
	"SELECT COUNT(computer.id) FROM computer";
	
	
	
	public List<Computer> requestListComputer() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		return jdbcTemplate.query( QUERY_LIST_COMPUTERS, new ComputerRowMapper() );
	}
	
	
	
	public Page<Computer> requestPageComputer( Page<Computer> page ) {
		if ( page != null ) {	
			JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
			page.setContent( jdbcTemplate.query( QUERY_PAGE_COMPUTERS
											   , new ComputerRowMapper()
											   , page.getSize()
											   , (page.getNumber() - 1) * page.getSize() 
										  	   ) 
						   );
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByComputerName ( Page<Computer> page ) {
		if ( page != null ) {	
			JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
			page.setContent( jdbcTemplate.query( QUERY_PAGE_COMPUTERS_ORDERED_BY_COMPUTER_NAME
											   , new ComputerRowMapper()
											   , page.getSize()
											   , (page.getNumber() - 1) * page.getSize() 
										  	   ) 
						   );
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByIntroducedDate ( Page<Computer> page ) {
		if ( page != null ) {	
			JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
			page.setContent( jdbcTemplate.query( QUERY_PAGE_COMPUTERS_ORDERED_BY_INTRODUCED_DATE
											   , new ComputerRowMapper()
											   , page.getSize()
											   , (page.getNumber() - 1) * page.getSize() 
										  	   ) 
						   );
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByDiscontinuedDate ( Page<Computer> page ) {
		if ( page != null ) {	
			JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
			page.setContent( jdbcTemplate.query( QUERY_PAGE_COMPUTERS_ORDERED_BY_DISCONTINUED_DATE
											   , new ComputerRowMapper()
											   , page.getSize()
											   , (page.getNumber() - 1) * page.getSize() 
										  	   ) 
						   );
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByCompanyName ( Page<Computer> page ) {
		if ( page != null ) {	
			JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
			page.setContent( jdbcTemplate.query( QUERY_PAGE_COMPUTERS_ORDERED_BY_COMPANY_NAME
											   , new ComputerRowMapper()
											   , page.getSize()
											   , (page.getNumber() - 1) * page.getSize() 
										  	   ) 
						   );
		}
		return page;
	}
	
	
	public Page<Computer> requestPageComputerSearched ( String searchInput ) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		List<Computer> listComputer = jdbcTemplate.query( QUERY_LIST_COMPUTER_SEARCH
													    , new ComputerRowMapper()
													    , "%" + searchInput + "%" 
													    );
		int pageNumber = 1;
		Page<Computer> page = new Page<>( listComputer.size(), pageNumber );
		page.setContent( listComputer );
		return page;
	}
	
	
	public Computer requestOneComputer ( long computerId ) throws InvalidComputerIdException {
		if ( !this.isComputerIdInDatabase( computerId ) ) {
			throw new InvalidComputerIdException( computerId );
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		return jdbcTemplate.queryForObject( QUERY_ONE_COMPUTER
										  , new ComputerRowMapper()
										  , computerId 
										  );
	}
	
	
	
	public void requestComputerCreation ( Computer computerToCreate ) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert( hikariDataSource ).withTableName( "computer" );
		Map<String, Object> parameters = new HashMap<String, Object>();
		this.setParametersForQuery( parameters, computerToCreate );
		simpleJdbcInsert.execute( parameters );
	}
	
	
	
	public void requestComputerEdition ( Computer computerEdited ) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		Long companyId = this.getCompanyIdForQuery( computerEdited );
		Date introducedDate = this.parseIntoDate( computerEdited.getIntroduced() );
		Date discontinuedDate = this.parseIntoDate( computerEdited.getDiscontinued() );
		LoggerManager.getLoggerConsole().debug( "DAOComputer --> requestComputerEdition() --> computerEdited = " + computerEdited );
		jdbcTemplate.update( QUERY_COMPUTER_EDITION
						   , computerEdited.getName()
						   , introducedDate
						   , discontinuedDate
						   , companyId 
						   , computerEdited.getId()
						   );
	}
	
	
	
	public void requestComputerDeletion ( long computerId ) throws InvalidComputerIdException {
			if( !this.isComputerIdInDatabase( computerId ) ) {
				throw new InvalidComputerIdException( computerId );
			}
			this.deleteComputerFromDatabase( computerId, QUERY_COMPUTER_DELETION );
	}
	
	
	
	public void requestListComputerDeletion ( List<Long> listComputerId ) {
		try {
			for ( long id : listComputerId ) {
				this.requestComputerDeletion( id );
			}
		}
		catch ( InvalidComputerIdException invalidComputerIdEx ) {
			invalidComputerIdEx.printStackTrace();
		}
	}
	
	
	
	public int requestNumberOfComputer() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		return jdbcTemplate.queryForObject( QUERY_GET_NUMBER_OF_COMPUTERS, Integer.class );
	}
	
	
	
	private boolean isComputerIdInDatabase( long id ) throws InvalidComputerIdException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		return jdbcTemplate.queryForObject( QUERY_CHECK_IF_COMPUTER_ID_EXISTS
										  , Boolean.class
										  , id 
										  );
	}
	
	
	
	
	
	
	/*
	 *  +------------------------+
	 *  |						 |
	 *  |	JDBC TEMPLATE TOOLS	 |
	 *  |						 |
	 *  +------------------------+
	 */
	
	
	private void setParametersForQuery( Map<String, Object> parameters, Computer computer ) {
		Long companyId = this.getCompanyIdForQuery( computer );
		parameters.put( "name", computer.getName() );
		parameters.put( "introduced", computer.getIntroduced() );
		parameters.put( "discontinued", computer.getDiscontinued() );
		parameters.put( "company_id", companyId ); 
	}
	
	
	
	private void deleteComputerFromDatabase( long computerId, String query ) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate( hikariDataSource );
		jdbcTemplate.update( query, computerId );
	}
	
	
	
	private Long getCompanyIdForQuery( Computer computer ) {
		if ( computer.getCompany() != null ) {
			return computer.getCompany().getId();
		}
		else {
			return null;
		}
	}
	
	
	
	private Date parseIntoDate( LocalDate localDate ) {
		if ( localDate != null ) {
			return Date.valueOf( localDate );
		}
		else {
			return null;
		}
	}
	
	
	
	
	/* 		+---------------------------+
	 * 		|							|
	 * 		|	 OLD CLI VIEW METHODS	|
	 * 		|							|
	 * 		+---------------------------+
	 */
	
	public List<Computer> requestListComputer(int offset) {
		
		List<Computer> listComputers = new ArrayList<Computer>();
		
		try (Connection connection = hikariDataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_10_COMPUTERS) ) {

			preparedStatement.setInt(1, offset);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				listComputers = this.getListComputerFromResultSet(resultSet);
			}
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return listComputers;
	}
	
	
	
	private List<Computer> getListComputerFromResultSet ( ResultSet resultSetArg ) throws SQLException {
		List<Computer> listComputersToReturn = new ArrayList<Computer>();
		
		while (resultSetArg.next()) {
			long id = resultSetArg.getLong(1);
			String name = resultSetArg.getString(2);
			LocalDate introduced = this.castToLocalDate(resultSetArg.getDate(3));
			LocalDate discontinued = this.castToLocalDate(resultSetArg.getDate(4));
			
			long companyId = resultSetArg.getLong(5);
			String companyName = resultSetArg.getString(6);
			Company company = new CompanyBuilder().setId(companyId)
												  .setName(companyName)
												  .build();
			
			Computer computer = new ComputerBuilder().setId(id)
													 .setName(name)
													 .setIntroduced(introduced)
													 .setDiscontinued(discontinued)
													 .setCompany(company)
													 .build();
			listComputersToReturn.add(computer);
		}

		return listComputersToReturn;
	}
	

	
	private LocalDate castToLocalDate(Date date) {
		if (date != null) {
			return date.toLocalDate();
		}
		else {
			return null;
		}
	}
}
