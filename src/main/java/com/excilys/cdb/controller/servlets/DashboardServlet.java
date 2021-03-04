package com.excilys.cdb.controller.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.cdb.DTOs.DTOComputerAdd;
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
	private final ComputerService computerService = model.getComputerService();
	
	private static final int COMPUTERS_PER_PAGE_DEFAULT_VALUE = 10;
	private static final int FIRST_PAGE = 1;
	

   
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page<Computer> pageComputer = this.getPageComputer(request);

		System.out.println("SNUIRFL");
		this.handleRequest(request, response, pageComputer);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonClicked = request.getParameter("orderByButton");
		if (buttonClicked.equals("Order By Computer Name")) {
			Page<Computer> pageComputerOrderedByComputerName = this.getPageComputerOrderedByComputerName(request);
			this.handleRequest(request, response, pageComputerOrderedByComputerName);
			System.out.println("WAZA");
		}
		else {
			doGet(request, response);
		}
		
	}
	
	
	
	
	private Page<Computer> getPageComputer(HttpServletRequest request) {
		int nbOfComputersInPage = getNbOfComputersInPage(request);
		int pageNumber = getPageNumber(request);
		Page<Computer> pageComputer = this
									 .computerService
									 .getPageComputer(
											 new Page<Computer>(nbOfComputersInPage
													 		  , pageNumber)
									 );
		return pageComputer;
	}
	
	private Page<Computer> getPageComputerOrderedByComputerName(HttpServletRequest request) {
		int nbOfComputersInPage = getNbOfComputersInPage(request);
		int pageNumber = getPageNumber(request);
		Page<Computer> pageComputerOrderedByComputerName = this
											 			  .computerService
											 			  .getPageComputerOrderedByComputerName(
											 			 	  new Page<Computer>(nbOfComputersInPage
											 						 		   , pageNumber)
											 			  );
		return pageComputerOrderedByComputerName;
	}
	
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response, Page<Computer> pageComputer) throws ServletException, IOException {
		request.setAttribute("nbTotalOfComputer", this.computerService.getNumberOfComputer());
		request.setAttribute("listDTOComputerDashboard", ComputerDTOMapper.convertToListDTOComputerDashboard(pageComputer.getContent()));
		
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
