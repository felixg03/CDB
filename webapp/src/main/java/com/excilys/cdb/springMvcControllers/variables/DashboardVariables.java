package com.excilys.cdb.springMvcControllers.variables;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.CustomPage;

@Component
@RequestScope
//@Scope( value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS )
public class DashboardVariables {
	
	private ModelAndView modelAndView = new ModelAndView();
	
	private int pageNumber = 1;
	private int nbOfComputer = 10;
	private String search = "";
	
	private CustomPage<Computer> pageComputer = new CustomPage<>(nbOfComputer, pageNumber);
	
	
	public ModelAndView getModelAndView() {
		return modelAndView;
	}
	
	
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
	
	public int getNbOfComputer() {
		return nbOfComputer;
	}
	public void setNbOfComputer(int computerNumber) {
		this.nbOfComputer = computerNumber;
	}
	
	
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}



	public CustomPage<Computer> getPageComputer() {
		return pageComputer;
	}
	public void setPageComputer(CustomPage<Computer> pageComputer) {
		this.pageComputer = pageComputer;
	}
	
	
	
}
