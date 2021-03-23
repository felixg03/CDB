package com.excilys.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.excilys.cdb.DAOs.DAOComputer;
import com.excilys.cdb.DTODatabase.DTOComputerDB;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.CustomPage;
import com.excilys.cdb.repositoryInterfaces.ComputerRepository;
import com.excilys.cdb.mappers.DTODatabaseMappers.ComputerDTODatabaseMapper;



@Service
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class ComputerService {
	
	private DAOComputer daoComputer;
	private ComputerRepository computerRepository;
	
	@Autowired
	public ComputerService(DAOComputer daoComputer, ComputerRepository computerRepository) {
		super();
		this.daoComputer = daoComputer;
		this.computerRepository = computerRepository;
	}

	
	
	
	// For old CLIView
	public List<Computer> getListComputers(int offset) {
		return daoComputer.requestListComputer(offset);
	}
	
	public List<Computer> getListComputers() {
		return daoComputer.requestListComputer();
	}
	
	public CustomPage<Computer> getPageComputer(CustomPage<Computer> pageComputer) {
//		return daoComputer.requestPageComputer(pageComputer);
		Page<DTOComputerDB> pageDTOComputerDB = computerRepository.findAll( PageRequest.of( pageComputer.getNumber() - 1, pageComputer.getSize() ) );
		pageComputer.setContent( ComputerDTODatabaseMapper.convertToListComputer( pageDTOComputerDB.getContent() ) );
		return pageComputer;
	}
	
	public CustomPage<Computer> getPageComputerSearched(String searchInput) {
		return daoComputer.requestPageComputerSearched(searchInput);
	}
	
	public CustomPage<Computer> getPageComputerOrderedByComputerName(CustomPage<Computer> pageComputer) {
		return daoComputer.requestPageComputerOrderedByComputerName(pageComputer);
	}
	
	public CustomPage<Computer> getPageComputerOrderedByIntroducedDate(CustomPage<Computer> pageComputer) {
		return daoComputer.requestPageComputerOrderedByIntroducedDate(pageComputer);
	}
	
	public CustomPage<Computer> getPageComputerOrderedByDiscontinuedDate(CustomPage<Computer> pageComputer) {
		return daoComputer.requestPageComputerOrderedByDiscontinuedDate(pageComputer);
	}
	
	public CustomPage<Computer> getPageComputerOrderedByCompanyName(CustomPage<Computer> pageComputer) {
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
