package com.excilys.cdb.controller.springMvcControllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.DTOs.DTOCompany;
import com.excilys.cdb.DTOs.DTOComputerAdd;
import com.excilys.cdb.DTOs.DTOComputerEdit;
import com.excilys.cdb.DTOs.mappers.CompanyDTOMapper;
import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.controller.springMvcControllers.validators.AddOrEditComputerValidator;
import com.excilys.cdb.controller.springMvcControllers.variables.DashboardVariables;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.customExceptions.InvalidUserInputException;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Page;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;

@Controller
public class ControllerSpringMvc {
	
	private CompanyService companyService;
	private ComputerService computerService;
	private AddOrEditComputerValidator addOrEditComputerValidator;
	private ModelAndView modelAndView;
	
	
	@Autowired
	private DashboardVariables dashboardVariables;
	
	@Autowired
	public ControllerSpringMvc(CompanyService companyService, ComputerService computerService,
			AddOrEditComputerValidator addOrEditComputerValidator, ModelAndView modelAndView) {
		super();
		this.companyService = companyService;
		this.computerService = computerService;
		this.addOrEditComputerValidator = addOrEditComputerValidator;
		this.modelAndView = modelAndView;
	}
	
	
	
	
	/*
	 * 		+------------------+
	 * 		|				   |
	 * 		|	 Dashboard	   |
	 * 		|				   |
	 * 		+------------------+
	 */
	
	@GetMapping( value = "/dashboard" )
	@ResponseBody
	public ModelAndView getDashboard( @RequestParam( required = false ) String page
								    , @RequestParam( required = false ) String nbOfComputer
								    , @RequestParam( required = false ) String search
								    , @RequestParam( required = false ) String orderByInput ) {
		
		this.setNbOfComputer( nbOfComputer );
		this.setPageNumber( page );
		this.setSearch( search );
		
		if ( dashboardVariables.getSearch() != "" ) {
			dashboardVariables.setPageComputer( computerService.getPageComputerSearched( search ) );
		}
		else if ( orderByInput != null ) {
			if ( orderByInput.equals( "Order By Computer Name" ) ) {
				dashboardVariables.setPageComputer( computerService
												   .getPageComputerOrderedByComputerName (
													  new Page<Computer>(dashboardVariables.getNbOfComputer()
															 		   , dashboardVariables.getPageNumber()
															 		   )
													   )
								 	 );
			}
			else if (orderByInput.equals("Order By Computer Introduced Date")) {
				dashboardVariables.setPageComputer( computerService
												   .getPageComputerOrderedByIntroducedDate (
													  new Page<Computer>(dashboardVariables.getNbOfComputer()
																	   , dashboardVariables.getPageNumber()
																	    )
														)
										);
			}
			else if (orderByInput.equals("Order By Computer Discontinued Date")) {
				dashboardVariables.setPageComputer( computerService
												   .getPageComputerOrderedByDiscontinuedDate (
													  new Page<Computer>(dashboardVariables.getNbOfComputer()
															 		   , dashboardVariables.getPageNumber()
															 		   )
													  )
									 );
			}
			else if (orderByInput.equals("Order By Company Name")) {
				dashboardVariables.setPageComputer( computerService
												   .getPageComputerOrderedByCompanyName (
													  new Page<Computer>(dashboardVariables.getNbOfComputer()
															 		   , dashboardVariables.getPageNumber()
															 		   )
													  )
									 );
			}
		}
		else {
			dashboardVariables.setPageComputer( computerService
											   .getPageComputer (
													  new Page<Computer>(dashboardVariables.getNbOfComputer()
															 		   , dashboardVariables.getPageNumber()
															 		   )
													  )
								 );
		}
		
		this.putGeneralParametersInModel();
		return this.modelAndView;
	}
	
	
	
	@PostMapping( value = "/dashboard" )
	@ResponseBody
	public ModelAndView postDashboard( @RequestParam String selection ) {
		List<String> listStringComputerId = Arrays.asList( selection.split(",") );
		List<Long> listComputerId = listStringComputerId.stream().map(Long::valueOf).collect(Collectors.toList());
		this.computerService.callListComputerDeletion(listComputerId);
		this.putGeneralParametersInModel();
		return this.modelAndView;
	}
	
	
	
	private void setPageNumber( String pageNumberString ) {
		if ( pageNumberString != null ) {
			try {
				dashboardVariables.setPageNumber( Integer.valueOf( pageNumberString ));
			}
			catch ( NumberFormatException nbFormatEx ) {
				LoggerManager.getLoggerFile().error( nbFormatEx.toString() );
			}
		}
	}
	
	
	
	private void setNbOfComputer( String nbOfComputerString ) {
		if ( nbOfComputerString != null ) {
			try {
				dashboardVariables.setNbOfComputer( Integer.valueOf( nbOfComputerString ));
			}
			catch ( NumberFormatException nbFormatEx ) {
				LoggerManager.getLoggerFile().error( nbFormatEx.toString() );
			}
		}
	}
	
	
	
	private void setSearch( String search ) {
		if ( search != null ) {
			dashboardVariables.setSearch( search );
		}
	}
	
	
	
	private void putGeneralParametersInModel() {
		Map<String, Object> model = this.modelAndView.getModel();
		model.put( "nbTotalOfComputer", computerService.getNumberOfComputer() );
		model.put( "listDTOComputerDashboard", ComputerDTOMapper
											  .convertToListDTOComputerDashboard( 
													  dashboardVariables.getPageComputer()
													  					.getContent() 
													 ) 
				 );
	}
	
	
	
