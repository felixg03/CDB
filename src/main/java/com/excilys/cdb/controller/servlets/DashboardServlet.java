package com.excilys.cdb.controller.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.cdb.DTOs.DTOComputerAddComputer;
import com.excilys.cdb.DTOs.DTOComputerDashboard;
import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Page;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.Model;


@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Model model = Model.getInstance();
	private final CompanyService companyService = model.getCompanyService();
	private final ComputerService computerService = model.getComputerService();
	
	private static final int COMPUTERS_PER_PAGE_DEFAULT_VALUE = 10;
	private static final int FIRST_PAGE = 1;
	

   
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.handleRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.handleRequest(request, response);
	}
	
	
	
	
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbOfComputersInPage = getNbOfComputersInPage(request);
		int pageNumber = getPageNumber(request);
		Page<Computer> pageComputer = this
									 .computerService
									 .getPageComputer(
											 new Page<Computer>(nbOfComputersInPage
													 		  , pageNumber)
									 );
		
		long numberOfComputer = this
							  .computerService
							  .getNumberOfComputer();

		List<DTOComputerDashboard> listDTOComputerDashboard = ComputerDTOMapper
													.convertToListDTOComputerDashboard(
															pageComputer.getContent());
		
		request.setAttribute("numberOfComputers", numberOfComputer);
		request.setAttribute("listDTOComputer", listDTOComputerDashboard);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspViews/dashboard.jsp").forward(request, response);
	}
	
	private int getPageNumber(HttpServletRequest request) {
		String pageNumberString = request.getParameter("page");
		if (pageNumberString == "" || pageNumberString == null) {
			return FIRST_PAGE;
		}
		else {
			return Integer.valueOf(pageNumberString);
		}
	}
	
	private int getNbOfComputersInPage(HttpServletRequest request) {
		String nbOfComputersInPageString = request.getParameter("nbComputers");
		if (nbOfComputersInPageString == "" || nbOfComputersInPageString == null) {
			return COMPUTERS_PER_PAGE_DEFAULT_VALUE;
		}
		else {
			return Integer.valueOf(nbOfComputersInPageString);
		}
	}
}
