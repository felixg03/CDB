package com.excilys.cdb.restController;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.DTORest.DTOComputerRest;
import com.excilys.cdb.customExceptions.InvalidUserInputException;
import com.excilys.cdb.enums.OrderByAttribute;
import com.excilys.cdb.mappers.DTORestMappers.ComputerDTORestMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.CustomPage;
import com.excilys.cdb.models.CustomPage.CustomPageBuilder;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.userInputValidators.DTOComputerRestValidator;

@RestController
@RequestMapping( "/computer" )
public class ComputerControllerRest {
	
	private ComputerService computerService;
	private DTOComputerRestValidator validator;
	
	private static final String DEFAULT_PAGE_NUMBER = "0";
	private static final String DEFAULT_NB_OF_COMPUTERS = "10";
	private static final String DEFAULT_ORDER_BY_ATTRIBUTE = "ID";
	private static final String DEFAULT_DIRECTION = "ASC";

	public ComputerControllerRest( ComputerService computerService, DTOComputerRestValidator validator ) {
		super();
		this.computerService = computerService;
		this.validator = validator;
	}
	
	
	
	@GetMapping( value = "/getComputer", produces = "application/json" )
	public ResponseEntity<DTOComputerRest> getDTOComputerRest( @RequestParam Long id ) {
		
		Optional<DTOComputerRest> dtoComputerRest = Optional.empty();
		
		try {
			dtoComputerRest = Optional.of( ComputerDTORestMapper.convertToDTOComputerRest( computerService.getOneComputer( id ) ) );
			return new ResponseEntity<DTOComputerRest>( dtoComputerRest.get() , HttpStatus.OK );
		}
		catch( NoSuchElementException noSuchElemEx ) {
			return new ResponseEntity<DTOComputerRest>( HttpStatus.NOT_FOUND );
		}
	}
	
	
	
	
	
	@GetMapping( value = "/getList", produces = "application/json" )
	public ResponseEntity<List<DTOComputerRest>> getListDTOComputerRest( @RequestParam( value = "orderBy", defaultValue = DEFAULT_ORDER_BY_ATTRIBUTE ) OrderByAttribute orderByAttribute
																	   , @RequestParam( defaultValue = DEFAULT_DIRECTION ) Direction direction ) {
	
		List<DTOComputerRest> listDtoComputerRest = new ArrayList<>();
		listDtoComputerRest = ComputerDTORestMapper.convertToListDTOComputerRest( computerService.getListComputers( direction, orderByAttribute ) );
		return new ResponseEntity<List<DTOComputerRest>>( listDtoComputerRest, HttpStatus.OK );
	}
	
	
	
	@GetMapping( value = "/getList/search", produces = "application/json" )
	public ResponseEntity<List<DTOComputerRest>> getListDTOComputerRestSearched( @RequestParam String searchInput
																			   , @RequestParam( value = "orderBy", defaultValue = DEFAULT_ORDER_BY_ATTRIBUTE ) OrderByAttribute orderByAttribute
																			   , @RequestParam( defaultValue = DEFAULT_DIRECTION ) Direction direction) {
		
		List<Computer> searchResult = computerService.getListComputerSearched( searchInput, direction, orderByAttribute );
		
		if ( !searchResult.isEmpty() ) {
			return new ResponseEntity<List<DTOComputerRest>>( ComputerDTORestMapper.convertToListDTOComputerRest( searchResult ), HttpStatus.FOUND );
		}
		else {
			return new ResponseEntity<List<DTOComputerRest>>( HttpStatus.NOT_FOUND );
		}
	}
	
	
	