	/*
	 * 		+---------------------+
	 * 		|					  |
	 * 		|	 Add Computer	  |
	 * 		|					  |
	 * 		+---------------------+
	 */
	
	@GetMapping( value = "/addComputer" )
	@ResponseBody
	public ModelAndView getAddComputer() {
		this.putListCompaniesInModel();
		return this.modelAndView;
	}
	
	
	
	@PostMapping( value = "/addComputer" )
	@ResponseBody
	public ModelAndView postAddComputer( String computerName, String introduced, String discontinued, String companyId ) {
		try {
			DTOComputerAdd dtoComputerAdd = new DTOComputerAdd();
			dtoComputerAdd = this.putFieldsIntoDTOComputerAdd(dtoComputerAdd, computerName, introduced, discontinued, companyId);
			this.addOrEditComputerValidator.validate(dtoComputerAdd);
			LoggerManager.getLoggerConsole().debug("ControllerMvc -> postAddComputer -> dtoComputerAdd = " + dtoComputerAdd);
			Computer computerToAdd = ComputerDTOMapper.convertToComputer(dtoComputerAdd);

			computerService.callComputerCreation(computerToAdd);
			
		} catch (InvalidUserInputException invalidUserInputEx) {
			this.modelAndView.getModel().put("inputException", invalidUserInputEx.getMessage());
		}
		this.putListCompaniesInModel();
		return this.modelAndView;
	}
	
	
	
	private void putListCompaniesInModel() {
		Map<String, Object> model = this.modelAndView.getModel();
		List<Company> listCompany = this.companyService.getListCompanies();
		List<DTOCompany> listDTOCompany = CompanyDTOMapper.convertListCompanyToListDTOCompany(listCompany);
		model.put("listDTOCompany", listDTOCompany);
	}
	
	private DTOComputerAdd putFieldsIntoDTOComputerAdd( DTOComputerAdd dtoComputerAdd, String computerName, String introduced, String discontinued, String companyId ) {
		dtoComputerAdd.name = computerName;
		dtoComputerAdd.introduced = introduced;
		dtoComputerAdd.discontinued = discontinued;
		dtoComputerAdd.companyId = companyId;
		return dtoComputerAdd;
	}
	
	
	
	/*
	 * 		+---------------------+
	 * 		|					  |
	 * 		|	 Edit Computer	  |
	 * 		|					  |
	 * 		+---------------------+
	 */
	
	
	@GetMapping( value = "/editComputer" ) 
	public ModelAndView getEditComputer( String id ) {
		try {
			Computer computer = this.computerService.getOneComputer( this.parseToLong( id ) );
			DTOComputerEdit dtoComputerEdit = ComputerDTOMapper.convertToDTOComputerEdit( computer );
			List<Company> listCompany = this.companyService
											.getListCompanies();
			List<DTOCompany> listDTOCompany = CompanyDTOMapper
							 				 .convertListCompanyToListDTOCompany(
							 							    listCompany
							 				 );
			Map<String, Object> model = this.modelAndView.getModel();
			model.put("dtoComputerEdit", dtoComputerEdit);
			model.put("listDTOCompany", listDTOCompany);
			
			return this.modelAndView;
		}
		catch ( InvalidComputerIdException invalidComputerIdEx ) {
			this.modelAndView.getModel().put( "invalidComputerIdException", invalidComputerIdEx.getMessage() );
		}
		
		return this.modelAndView;
	}
	
	
	
	@PostMapping( value = "/editComputer" )
	public ModelAndView postEditComputer( String id, String computerName, String introduced, String discontinued, String companyId, String companyName ) {
		try {
			DTOComputerEdit dtoComputerEdit = new DTOComputerEdit();
			dtoComputerEdit = this.putFieldsIntoDTOComputerEdit( dtoComputerEdit
																			   , id
																			   , computerName
																			   , introduced
																			   , discontinued
																			   , companyId
																			   , companyName);
			this.addOrEditComputerValidator.validate(dtoComputerEdit);
			Computer computerEdited = ComputerDTOMapper.convertToComputer( dtoComputerEdit );
			
			computerService.callComputerEdition(computerEdited);
		} 
		catch ( InvalidUserInputException invalidUserInputEx ) {
			this.modelAndView.getModel().put("inputException", "Operation failed: " + invalidUserInputEx.getMessage());
		}
		
		return this.modelAndView;
	}
	
	
	
	private long parseToLong( String input ) throws InvalidComputerIdException {
		if (input == "" || input == null) {
			throw new InvalidComputerIdException( -1 );
		}
		else {
			return Long.valueOf(input);
		}
	}
	
	
	
	private DTOComputerEdit putFieldsIntoDTOComputerEdit( DTOComputerEdit dtoComputerEdit
													    , String id
													    , String computerName
													    , String introduced
													    , String discontinued
													    , String companyId
													    , String companyName) {
		
		dtoComputerEdit.id = id;
		dtoComputerEdit.name = computerName;
		dtoComputerEdit.introduced = introduced;
		dtoComputerEdit.discontinued = discontinued;
		dtoComputerEdit.companyId = companyId;
		dtoComputerEdit.companyName = companyName;
		
		return dtoComputerEdit;
	}
}
