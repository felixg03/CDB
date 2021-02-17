package com.excilys.formation.java.service;

import java.util.List;

import com.excilys.formation.java.model.Computer;
import com.excilys.formation.java.database.DAOComputer;

public class ComputerService {
	
	private DAOComputer daoComputer = DAOComputer.getInstance();
	
	public List<Computer> getListComputers(int offset) {
		return daoComputer.requestListComputer(offset);
	}
	
	public Computer getOneComputerDetails(long computerId) {
		return daoComputer.requestOneComputerDetails(computerId);
	}
	
	public String getResultComputerCreation(Computer computerToCreate) {
		return daoComputer.requestComputerCreation(computerToCreate);
	}
	
	public String getResultComputerUpdate(Computer computerToUpdate) {
		return daoComputer.requestComputerUpdate(computerToUpdate);
	}
	
	public String getResultComputerDeletion(long computerId) {
		return daoComputer.requestComputerDeletion(computerId);
	}
}
