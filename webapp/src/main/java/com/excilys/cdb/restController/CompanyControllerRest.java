package com.excilys.cdb.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.DTORest.DTOCompanyRest;
import com.excilys.cdb.mappers.DTORestMappers.CompanyDTORestMapper;
import com.excilys.cdb.services.CompanyService;

@RestController
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
	
	
}
