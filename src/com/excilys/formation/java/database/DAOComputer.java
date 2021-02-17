package com.excilys.formation.java.database;

import com.excilys.formation.java.model.Company;
import com.excilys.formation.java.model.Computer;

import java.sql.Connection;
import java.sql.Types;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Follows singleton pattern
public final class DAOComputer {

	private static DAOComputer instance;
	private DBConnection databaseConnection = DBConnection.getInstance();
	private ResultSet resultSet;

	public static DAOComputer getInstance() {
		if (instance == null) {
			instance = new DAOComputer();
		}
		return instance;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public List<Computer> requestListComputer(int offset) {
		databaseConnection.openConnection();
		Connection connection = databaseConnection.getConnection();
		try {
			PreparedStatement query = connection
									.prepareStatement(
										"SELECT * FROM computer ORDER BY id LIMIT 10 OFFSET ?"
									);
			query.setInt(1, offset);
			this.resultSet = query.executeQuery();

			List<Computer> listComputers = new ArrayList<Computer>();

			while (this.resultSet.next()) {
				long id = this.resultSet.getLong(1);
				String name = this.resultSet.getString(2);
				LocalDate introduced;
				LocalDate discontinued;
				long companyId = this.resultSet.getLong(5);

				if (this.resultSet.getDate(3) != null) {
					introduced = this.resultSet.getDate(3).toLocalDate();
				} else {
					introduced = null;
				}

				if (this.resultSet.getDate(4) != null) {
					discontinued = this.resultSet.getDate(4).toLocalDate();
				} else {
					discontinued = null;
				}

				Computer computer = new Computer(id, name, introduced, discontinued, companyId);
				listComputers.add(computer);
			}

			return listComputers;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			databaseConnection.closeConnection();
		}
		return null; // Not very clean but dunno what else to do
	}

	public Computer requestOneComputerDetails(long computerId) {
		databaseConnection.openConnection();
		Connection connection = databaseConnection.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement("SELECT * FROM computer WHERE id = ?");
			query.setLong(1, computerId);
			this.resultSet = query.executeQuery();

			if (this.resultSet.next()) {
				long id = this.resultSet.getLong(1);
				String name = this.resultSet.getString(2);
				LocalDate introduced;
				LocalDate discontinued;
				long companyId = this.resultSet.getLong(5);

				if (this.resultSet.getDate(3) != null) {
					introduced = this.resultSet.getDate(3).toLocalDate();
				} else {
					introduced = null;
				}

				if (this.resultSet.getDate(4) != null) {
					discontinued = this.resultSet.getDate(4).toLocalDate();
				} else {
					discontinued = null;
				}

				return new Computer(id, name, introduced, discontinued, companyId);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			databaseConnection.closeConnection();
		}
		return new Computer(0, "ERROR: Didn't work", null, null, 0); // Not very clean but dunno what else to do
	}

	public String requestComputerCreation(Computer computerToCreate) {
		databaseConnection.openConnection();
		Connection connection = databaseConnection.getConnection();

		String requestMaxId = "SELECT MAX(id) FROM computer";
		String requestCreation = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
		
		long id = computerToCreate.getId();

		try {
			// Getting max id in table computer
			PreparedStatement query = connection.prepareStatement(requestMaxId);
			this.resultSet = query.executeQuery();
			if (this.resultSet.next()) {
				id = resultSet.getLong(1) + 1;
			}
			
			// Creating computer
			query = connection.prepareStatement(requestCreation);
			query.setLong(1, id);
			
			if (computerToCreate.getName() == null) {
				query.setNull(2, Types.VARCHAR);
			}
			else {
				query.setString(2, computerToCreate.getName());
			}
			
			
			if (computerToCreate.getIntroduced() == null) {
				query.setNull(3, Types.DATE);
			}
			else {
				query.setDate(3, Date.valueOf(computerToCreate.getIntroduced()));
			}
			
			
			if (computerToCreate.getDiscontinued() == null) {
				query.setNull(4, Types.DATE);
			}
			else {
				query.setDate(4, Date.valueOf(computerToCreate.getDiscontinued()));
			}
			
			query.setLong(5, computerToCreate.getCompanyId());
			query.executeUpdate();

			return "CREATION SUCCESS";
			
		} 
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
		finally {
			databaseConnection.closeConnection();
		}

		return "CREATION FAILURE";
	}

	public String requestComputerUpdate(Computer computerToUpdate) {
		databaseConnection.openConnection();
		Connection connection = databaseConnection.getConnection();

		String request = "UPDATE computer SET ";

		int argumentNumber = 0;

		String name = computerToUpdate.getName();
		LocalDate introduced = computerToUpdate.getIntroduced();
		LocalDate discontinued = computerToUpdate.getDiscontinued();
		long companyId = computerToUpdate.getCompanyId();

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
			PreparedStatement query = connection.prepareStatement(request);
			if (hasName) {
				argumentNumber++;
				query.setString(argumentNumber, name);
			}
			if (hasIntroducedDate) {
				argumentNumber++;
				query.setDate(argumentNumber, Date.valueOf(introduced));
			}
			if (hasDiscontinuedDate) {
				argumentNumber++;
				query.setDate(argumentNumber, Date.valueOf(discontinued));
			}
			if (hasCompanyId) {
				argumentNumber++;
				query.setLong(argumentNumber, companyId);
			}

			argumentNumber++;
			query.setLong(argumentNumber, computerToUpdate.getId());

			query.executeUpdate();

			return "UPDATE SUCCESS";
		} catch (SQLException sqlException) {
			System.out.println();
			System.out.println("DAOComputer.requestComputerDeletion(): exception");
			System.out.println();
			sqlException.printStackTrace();
		} finally {
			databaseConnection.closeConnection();
		}

		return "UPDATE FAILURE";

	}

	public String requestComputerDeletion(long computerId) {
		databaseConnection.openConnection();
		Connection connection = databaseConnection.getConnection();
		try {
			PreparedStatement query = connection.prepareStatement("DELETE FROM computer WHERE id = ?");
			query.setLong(1, computerId);
			query.executeUpdate();

			return "DELETION SUCCESS";
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			databaseConnection.closeConnection();
		}

		return "DELETION FAILURE";
	}
}
