package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.database.DAOComputer;

public class ComputerService {
	
	private DAOComputer daoComputer = DAOComputer.getInstance();
	
	public List<Computer> getListComputers(int offset) {
		return daoComputer.requestListComputer(offset);
	}
	
	public Computer getOneComputerDetails(long computerId) {
		return daoComputer.requestOneComputerDetails(computerId);
	}
	
	public void getResultComputerCreation(Computer computerToCreate) {
		daoComputer.requestComputerCreation(computerToCreate);
	}
	
	public void getResultComputerUpdate(Computer computerToUpdate) {
		daoComputer.requestComputerUpdate(computerToUpdate);
	}
	
	public void getResultComputerDeletion(long computerId) {
		daoComputer.requestComputerDeletion(computerId);
	}
}
