package com.excilys.formation.java.service;

import java.util.List;

import com.excilys.formation.java.model.Computer;

public class ComputerService {
	
	public List<Computer> getListComputers() {
		List<Computer> listComputers = null; // temporary
		// call to DAODatabase etc.
		return listComputers;
	}
	
	public Computer getOneComputerDetails() {
		Computer computer = null; //temporary
		// call to DAODAtabase etc.
		return computer;
	}
	
	public Computer getResultComputerCreation() {
		Computer computer = null; // temporary
		return computer;
	}
	
	public Computer getResultComputerUpdate() {
		Computer computer = null; // temporary
		return computer;
	}
	
	public String getResultComputerDeletion() {
		return ""; // temporary
		/* We can't return a Computer here, because
		 * it would have deleted
		 * */
	}
}
