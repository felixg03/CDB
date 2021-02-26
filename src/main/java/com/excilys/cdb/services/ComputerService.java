package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.database.DAOComputer;
import com.excilys.cdb.models.Computer;

public final class ComputerService {
	
	private static ComputerService instance;
	private DAOComputer daoComputer = DAOComputer.getInstance();
	
	public static ComputerService getInstance() {
		if (instance == null) {
			instance = new ComputerService();
		}
		return instance;
	}
	
	public List<Computer> getListComputers(int offset) {
		return daoComputer.requestListComputer(offset);
	}
	
	public List<Computer> getListComputers() {
		return daoComputer.requestListComputer();
	}
	
	public Computer getOneComputerDetails(long computerId) throws InvalidComputerIdException {
		return daoComputer.requestOneComputerDetails(computerId);
	}
	
	public void getResultComputerCreation(Computer computerToCreate) {
		daoComputer.requestComputerCreation(computerToCreate);
	}
	
	public void getResultComputerUpdate(Computer computerToUpdate) {
		daoComputer.requestComputerUpdate(computerToUpdate);
	}
	
	public void getResultComputerDeletion(long computerId) throws InvalidComputerIdException {
		daoComputer.requestComputerDeletion(computerId);
	}
}
