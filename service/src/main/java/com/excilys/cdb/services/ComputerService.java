package com.excilys.cdb.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.excilys.cdb.DTOs.DTODatabase.DTOComputerDB;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.customExceptions.InvalidUserInputException;
import com.excilys.cdb.enums.OrderByAttribute;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.mappers.DTODatabaseMappers.ComputerDTODatabaseMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.CustomPage;
import com.excilys.cdb.models.CustomPage.CustomPageBuilder;
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
	
	
	
	
	public List<Computer> getListComputers( Direction direction, OrderByAttribute orderByAttribute ) {
		return ComputerDTODatabaseMapper.convertToListComputer( 
									computerRepository.findAll( 
											Sort.by( direction, orderByAttribute.getOrderByAttributeString() ) 
									) 
				);
	}
	
	
	
	
	public List<Computer> getListComputerSearched( String searchInput, Direction direction, OrderByAttribute orderByAttribute ) {
		List<DTOComputerDB> listDTOComputerDB = computerRepository.findByNameContaining( searchInput, Sort.by( direction, orderByAttribute.getOrderByAttributeString() ) );
		return ComputerDTODatabaseMapper.convertToListComputer( listDTOComputerDB );
	}
	
	
	
	
	
	
	
	public CustomPage<Computer> getPageComputer( CustomPage<Computer> pageComputer ) {
		Page<DTOComputerDB> pageDTOComputerDB = computerRepository.findAll( PageRequest.of( pageComputer.getNumber()
																						  , pageComputer.getSize()
																						  , pageComputer.getDirection()
																						  , pageComputer.getOrderByAttribute().getOrderByAttributeString() ) );
		
		pageComputer.setContent( ComputerDTODatabaseMapper.convertToListComputer( pageDTOComputerDB.getContent() ) );
		return pageComputer;
	}
	
	
	
	
	public CustomPage<Computer> getPageComputerSearched( CustomPage<Computer> pageComputer ) {
		
		String searchInput = pageComputer.getSearch();
		int pageNumber = pageComputer.getNumber();
		int nbOfComputers = pageComputer.getSize();
		Direction direction = pageComputer.getDirection();
		OrderByAttribute orderByAttribute = pageComputer.getOrderByAttribute();
		
		Page<DTOComputerDB> page = computerRepository.findByNameContaining( 
											searchInput
										  , PageRequest.of( pageNumber, 
												  			nbOfComputers, 
												  			direction, 
												  			orderByAttribute.getOrderByAttributeString()
												  		   ) 
										  );
		
		List<Computer> listComputerFound = ComputerDTODatabaseMapper.convertToListComputer( page.getContent() );
		
		CustomPage<Computer> searchResult = new CustomPageBuilder<Computer>().setSize( nbOfComputers )
																			 .setNumber( pageNumber )
																			 .setContent( listComputerFound )
																			 .setOrderByAttribute( orderByAttribute )
																			 .setDirection( direction )
																			 .build();
		
		return searchResult;
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
	
	
	
	
	public void updateComputer( Computer computerEdited ) {
		DTOComputerDB dtoComputerDB = ComputerDTODatabaseMapper.convertToDTOComputerDB( computerEdited );
		computerRepository.save( dtoComputerDB );
	}
	
	
	
	
	public void deleteComputer( Long computerId ) {
		computerRepository.deleteById( computerId );
	}
	
	
	
	
	public void deleteSeveralComputers( List<Long> listComputerId ) {
		for( Long id : listComputerId ) {
			this.deleteComputer( id );
		}
	}
	
	
	public boolean checkComputerExistance( Long id ) {
		return computerRepository.existsById( id );
	}
	
	public long getNumberOfComputer() {
		return computerRepository.count();
	}
}
