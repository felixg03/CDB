package com.excilys.cdb.controller.springMvcControllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.controller.springMvcControllers.requestVariablesContainers.DashboardRequestVariablesContainer;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Page;
import com.excilys.cdb.services.ComputerService;

@Controller
public class DashboardController {
	
	private ComputerService computerService;
	
	@Autowired
	private DashboardRequestVariablesContainer requestVariablesContainer;
	
	
	
	@Autowired
	public DashboardController( ComputerService computerService ) {
		this.computerService = computerService;
	}
	
	
	
	@GetMapping( value = "/dashboard" )
	public ModelAndView getDashboard( @RequestParam( required = false ) String page
								    , @RequestParam( required = false ) String nbOfComputer
								    , @RequestParam( required = false ) String search
								    , @RequestParam( required = false ) String orderByInput ) {
		
		Page<Computer> pageComputer = null;
		
		this.setNbOfComputer( nbOfComputer );
		this.setPageNumber( page );
		this.setSearch( search );
		
		if ( requestVariablesContainer.getSearch() == null ) {
			pageComputer = computerService.getPageComputerSearched( search );
		}
		else if ( orderByInput != null ) {
			if ( orderByInput.equals( "Order By Computer Name" ) ) {
				pageComputer = computerService
					  .getPageComputerOrderedByCompanyName (
						 new Page<Computer>(requestVariablesContainer.getNbOfComputer()
								 		  , requestVariablesContainer.getPageNumber()
								 		  )
						 );
			}
			else if (orderByInput.equals("Order By Computer Introduced Date")) {
				pageComputer = computerService
					  .getPageComputerOrderedByIntroducedDate (
			     	     new Page<Computer>(requestVariablesContainer.getNbOfComputer()
					  		 		      , requestVariablesContainer.getPageNumber()
								 	      )
					    );
			}
			else if (orderByInput.equals("Order By Computer Discontinued Date")) {
				pageComputer = computerService
					  .getPageComputerOrderedByDiscontinuedDate (
						 new Page<Computer>(requestVariablesContainer.getNbOfComputer()
								  		  , requestVariablesContainer.getPageNumber()
										   )
					    );
			}
			else if (orderByInput.equals("Order By Company Name")) {
				pageComputer = computerService
					  .getPageComputerOrderedByCompanyName (
					     new Page<Computer>(requestVariablesContainer.getNbOfComputer()
							  		      , requestVariablesContainer.getPageNumber()
										   )
					   );
			}
		}
		else {
			pageComputer = computerService.getPageComputer( 
					  new Page<Computer>( requestVariablesContainer.getNbOfComputer()
							  			, requestVariablesContainer.getPageNumber() 
							  			) 
					  );
		}
		
		handleRequest( pageComputer );
		return requestVariablesContainer.getModelAndView();
	}
	
	
	
	private void handleRequest( Page<Computer> pageComputer ) {
		Map<String, Object> model = requestVariablesContainer.getModelAndView().getModel();
		model.put( "nbTotalOfComputer", computerService.getNumberOfComputer() );
		model.put( "listDTOComputerDashboard", ComputerDTOMapper.convertToListDTOComputerDashboard( pageComputer.getContent() ) );
	}
	
	
	
	private void setPageNumber( String pageNumberString ) {
		if ( pageNumberString != null ) {
			try {
				requestVariablesContainer.setPageNumber( Integer.valueOf( pageNumberString ));
			}
			catch ( NumberFormatException nbFormatEx ) {
				LoggerManager.getLoggerFile().error( nbFormatEx.toString() );
			}
		}
	}
	
	
	
	private void setNbOfComputer( String nbOfComputerString ) {
		if ( nbOfComputerString != null ) {
			try {
				requestVariablesContainer.setNbOfComputer( Integer.valueOf( nbOfComputerString ));
			}
			catch ( NumberFormatException nbFormatEx ) {
				LoggerManager.getLoggerFile().error( nbFormatEx.toString() );
			}
		}
	}
	
	
	
	private void setSearch( String search ) {
		if ( search != null ) {
			requestVariablesContainer.setSearch( search );
		}
	}
	
}
