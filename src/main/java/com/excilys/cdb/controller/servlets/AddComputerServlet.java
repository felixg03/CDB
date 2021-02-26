package com.excilys.cdb.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.DTOs.DTOCompany;
import com.excilys.cdb.DTOs.DTOComputerCreation;
import com.excilys.cdb.DTOs.mappers.CompanyDTOMapper;
import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.Model;

@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final Model model = Model.getInstance();
	private final CompanyService companyService = model.getCompanyService();
	private final ComputerService computerService = model.getComputerService();

    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request
					   , HttpServletResponse response) 
							   throws ServletException, IOException {
		
		handleRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request
			 			, HttpServletResponse response) 
			 					throws ServletException, IOException {
		
		DTOComputerCreation dtoComputer = getDTOComputerCreationFromRequest(request);
		
		Computer computerWithNoIdYet = ComputerDTOMapper
									  .convertDTOComputerCreationToComputer(
											  dtoComputer);
		
		computerService.callComputerCreationInDaoComputer(computerWithNoIdYet);
		
		handleRequest(request, response);
	}
	
	
	
	
	
	
	private void handleRequest(HttpServletRequest request
							 , HttpServletResponse response) 
									 throws ServletException, IOException {
		
		List<Company> listCompany = this
				  				   .companyService
				  				   .getListCompanies();
		List<DTOCompany> listDTOCompany = CompanyDTOMapper
										 .convertListCompanyToListDTOCompany(
												 listCompany
										 );
		request.setAttribute("listDTOCompany", listDTOCompany);
		
		this.getServletContext()
		    .getRequestDispatcher(
		    		"/WEB-INF/jspViews/addComputer.jsp"
		    )
		    .forward(request, response);
	}
	
	
	
	
	
	
	
	private DTOComputerCreation getDTOComputerCreationFromRequest(HttpServletRequest request) {
		String name = request.getParameter("computerName");
		String introducedString = request.getParameter("introduced");
		String discontinuedString = request.getParameter("discontinued");
		String companyIdString = request.getParameter("company");
		
		return new DTOComputerCreation(name
						  		     , introducedString
								     , discontinuedString
								     , companyIdString);
	}

}
