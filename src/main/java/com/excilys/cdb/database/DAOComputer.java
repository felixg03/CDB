package com.excilys.cdb.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;
import com.excilys.cdb.models.Page;


// Follows singleton pattern
public final class DAOComputer {
	
	// GENERAL ATTRIBUTES
	private static DAOComputer instance;
	private DBConnection databaseConnection = DBConnection.getInstance();
	
	// STRING QUERIES
	private final static String QUERY_LIST_10_COMPUTERS = 
	"SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY id LIMIT 10 OFFSET ?";
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
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id";
	private final static String QUERY_ONE_COMPUTER = 
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
	private final static String QUERY_COMPUTER_CREATION = 
	"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final static String QUERY_COMPUTER_EDITION =
	"UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private final static String QUERY_COMPUTER_DELETION = 
	"DELETE FROM computer WHERE id = ?";
	
	private final static String QUERY_GET_COMPUTER_ID = 
	"SELECT id FROM computer WHERE id = ?";
	private final static String QUERY_GET_NUMBER_OF_COMPUTERS =
	"SELECT COUNT(id) FROM computer";
	
	// GETTERS
	public static DAOComputer getInstance() {
		if (instance == null) {
			instance = new DAOComputer();
		}
		return instance;
	}

	
	/*
	 * ##############################################
	 * ###  OLD requestListComputer FOR CLI VIEW  ###
	 * ##############################################
	 */
	public List<Computer> requestListComputer(int offset) {
		
		List<Computer> listComputers = new ArrayList<Computer>();
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_10_COMPUTERS);
			preparedStatement.setInt(1, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			listComputers = this.getListComputerFromResultSet(resultSet);
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return listComputers;
	}
	
	
	
	
	
