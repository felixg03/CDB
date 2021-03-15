package com.excilys.cdb.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.DTOs.DTOCompany;
import com.excilys.cdb.DTOs.DTOComputerAdd;
import com.excilys.cdb.DTOs.DTOComputerEdit;
import com.excilys.cdb.DTOs.mappers.CompanyDTOMapper;
import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.controller.servlets.validators.AddOrEditComputerValidator;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.customExceptions.InvalidUserInputException;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;
import com.mysql.cj.log.Log;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class EditComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private AddOrEditComputerValidator addOrEditComputerValidator;
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// this will be better handled after VALIDATION BACK 
		Computer computerToEdit = null;
		try {
			 computerToEdit= this.computerService.getOneComputer(
					 					this.parseToLong(request.getParameter("id")));
			 LoggerManager.getViewLoggerConsole().debug( "EditComputerServlet --> doGet() --> computerToEdit.getId() = " + computerToEdit.getId() );
		}
		catch(InvalidComputerIdException invalidComputerIdEx) {
			invalidComputerIdEx.printStackTrace();
		}
		DTOComputerEdit dtoComputerEdit = ComputerDTOMapper.convertToDTOComputerEdit( computerToEdit );
				
		LoggerManager.getViewLoggerConsole().debug( "EditComputerServlet --> doGet() --> dtoComputerEdit.id = " + dtoComputerEdit.id );
		
		List<Company> listCompany = this.companyService
										.getListCompanies();
		List<DTOCompany> listDTOCompany = CompanyDTOMapper
								 				.convertListCompanyToListDTOCompany(
								 							listCompany
								 				);
				
		request.setAttribute("dtoComputerEdit", dtoComputerEdit);
		request.setAttribute("listDTOCompany", listDTOCompany);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspViews/editComputer.jsp").forward(request, response);		
		
	}

	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DTOComputerEdit dtoComputerEdit = this.getDTOComputerEditFromRequest(request);
			this.addOrEditComputerValidator.validate(dtoComputerEdit);
			Computer computerEdited = ComputerDTOMapper.convertToComputer( dtoComputerEdit );
			
			LoggerManager.getViewLoggerConsole().debug( "EditCompuerServlet --> doPost()--> computerEdited = " + computerEdited.toString() );
			computerService.callComputerEdition(computerEdited);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspViews/editComputer.jsp").forward(request, response);	
		} 
		catch ( InvalidUserInputException invalidUserInputEx ) {
			request.setAttribute("inputException", invalidUserInputEx.getMessage());
		}
	}
	
	
	
	
	/*
	 *   ##########################
	 *   ###					###
	 *   ### 	ServletConfig   ###
	 *   ###					###
	 *   ##########################
	 */
	
	@Override
	public void init( ServletConfig servletConfig ) throws ServletException {
		SpringBeanAutowiringSupport
		.processInjectionBasedOnServletContext(
				this, servletConfig.getServletContext() );
		
		super.init( servletConfig );
	}
	
	
	
	/*
	 * ######################
	 * ###				  ###
	 * ###     TOOLS      ###
	 * ###				  ###
	 * ######################
	 */
	private long parseToLong(String stringToParse) {
		if (stringToParse == "" || stringToParse == null) {
			return 0;
		}
		else {
			return Long.valueOf(stringToParse);
		}
	}
	
	private DTOComputerEdit getDTOComputerEditFromRequest(HttpServletRequest request) {
		DTOComputerEdit dtoComputerEdit = new DTOComputerEdit();
		
		dtoComputerEdit.id = request.getParameter("id");
		dtoComputerEdit.name = request.getParameter("computerName");
		dtoComputerEdit.introduced = request.getParameter("introduced");
		dtoComputerEdit.discontinued = request.getParameter("discontinued");
		dtoComputerEdit.companyId = request.getParameter("companyId");
		dtoComputerEdit.companyName = request.getParameter("companyName");
		
		return dtoComputerEdit;
	}
}
