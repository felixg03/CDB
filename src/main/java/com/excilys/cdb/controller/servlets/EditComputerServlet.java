package com.excilys.cdb.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.DTOs.DTOCompany;
import com.excilys.cdb.DTOs.DTOComputerEdit;
import com.excilys.cdb.DTOs.mappers.CompanyDTOMapper;
import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.Model;


@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final Model model = Model.getInstance();
	private final ComputerService computerService = model.getComputerService();
	private final CompanyService companyService = model.getCompanyService();
	
    public EditComputerServlet() {
        super();
    }

    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// this will be better handled after VALIDATION BACK 
		Computer computerToEdit = null;
		try {
			 computerToEdit= this.computerService.getOneComputer(
					 					this.parseToLong(request.getParameter("id")));
		}
		catch(InvalidComputerIdException invalidComputerIdEx) {
			invalidComputerIdEx.printStackTrace();
		}
		DTOComputerEdit dtoComputerEdit = ComputerDTOMapper.convertToDTOComputerEdit(computerToEdit);
				

				
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

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DTOComputerEdit dtoComputerEdit = this.getDTOComputerEditFromRequest(request);
		Computer computerEdited = ComputerDTOMapper.convertToComputer(dtoComputerEdit);
		System.out.println("id = " + computerEdited.getId());
		System.out.println("name = " + computerEdited.getName());
		System.out.println("introduced = " + computerEdited.getIntroduced());
		System.out.println("discontinued = " + computerEdited.getDiscontinued());
		System.out.println("companyId = " + computerEdited.getCompany().getId());
		System.out.println("companyName = " + computerEdited.getCompany().getName());
		computerService.callComputerEdition(computerEdited);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspViews/editComputer.jsp").forward(request, response);	
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
		String id = request.getParameter("id");
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName = request.getParameter("companyName");
		
		DTOComputerEdit dtoComputerEdit = new DTOComputerEdit();
		
		dtoComputerEdit.id = id;
		dtoComputerEdit.name = name;
		dtoComputerEdit.introduced = introduced;
		dtoComputerEdit.discontinued = discontinued;
		dtoComputerEdit.companyId = companyId;
		dtoComputerEdit.companyName = companyName;
		
		return dtoComputerEdit;
	}
}
