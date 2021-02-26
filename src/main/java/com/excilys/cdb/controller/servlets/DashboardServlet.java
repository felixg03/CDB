package com.excilys.cdb.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.DTOs.DTOComputerId;
import com.excilys.cdb.DTOs.mappers.ComputerDTOMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.Model;


@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Model model = Model.getInstance();
	private final CompanyService companyService = model.getCompanyService();
	private final ComputerService computerService = model.getComputerService();

   
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Computer> listComputer = this
									 .computerService
									 .getListComputers();
		
		List<DTOComputerId> listDTOComputerId = ComputerDTOMapper
											   .convertListComputerToListDTOComputerId(
													   listComputer);
		
		request.setAttribute("numberOfComputers", listDTOComputerId.size());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspViews/dashboard.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
