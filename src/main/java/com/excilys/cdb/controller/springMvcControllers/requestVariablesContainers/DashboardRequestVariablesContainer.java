package com.excilys.cdb.controller.springMvcControllers.requestVariablesContainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Component
@Scope( value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS )
//@RequestScope // Shortcut to the line above
public class DashboardRequestVariablesContainer {
	
	@Autowired
	private ModelAndView modelAndView;
	
	private int pageNumber = 1;
	private int nbOfComputer = 10;
	private String search = "";
	
	
	
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
	
	
}
