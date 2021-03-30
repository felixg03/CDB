package com.excilys.cdb.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.DTORest.DTOComputerRest;
import com.excilys.cdb.mappers.DTORestMappers.ComputerDTORestMapper;
import com.excilys.cdb.services.ComputerService;

@RestController
public class ComputerControllerRest {
	
	private ComputerService computerService;

	public ComputerControllerRest( ComputerService computerService ) {
		super();
		this.computerService = computerService;
	}
	
	
	
	@GetMapping( value = "/getComputer", produces = "application/json" )
	public ResponseEntity<DTOComputerRest> getDTOComputerRest( @RequestParam( value = "id" ) Long id  ) {
		
		Optional<DTOComputerRest> dtoComputerRest = Optional.empty();
		
		try {
			dtoComputerRest = Optional.of( ComputerDTORestMapper.convertToDTOComputerRest( computerService.getOneComputer( id ) ) );
			return new ResponseEntity<DTOComputerRest>( dtoComputerRest.get() , HttpStatus.OK );
		}
		catch( NoSuchElementException noSuchElemEx ) {
			return new ResponseEntity<DTOComputerRest>( HttpStatus.BAD_REQUEST );
		}
	}
	
	
	
	@GetMapping( value = "/getListComputers", produces = "application/json" )
	public ResponseEntity<List<DTOComputerRest>> getListDTOComputerRest() {
		List<DTOComputerRest> listDtoComputerRest = new ArrayList<>();
		listDtoComputerRest = ComputerDTORestMapper.convertToListDTOComputerRest( computerService.getListComputers() );
		return new ResponseEntity<List<DTOComputerRest>>( listDtoComputerRest, HttpStatus.OK );
	}
}