	@GetMapping( value = "/getPage", produces = "application/json" )
	public ResponseEntity<List<DTOComputerRest>> getPageDTOComputerRest( @RequestParam( value = "page", defaultValue = DEFAULT_PAGE_NUMBER ) int pageNumber
																	   , @RequestParam( defaultValue = DEFAULT_NB_OF_COMPUTERS ) int nbOfComputers
																	   , @RequestParam( value = "orderBy", defaultValue = DEFAULT_ORDER_BY_ATTRIBUTE ) OrderByAttribute orderbyAttribute
																	   , @RequestParam( defaultValue = DEFAULT_DIRECTION ) Direction direction ) {
		
		CustomPage<Computer> page = new CustomPageBuilder<Computer>()
									   .setSize( nbOfComputers )
									   .setNumber( pageNumber )
									   .setOrderByAttribute( orderbyAttribute )
									   .setDirection( direction )
									   .build();
		
		page = computerService.getPageComputer( page );
		return new ResponseEntity<List<DTOComputerRest>>( ComputerDTORestMapper.convertToListDTOComputerRest( 
																						page.getContent() 
																				), 
														  HttpStatus.OK 
														);
	}
	
	
	
	
	
	@GetMapping( value = "/getPage/search", produces = "application/json" )
	public ResponseEntity<List<DTOComputerRest>> getPageDTOComputerRest( 
												@RequestParam String searchInput
											  , @RequestParam( value = "page", defaultValue = DEFAULT_PAGE_NUMBER ) int pageNumber
											  , @RequestParam( defaultValue = DEFAULT_NB_OF_COMPUTERS ) int nbOfComputers
											  , @RequestParam( value = "orderBy", defaultValue = DEFAULT_ORDER_BY_ATTRIBUTE ) OrderByAttribute orderbyAttribute
											  , @RequestParam( defaultValue = DEFAULT_DIRECTION ) Direction direction ) {
		
		CustomPage<Computer> page = new CustomPageBuilder<Computer>()
									   .setSize( nbOfComputers )
									   .setNumber( pageNumber )
									   .setSearch( searchInput )
									   .setOrderByAttribute( orderbyAttribute )
									   .setDirection( direction )
									   .build();

		page = computerService.getPageComputerSearched( page );
		
		if ( !page.getContent().isEmpty() ) {
		return new ResponseEntity<List<DTOComputerRest>>( ComputerDTORestMapper
														 .convertToListDTOComputerRest( page.getContent() )
														 
													   , HttpStatus.FOUND 
										);
		}
		else {
			return new ResponseEntity<List<DTOComputerRest>>( HttpStatus.NOT_FOUND );
		}
	}
	
	
	
	
	@PostMapping( value = "/add", produces = "application/json" )
	public ResponseEntity<String> createComputer( @RequestBody DTOComputerRest dtoComputerRest ) {
		try {
			validator.validate( dtoComputerRest );
			computerService.createComputer( ComputerDTORestMapper.convertToComputer( dtoComputerRest ) );
			return new ResponseEntity<String>( HttpStatus.CREATED );
		}
		catch( InvalidUserInputException | DateTimeParseException ex ) {
			return new ResponseEntity<String>( ex.getMessage(), HttpStatus.BAD_REQUEST );
		}
	}
	
	
	
	
	@PutMapping( value = "/edit", produces = "application/json" )
	public ResponseEntity<String> updateComputer( @RequestBody DTOComputerRest dtoComputerRest ) {
		try {
			validator.validate( dtoComputerRest );
			computerService.updateComputer( ComputerDTORestMapper.convertToComputer( dtoComputerRest ) );
			return new ResponseEntity<String>( HttpStatus.OK );
		}
		catch( InvalidUserInputException | DateTimeParseException ex ) {
			return new ResponseEntity<String>( ex.getMessage(), HttpStatus.BAD_REQUEST );
		}
	}
	
	
	
	@DeleteMapping( value = "/delete", produces = "application/json" )
	public ResponseEntity<String> deleteComputer( @RequestParam Long id ) {
		if ( computerService.checkComputerExistance( id ) != false ) {
			computerService.deleteComputer( id );
			return new ResponseEntity<String>( HttpStatus.OK );
		}
		else {
			return new ResponseEntity<String>( "Computer id is null", HttpStatus.BAD_REQUEST );
		}
	}
	
	
	
	@DeleteMapping( value = "/delete/several", produces = "application/json" )
	public ResponseEntity<String> deleteSeveralComputers( @RequestBody List<Long> idList ) {
		computerService.deleteSeveralComputers( idList );
		return new ResponseEntity<String>( HttpStatus.OK );
	}
}
