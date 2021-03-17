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
import com.excilys.cdb.DTOs.mappers.CompanyDTOMapper;
import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.controller.webSpringMvc.validators.AddOrEditComputerValidator;
import com.excilys.cdb.customExceptions.InvalidUserInputException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private AddOrEditComputerValidator addOrEditComputerValidator;
	



	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletConfig.getServletContext());

		super.init(servletConfig);
	}

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		handleRequest(request, response);
	}

	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DTOComputerAdd dtoComputerAdd = getDTOComputerAdd(request);
			this.addOrEditComputerValidator.validate(dtoComputerAdd);

			Computer computerToAdd = ComputerDTOMapper.convertToComputer(dtoComputerAdd);

			computerService.callComputerCreation(computerToAdd);
		} catch (InvalidUserInputException invalidUserInputEx) {
			request.setAttribute("inputException", invalidUserInputEx.getMessage());
		}
		handleRequest(request, response);
	}

	

	private void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Company> listCompany = this.companyService.getListCompanies();
		List<DTOCompany> listDTOCompany = CompanyDTOMapper.convertListCompanyToListDTOCompany(listCompany);
		request.setAttribute("listDTOCompany", listDTOCompany);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jspViews/addComputer.jsp").forward(request, response);
	}

	private DTOComputerAdd getDTOComputerAdd(HttpServletRequest request) {
		String name = request.getParameter("computerName");
		String introducedString = request.getParameter("introduced");
		String discontinuedString = request.getParameter("discontinued");
		String companyIdString = request.getParameter("companyId");

		DTOComputerAdd dtoComputerAdd = new DTOComputerAdd();

		dtoComputerAdd.name = name;
		dtoComputerAdd.introduced = introducedString;
		dtoComputerAdd.discontinued = discontinuedString;
		dtoComputerAdd.companyId = companyIdString;

		return dtoComputerAdd;
	}

}
