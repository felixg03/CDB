package com.excilys.formation.java.service;

import java.util.List;

import com.excilys.formation.java.model.Computer;
import com.excilys.formation.java.database.DAOComputer;

public class ComputerService {
	
	private DAOComputer daoComputer = DAOComputer.getInstance();
	
	public List<Computer> getListComputers() {
		return daoComputer.requestListComputer();
	}
	
	public Computer getOneComputerDetails(long computerId) {
		return daoComputer.requestOneComputerDetails(computerId);
	}
	
	public Computer getResultComputerCreation() {
		Computer computer = null; // temporary
		return computer;
	}
	
	public Computer getResultComputerUpdate() {
		Computer computer = null; // temporary
		return computer;
	}
	
	public String getResultComputerDeletion(long computerId) {
		return daoComputer.requestComputerDeletion(computerId);
	}
}