	public List<Computer> requestListComputer() {
		
		List<Computer> listComputers = new ArrayList<Computer>();
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_COMPUTERS);
			ResultSet resultSet = preparedStatement.executeQuery();
			listComputers = this.getListComputerFromResultSet(resultSet);
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return listComputers;
	}
	
	
	
	public Page<Computer> requestPageComputer(Page<Computer> page) {
		
		if (page != null) {	
			try (Connection connection = databaseConnection.openAndGetAConnection()) {

				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PAGE_COMPUTERS);
				preparedStatement.setInt(1, page.getSize());
				preparedStatement.setInt(2, (page.getNumber() - 1) * page.getSize());
				ResultSet resultSet = preparedStatement.executeQuery();
				page.setContent(getListComputerFromResultSet(resultSet));
			}
			catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByComputerName(Page<Computer> page) {
		
		if (page != null) {	
			try (Connection connection = databaseConnection.openAndGetAConnection()) {

				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PAGE_COMPUTERS_ORDERED_BY_COMPUTER_NAME);
				preparedStatement.setInt(1, page.getSize());
				preparedStatement.setInt(2, (page.getNumber() - 1) * page.getSize());
				ResultSet resultSet = preparedStatement.executeQuery();
				page.setContent(getListComputerFromResultSet(resultSet));
			}
			catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByIntroducedDate(Page<Computer> page) {
		if (page != null) {	
			try (Connection connection = databaseConnection.openAndGetAConnection()) {

				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PAGE_COMPUTERS_ORDERED_BY_INTRODUCED_DATE);
				preparedStatement.setInt(1, page.getSize());
				preparedStatement.setInt(2, (page.getNumber() - 1) * page.getSize());
				ResultSet resultSet = preparedStatement.executeQuery();
				page.setContent(getListComputerFromResultSet(resultSet));
			}
			catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByDiscontinuedDate(Page<Computer> page) {
		if (page != null) {	
			try (Connection connection = databaseConnection.openAndGetAConnection()) {

				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PAGE_COMPUTERS_ORDERED_BY_DISCONTINUED_DATE);
				preparedStatement.setInt(1, page.getSize());
				preparedStatement.setInt(2, (page.getNumber() - 1) * page.getSize());
				ResultSet resultSet = preparedStatement.executeQuery();
				page.setContent(getListComputerFromResultSet(resultSet));
			}
			catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return page;
	}
	
	public Page<Computer> requestPageComputerOrderedByCompanyName(Page<Computer> page) {
		if (page != null) {	
			try (Connection connection = databaseConnection.openAndGetAConnection()) {

				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PAGE_COMPUTERS_ORDERED_BY_COMPANY_NAME);
				preparedStatement.setInt(1, page.getSize());
				preparedStatement.setInt(2, (page.getNumber() - 1) * page.getSize());
				ResultSet resultSet = preparedStatement.executeQuery();
				page.setContent(getListComputerFromResultSet(resultSet));
			}
			catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return page;
	}
	
	
	public Page<Computer> requestPageComputerSearched(String searchInput) {
		Page<Computer> pageComputerSearched = null;
		try (Connection connection = databaseConnection.openAndGetAConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_COMPUTER_SEARCH);
			preparedStatement.setString(1, "%" + searchInput + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Computer> listComputerFound = getListComputerFromResultSet(resultSet);
			int pageNumberOne = 1;
			pageComputerSearched = new Page<Computer>(listComputerFound.size(), pageNumberOne);
			pageComputerSearched.setContent(listComputerFound);
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return pageComputerSearched;
	}
	
	
	public Computer requestOneComputer(long computerId) throws InvalidComputerIdException {
		
		Computer computer = null;
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {
			
			this.checkComputerId(computerId, connection);
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ONE_COMPUTER);
			preparedStatement.setLong(1, computerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			computer = this.getComputerFromResultSet(resultSet);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return computer; 
	}
	
	
	
	public void requestComputerCreation(Computer computerToCreate) {
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_COMPUTER_CREATION);
			preparedStatement = this.setAndReturnPreparedStatementForComputerCreation(computerToCreate, preparedStatement);
			preparedStatement.executeUpdate();
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	
	public void requestComputerEdition(Computer computerEdited) {
		try (Connection connection = databaseConnection.openAndGetAConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_COMPUTER_EDITION);
			preparedStatement.setString(1, computerEdited.getName());
			preparedStatement.setDate(2, this.castToDate(computerEdited.getIntroduced()));
			preparedStatement.setDate(3, this.castToDate(computerEdited.getDiscontinued()));
			preparedStatement.setLong(4, computerEdited.getCompany().getId());
			preparedStatement.setLong(5, computerEdited.getId());
			preparedStatement.executeUpdate();
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	public void requestComputerDeletion(long computerId) throws InvalidComputerIdException {
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {
			
			this.checkComputerId(computerId, connection);
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_COMPUTER_DELETION);
			preparedStatement.setLong(1, computerId);
			preparedStatement.executeUpdate();
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public void requestListComputerDeletion(List<Long> listComputerId) {
		try {
			for (long id : listComputerId) {
				this.requestComputerDeletion(id);
			}
		}
		catch (InvalidComputerIdException invalidComputerIdEx) {
			invalidComputerIdEx.printStackTrace();
		}
	}
	
	public long requestNumberOfComputer() {
		
		long numberOfComputer = -1;
		
		try (Connection connection = databaseConnection.openAndGetAConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_NUMBER_OF_COMPUTERS);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				numberOfComputer = resultSet.getLong(1);
			}
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return numberOfComputer;
	}
	
	
	// TOOLS
	private List<Computer> getListComputerFromResultSet(ResultSet resultSetArg) throws SQLException {
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
	private Computer getComputerFromResultSet(ResultSet resultSetArg) throws SQLException {
		
		long id = 0;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		long companyId = 0;
		String companyName = null;
		
		if (resultSetArg.next()) {
			id = resultSetArg.getLong(1);
			name = resultSetArg.getString(2);
			introduced = this.castToLocalDate(resultSetArg.getDate(3));
			discontinued = this.castToLocalDate(resultSetArg.getDate(4));
			companyId = resultSetArg.getLong(5);
			companyName = resultSetArg.getString(6);
		}
		Company company = new CompanyBuilder().setId(companyId)
											  .setName(companyName)
											  .build();
		return new ComputerBuilder().setId(id)
									.setName(name)
									.setIntroduced(introduced)
									.setDiscontinued(discontinued)
									.setCompany(company)
									.build();
	}
	private LocalDate castToLocalDate(Date date) {
		if (date != null) {
			return date.toLocalDate();
		}
		else {
			return null;
		}
	}
	
	private Date castToDate(LocalDate localDate) {
		if (localDate != null) {
			return Date.valueOf(localDate);
		}
		else {
			return null;
		}
	}
	
	private PreparedStatement setAndReturnPreparedStatementForComputerCreation(Computer computer, PreparedStatement preparedStatement) throws SQLException {
		preparedStatement = this.setPreparedStatementParameter(computer.getName()
															 , Types.VARCHAR
															 , 1
															 , preparedStatement);
		preparedStatement = this.setPreparedStatementParameter(computer.getIntroduced()
															 , Types.DATE
															 , 2
															 , preparedStatement);
		preparedStatement = this.setPreparedStatementParameter(computer.getDiscontinued()
															 , Types.DATE
															 , 3
															 , preparedStatement);
		
		preparedStatement.setLong(4, computer.getCompany().getId());
		
		return preparedStatement;
	}
	
	
	
	private PreparedStatement setPreparedStatementParameter (Object parameter
														   , int objectType
														   , int position
														   , PreparedStatement preparedStatement)  
																   throws SQLException {
		if (parameter == null) {
			preparedStatement.setNull(position, objectType);
		}
		else {
			if (objectType == Types.VARCHAR) {
				preparedStatement.setString(position, (String) parameter);
			}
			else if (objectType == Types.DATE) {
				preparedStatement.setDate(position, Date.valueOf((LocalDate) parameter));
			}
		}
		
		return preparedStatement;
	}
	
	private void checkComputerId(long id, Connection connection) throws InvalidComputerIdException, SQLException {
		PreparedStatement preparedStatement = connection
									    	 .prepareStatement(
									  			 QUERY_GET_COMPUTER_ID
										  	  );
		preparedStatement.setLong(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet == null) {
			throw new InvalidComputerIdException(id);
		}
	}
}
