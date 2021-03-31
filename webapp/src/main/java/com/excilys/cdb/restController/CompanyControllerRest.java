package com.excilys.cdb.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.DTORest.DTOCompanyRest;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.mappers.DTORestMappers.CompanyDTORestMapper;
import com.excilys.cdb.services.CompanyService;

@RestController
@RequestMapping( "/company" )
public class CompanyControllerRest {
	
	private CompanyService companyService;

	public CompanyControllerRest(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	
	
	
	@GetMapping( value = "/getListCompanies", produces = "application/json" )
	public ResponseEntity<List<DTOCompanyRest>> getListDtoCompanyRest() {
		List<DTOCompanyRest> listDtoCompanyRest = new ArrayList<>();
		listDtoCompanyRest = CompanyDTORestMapper.convertToListDTOCompanyRest( companyService.getListCompanies() );
		return new ResponseEntity<List<DTOCompanyRest>>( listDtoCompanyRest, HttpStatus.OK );
	}
	
	
	@GetMapping( value = "/doesCompanyExists", produces = "application/json" )
	public ResponseEntity<Boolean> checkIfCompanyExists( @RequestParam Long id ) {
		
		Boolean companyExists = companyService.checkCompanyId( id );
		
		if ( companyExists ) {
			return new ResponseEntity<Boolean>( companyExists, HttpStatus.FOUND );
		}
		else {
			LoggerManager.getLoggerFile().info( "\nCompanyControllerRest -> checkIfCompanyExists() -> else: companyExists = " + companyExists 
											  + "\nid = " + id.toString() );
			return new ResponseEntity<Boolean>( companyExists, HttpStatus.NOT_FOUND );
		}
	}
		
	
	@DeleteMapping( value = "/deleteCompany", produces = "application/json" )
	public ResponseEntity<String> deleteCompany(@RequestParam Long id ) {
		companyService.callCompanyDeletion( id );
		return new ResponseEntity<String>( "Company (id = " + id.toString() + ") deleted", HttpStatus.OK );
	}
	
}
