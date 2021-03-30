package com.excilys.cdb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.excilys.cdb.DTOs.DTODatabase.DTOComputerDB;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.mappers.DTODatabaseMappers.ComputerDTODatabaseMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.CustomPage;
import com.excilys.cdb.repositoryInterfaces.ComputerRepository;



@Service
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class ComputerService {
	
	private ComputerRepository computerRepository;
	
	@Autowired
	public ComputerService(ComputerRepository computerRepository) {
		super();
		this.computerRepository = computerRepository;
	}

	
	
	
	// For old CLIView
	public List<Computer> getListComputers(int offset) {
		int pageNumber = offset / 10;
		int nbOfComputer = 10;
		Page<DTOComputerDB> pageDTOComputerDB = computerRepository.findAll( PageRequest.of( pageNumber, nbOfComputer ) );
		return ComputerDTODatabaseMapper.convertToListComputer( pageDTOComputerDB.getContent() );
	}
	
	public List<Computer> getListComputers() {
		ArrayList<DTOComputerDB> list = new ArrayList<>();
		
		if( !list.isEmpty() ) {
			System.out.println( "\nLa liste n'est pas vide :/\n" );
		}
		
		return ComputerDTODatabaseMapper.convertToListComputer( computerRepository.findAll() );
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
	}
	
	public CustomPage<Computer> getPageComputerOrderedByComputerName(CustomPage<Computer> pageComputer) {
		Page<DTOComputerDB> pageDTO = computerRepository.findByOrderByNameAsc( PageRequest.of( pageComputer.getNumber(), pageComputer.getSize() ) );
		List<Computer> listComputerOrdered = ComputerDTODatabaseMapper.convertToListComputer( pageDTO.getContent() );
		pageComputer.setContent( listComputerOrdered );
		return pageComputer;
	}
	
	public CustomPage<Computer> getPageComputerOrderedByIntroducedDate( CustomPage<Computer> pageComputer ) {
		Page<DTOComputerDB> pageDTO = computerRepository.findByOrderByIntroducedAsc( PageRequest.of( pageComputer.getNumber(), pageComputer.getSize() ) );
		List<Computer> listComputerOrdered = ComputerDTODatabaseMapper.convertToListComputer( pageDTO.getContent() );
		pageComputer.setContent( listComputerOrdered );
		return pageComputer;
	}
	
	public CustomPage<Computer> getPageComputerOrderedByDiscontinuedDate( CustomPage<Computer> pageComputer ) {
		Page<DTOComputerDB> pageDTO = computerRepository.findByOrderByDiscontinuedAsc( PageRequest.of( pageComputer.getNumber(), pageComputer.getSize() ) );
		List<Computer> listComputerOrdered = ComputerDTODatabaseMapper.convertToListComputer( pageDTO.getContent() );
		pageComputer.setContent( listComputerOrdered );
		return pageComputer;
	}
	
	public CustomPage<Computer> getPageComputerOrderedByCompanyName( CustomPage<Computer> pageComputer ) {
		Page<DTOComputerDB> pageDTO = computerRepository.findByOrderByDtoCompanyDB_NameAsc( PageRequest.of( pageComputer.getNumber(), pageComputer.getSize() ) );
		List<Computer> listComputerOrdered = ComputerDTODatabaseMapper.convertToListComputer( pageDTO.getContent() );
		pageComputer.setContent( listComputerOrdered );
		return pageComputer;
	}
	
	public Computer getOneComputer( Long computerId ) throws NoSuchElementException {
		Optional<DTOComputerDB> optionalComputer = computerRepository.findById( computerId );
		LoggerManager.getLoggerConsole().info( "ComputerService -> getOneComputer() -> optionalComputer.get() = "  + optionalComputer.get() );
		return ComputerDTODatabaseMapper.convertToComputer( optionalComputer.get() );
	}
	
	public void createComputer( Computer computerToCreate ) {
		DTOComputerDB dtoComputerDB = ComputerDTODatabaseMapper.convertToDTOComputerDB( computerToCreate );
		computerRepository.save( dtoComputerDB );
	}
	
	public void editComputer( Computer computerEdited ) {
		DTOComputerDB dtoComputerDB = ComputerDTODatabaseMapper.convertToDTOComputerDB( computerEdited );
		computerRepository.save( dtoComputerDB );
	}
	
	public void deleteComputer( Long computerId ) throws InvalidComputerIdException {
		computerRepository.deleteById( computerId );
	}
	
	public void deleteSeveralComputers( List<Long> listComputerId ) throws InvalidComputerIdException {
		for( Long id : listComputerId ) {
			this.deleteComputer( id );
		}
	}
	
	public long getNumberOfComputer() {
		return computerRepository.count();
	}
}
