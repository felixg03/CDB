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
		int pageNumber = offset / 10;
		int nbOfComputer = 10;
		Page<DTOComputerDB> pageDTOComputerDB = computerRepository.findAll( PageRequest.of( pageNumber, nbOfComputer ) );
		return ComputerDTODatabaseMapper.convertToListComputer( pageDTOComputerDB.getContent() );
//		return daoComputer.requestListComputer(offset);
	}
	
	public List<Computer> getListComputers() {
		return ComputerDTODatabaseMapper.convertToListComputer( computerRepository.findAll() );
//		return daoComputer.requestListComputer();
	}
	
	public CustomPage<Computer> getPageComputer(CustomPage<Computer> pageComputer) {
		int pageNumber = pageComputer.getNumber() - 1;
		int nbOfComputer = pageComputer.getSize();
		Page<DTOComputerDB> pageDTOComputerDB = computerRepository.findAll( PageRequest.of( pageNumber, nbOfComputer ) );
		pageComputer.setContent( ComputerDTODatabaseMapper.convertToListComputer( pageDTOComputerDB.getContent() ) );
		return pageComputer;
	}
	
	public CustomPage<Computer> getPageComputerSearched(String searchInput) {
		List<DTOComputerDB> listDTOComputerDB = computerRepository.findByNameContaining( searchInput );
		CustomPage<Computer> pageComputerSearched = new CustomPage<Computer>(listDTOComputerDB.size(), 1);
		pageComputerSearched.setContent( ComputerDTODatabaseMapper.convertToListComputer( listDTOComputerDB ) );
		return pageComputerSearched;
//		return daoComputer.requestPageComputerSearched(searchInput);
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
