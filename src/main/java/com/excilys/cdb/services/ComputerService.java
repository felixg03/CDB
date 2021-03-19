package com.excilys.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.cdb.DAOImpl.DAOComputer;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Page;



@Service
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class ComputerService {
	
	private DAOComputer daoComputer;
	
	@Autowired
	public ComputerService(DAOComputer daoComputer) {
		super();
		this.daoComputer = daoComputer;
	}

	
	
	
	// For old CLIView
	public List<Computer> getListComputers(int offset) {
		return daoComputer.requestListComputer(offset);
	}
	
	public List<Computer> getListComputers() {
		return daoComputer.requestListComputer();
	}
	
	public Page<Computer> getPageComputer(Page<Computer> pageComputer) {
		return daoComputer.requestPageComputer(pageComputer);
	}
	
	public Page<Computer> getPageComputerSearched(String searchInput) {
		return daoComputer.requestPageComputerSearched(searchInput);
	}
	
	public Page<Computer> getPageComputerOrderedByComputerName(Page<Computer> pageComputer) {
		return daoComputer.requestPageComputerOrderedByComputerName(pageComputer);
	}
	
	public Page<Computer> getPageComputerOrderedByIntroducedDate(Page<Computer> pageComputer) {
		return daoComputer.requestPageComputerOrderedByIntroducedDate(pageComputer);
	}
	
	public Page<Computer> getPageComputerOrderedByDiscontinuedDate(Page<Computer> pageComputer) {
		return daoComputer.requestPageComputerOrderedByDiscontinuedDate(pageComputer);
	}
	
	public Page<Computer> getPageComputerOrderedByCompanyName(Page<Computer> pageComputer) {
		return daoComputer.requestPageComputerOrderedByCompanyName(pageComputer);
	}
	
	public Computer getOneComputer(long computerId) throws InvalidComputerIdException {
		return daoComputer.requestOneComputer(computerId);
	}
	
	public void callComputerCreation(Computer computerToCreate) {
		daoComputer.requestComputerCreation(computerToCreate);
	}
	
	public void callComputerEdition(Computer computerEdited) {
		daoComputer.requestComputerEdition(computerEdited);
	}
	
	public void getResultComputerDeletion(long computerId) throws InvalidComputerIdException {
		daoComputer.requestComputerDeletion(computerId);
	}
	
	public void callListComputerDeletion(List<Long> listComputerId) {
		daoComputer.requestListComputerDeletion(listComputerId);
	}
	
	public long getNumberOfComputer() {
		return daoComputer.requestNumberOfComputer();
	}
}
