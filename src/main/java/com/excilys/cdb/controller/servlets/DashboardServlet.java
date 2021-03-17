package com.excilys.cdb.controller.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.loggers.LoggerManager;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Page;
import com.excilys.cdb.services.ComputerService;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService computerService;
	
	private static final int COMPUTERS_PER_PAGE_DEFAULT_VALUE = 10;
	private static final int FIRST_PAGE = 1;
	

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoggerManager.getLoggerConsole().debug("Entered doGet()");
		Page<Computer> pageComputer = null;
		String searchInput = request.getParameter("search");
		if (searchInput != null) {
			pageComputer = this.computerService.getPageComputerSearched(searchInput);
			this.handleRequest(request, response, pageComputer);
		}
		else {
			pageComputer = this.getPageComputer(request);
			this.handleRequest(request, response, pageComputer);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonClicked = request.getParameter("orderByButton");
		
		if (buttonClicked != null) {
			if (buttonClicked.equals("Order By Computer Name")) {
				Page<Computer> pageComputerOrderedByComputerName = this.getPageComputerOrderedByComputerName(request);
				this.handleRequest(request, response, pageComputerOrderedByComputerName);
			}
			else if (buttonClicked.equals("Order By Computer Introduced Date")) {
				Page<Computer> pageComputerOrderedByIntroducedDate = this.getPageComputerOrderedByIntroducedDate(request);
				this.handleRequest(request, response, pageComputerOrderedByIntroducedDate);
			}
			else if (buttonClicked.equals("Order By Computer Discontinued Date")) {
				Page<Computer> pageComputerOrderedByDiscontinuedDate = this.getPageComputerOrderedByDiscontinuedDate(request);
				this.handleRequest(request, response, pageComputerOrderedByDiscontinuedDate);
			}
			else if (buttonClicked.equals("Order By Company Name")) {
				Page<Computer> pageComputerOrderedByCompanyName = this.getPageComputerOrderedByCompanyName(request);
				this.handleRequest(request, response, pageComputerOrderedByCompanyName);
			}
			else {
				doGet(request, response);
			}
		}
		else {
			List<String> listStringComputerId = Arrays.asList(request.getParameter("selection").split(","));
			List<Long> listComputerId = listStringComputerId.stream().map(Long::valueOf).collect(Collectors.toList());
			this.computerService.callListComputerDeletion(listComputerId);
			doGet(request, response);
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
	
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response, Page<Computer> pageComputer) throws ServletException, IOException {
		request.setAttribute("nbTotalOfComputer", this.computerService.getNumberOfComputer());
		request.setAttribute("listDTOComputerDashboard", ComputerDTOMapper.convertToListDTOComputerDashboard(pageComputer.getContent()));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspViews/dashboard.jsp").forward(request, response);
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
	
	private Page<Computer> getPageComputerOrderedByIntroducedDate(HttpServletRequest request) {
		int nbOfComputersInPage = getNbOfComputersInPage(request);
		int pageNumber = getPageNumber(request);
		Page<Computer> pageComputerOrderedByIntroducedDate = this
												 			.computerService
												 			.getPageComputerOrderedByIntroducedDate(
												 			 	new Page<Computer>(nbOfComputersInPage
												 						 		 , pageNumber)
												 			);
		return pageComputerOrderedByIntroducedDate;
	}
	
	private Page<Computer> getPageComputerOrderedByDiscontinuedDate(HttpServletRequest request) {
		int nbOfComputersInPage = getNbOfComputersInPage(request);
		int pageNumber = getPageNumber(request);
		Page<Computer> pageComputerOrderedByIntroducedDate = this
												 			.computerService
												 			.getPageComputerOrderedByDiscontinuedDate(
												 			 	new Page<Computer>(nbOfComputersInPage
												 						 		 , pageNumber)
												 			);
		return pageComputerOrderedByIntroducedDate;
	}
	
	private Page<Computer> getPageComputerOrderedByCompanyName(HttpServletRequest request) {
		int nbOfComputersInPage = getNbOfComputersInPage(request);
		int pageNumber = getPageNumber(request);
		Page<Computer> pageComputer = this
									 .computerService
									 .getPageComputerOrderedByCompanyName(
											 new Page<Computer>(nbOfComputersInPage
													 		  , pageNumber)
									 );
		return pageComputer;
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
