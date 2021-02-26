package com.excilys.cdb.database;

import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer.ComputerBuilder;

import java.sql.Connection;
import java.sql.Types;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Follows singleton pattern
public final class DAOComputer {
	
	// GENERAL ATTRIBUTES
	private static DAOComputer instance;
	private DBConnection databaseConnection = DBConnection.getInstance();
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement query;
	
	// STRING QUERIES
	private final static String QUERY_LIST_10_COMPUTERS = 
	"SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY id LIMIT 10 OFFSET ?";
	private final static String QUERY_LIST_COMPUTERS = 
	"SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
  + "FROM computer LEFT JOIN company ON company.id = computer.company_id";
	private final static String QUERY_ONE_COMPUTER_DETAILS = 
	"SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	private final static String QUERY_COMPUTER_CREATION = 
	"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final static String QUERY_COMPUTER_DELETION = 
	"DELETE FROM computer WHERE id = ?";
	
	private final static String QUERY_GET_ALL_COMPUTER_ID = 
	"SELECT id FROM computer";
	
	// GETTERS
	public static DAOComputer getInstance() {
		if (instance == null) {
			instance = new DAOComputer();
		}
		return instance;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}

	// GENERAL METHODS READ/WRITE DATABASE
	public List<Computer> requestListComputer(int offset) {
		
		databaseConnection.openConnection();
		List<Computer> listComputers = new ArrayList<Computer>();
		
		try {
			this.connection = databaseConnection.getConnection();
			this.query = connection
					  	.prepareStatement(
					  			QUERY_LIST_10_COMPUTERS
					  	);
			
			this.query.setInt(1, offset);
			this.resultSet = this.query.executeQuery();
			listComputers = this.getListComputerFromResultSet(resultSet);
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			databaseConnection.closeConnection();
		}
		
		return listComputers;
	}
	public List<Computer> requestListComputer() {
		
		databaseConnection.openConnection();
		List<Computer> listComputers = new ArrayList<Computer>();
		
		try {
			this.connection = databaseConnection.getConnection();
			this.query = connection
					  	.prepareStatement(
							  QUERY_LIST_COMPUTERS
					  	);
			this.resultSet = this.query.executeQuery();
			listComputers = this.getListComputerFromResultSet(resultSet);
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			databaseConnection.closeConnection();
		}
		
		return listComputers;
	}
	public Computer requestOneComputerDetails(long computerId) throws InvalidComputerIdException {
		
		databaseConnection.openConnection();
		Computer computer = null;
		
		try {
			this.connection = databaseConnection.getConnection();
			
			this.checkComputerId(computerId);
			
			this.query = connection
					  	.prepareStatement(
					  		QUERY_ONE_COMPUTER_DETAILS
					  	);
			this.query.setLong(1, computerId);
			this.resultSet = query.executeQuery();
			computer = this.getComputerFromResultSet(this.resultSet);
			
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
		finally {
			databaseConnection.closeConnection();
		}
		
		return computer; 
	}
	public void requestComputerCreation(Computer computerToCreate) {
		
		databaseConnection.openConnection();

		try {
			this.connection = databaseConnection.getConnection();
			this.query = connection
					  	.prepareStatement(
					  		 QUERY_COMPUTER_CREATION
					  	);
			this.setPreparedStatementForComputerCreation(computerToCreate);
			this.query.executeUpdate();
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
		finally {
			databaseConnection.closeConnection();
		}
	}
	
	/* !!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!	  TO REDO	  !!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!
	 */
	/*public void requestComputerUpdate(Computer computerToUpdate) {
		databaseConnection.openConnection();
		Connection connection = databaseConnection.getConnection();

		String request = "UPDATE computer SET ";

		int argumentNumber = 0;

		String name = computerToUpdate.getName();
		LocalDate introduced = computerToUpdate.getIntroduced();
		LocalDate discontinued = computerToUpdate.getDiscontinued();
		Company companyId = computerToUpdate.getCompany();

		boolean hasName = false;
		boolean hasIntroducedDate = false;
		boolean hasDiscontinuedDate = false;
		boolean hasCompanyId = false;

		boolean firstArgument = true;

		if (name != null) {
			if (firstArgument) {
				request += "name = ?";
				firstArgument = false;
			} else {
				request += ", name = ?";
			}
			hasName = true;
		}
		if (introduced != null) {
			if (firstArgument) {
				request += "introduced = ?";
				firstArgument = false;
			} else {
				request += ", introduced = ?";
			}
			hasIntroducedDate = true;
		}
		if (discontinued != null) {
			if (firstArgument) {
				request += "discontinued = ?";
				firstArgument = false;
			} else {
				request += ", discontinued = ?";
			}
			hasDiscontinuedDate = true;
		}
		if (companyId != 0) {
			if (firstArgument) {
				request += "company_id = ?";
				firstArgument = false;
			} else {
				request += ", company_id = ?";
			}
			hasCompanyId = true;
		}

		request += " WHERE id = ?";

		try {
			this.query = connection.prepareStatement(request);
			if (hasName) {
				argumentNumber++;
				this.query.setString(argumentNumber, name);
			}
			if (hasIntroducedDate) {
				argumentNumber++;
				this.query.setDate(argumentNumber, Date.valueOf(introduced));
			}
			if (hasDiscontinuedDate) {
				argumentNumber++;
				this.query.setDate(argumentNumber, Date.valueOf(discontinued));
			}
			if (hasCompanyId) {
				argumentNumber++;
				this.query.setLong(argumentNumber, companyId);
			}

			argumentNumber++;
			this.query.setLong(argumentNumber, computerToUpdate.getId());

			this.query.executeUpdate();
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
		finally {
			databaseConnection.closeConnection();
		}
	}*/
	public void requestComputerDeletion(long computerId) throws InvalidComputerIdException {
		
		databaseConnection.openConnection();
		
		try {
			this.connection = databaseConnection.getConnection();
			
			this.checkComputerId(computerId);
			
			this.query = connection
					  	.prepareStatement(
					  		 QUERY_COMPUTER_DELETION
					  	);
			query.setLong(1, computerId);
			query.executeUpdate();
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			databaseConnection.closeConnection();
		}
	}
	
	// TOOLS
	private List<Computer> getListComputerFromResultSet(ResultSet resultSetArg) throws SQLException {
		List<Computer> listComputersToReturn = new ArrayList<Computer>();
		
		while (resultSetArg.next()) {
			long id = resultSetArg.getLong(1);
			String name = resultSetArg.getString(2);
			LocalDate introduced = this.castDateToLocalDate(resultSetArg.getDate(3));
			LocalDate discontinued = this.castDateToLocalDate(resultSetArg.getDate(4));
			
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
			introduced = this.castDateToLocalDate(resultSetArg.getDate(3));
			discontinued = this.castDateToLocalDate(resultSetArg.getDate(4));
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
	private LocalDate castDateToLocalDate(Date date) {
		if (date != null) {
			return date.toLocalDate();
		}
		else {
			return null;
		}
	}
	private void setPreparedStatementForComputerCreation(Computer computer) throws SQLException {
		this.setPreparedStatementParameter(
				computer.getName()
			  , Types.VARCHAR
			  , 1
				);
		this.setPreparedStatementParameter(
			    computer.getIntroduced()
			  , Types.DATE
			  , 2
				);
		this.setPreparedStatementParameter(
				computer.getDiscontinued()
			  , Types.DATE
			  , 3
				);
		
		this.query.setLong(4, computer.getCompany().getId());
	}
	private void setPreparedStatementParameter (Object parameter
											  , int objectType
											  , int position)  throws SQLException {
		if (parameter == null) {
			this.query.setNull(position, objectType);
		}
		else {
			if (objectType == Types.VARCHAR) {
				this.query.setString(position, (String) parameter);
			}
			else if (objectType == Types.DATE) {
				this.query.setDate(position, Date.valueOf((LocalDate) parameter));
			}
		}
	}
	
	private void checkComputerId(long id) throws InvalidComputerIdException, SQLException {
		this.query = connection
			  		.prepareStatement(
			  			 QUERY_GET_ALL_COMPUTER_ID
				  	);
		
		this.resultSet = this.query.executeQuery();
		Set<Long> setOfComputerId = getSetOfIntegerFromResultSet(this.resultSet);
		
		if (!setOfComputerId.contains(id)) {
			throw new InvalidComputerIdException(id);
		}
	}
	
	private Set<Long> getSetOfIntegerFromResultSet(ResultSet resultSetArg) throws SQLException {
		Set<Long> setOfInteger = new HashSet<Long>();
		while(resultSetArg.next()) {
			setOfInteger.add(resultSetArg.getLong(1));
		}
		return setOfInteger;
	}
}
