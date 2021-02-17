package com.excilys.formation.java.view;

import com.excilys.formation.java.service.Model;
import com.excilys.formation.java.controller.Controller;
import com.excilys.formation.java.model.Company;
import com.excilys.formation.java.model.Computer;

import java.util.List;
import java.util.Scanner;

public class View {
	
	private Model model;
	private Controller controller;
	private Scanner scanner = new Scanner(System.in);
	
	public View(Model model) {
		super();
		this.model = model;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void displayCli() {
		System.out.println("Hi");
		boolean loop = true;
		int userInput = 0;
		
		while(loop) {
			System.out.println("What do you want to do ?");
			System.out.println("1 - List computers");
			System.out.println("2 - List companies");
			System.out.println("3 - Show one computer details");
			System.out.println("4 - Create a computer");
			System.out.println("5 - Update a computer");
			System.out.println("6 - Delete a computer");
			System.out.println("7 - Exit application");
			
			userInput = scanner.nextInt();
			loop = controller.action(userInput);
		}
		
		System.out.println("Bye");
	}
	
	
	public void displayListComputers(List<Computer> listComputers) {
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("RESULT");
		System.out.println("List of computers:");
		System.out.println();
		for (Computer computer : listComputers) {
			System.out.println(computer.getId() 
							   + " | " 
							   + computer.getName()
							   + " | " 
							   + computer.getIntroduced()
							   + " | " 
							   + computer.getDiscontinued()
							   + " | " 
							   + computer.getCompany_id()
							   );
		}
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
	}
	
	public void displayListCompanies(List<Company> listCompanies) {
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("RESULT");
		System.out.println("List of companies:");
		System.out.println();
		for (Company company : listCompanies) {
			System.out.println(company.getId() 
							   + " | " 
							   + company.getName()
							   );
		}
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
	}
	
	public void displayOneComputerDetails(Computer computer) {
		
	}
	
	public void displayResultComputerCreation(Computer computer) {
		
	}
	
	public void displayResultComputerUpdate(Computer computer) {
		
	}
	
	/* String passed in argument instead of Computer because in 
	 * this scenario the computer has been deleted 
	 * */
	public void displayResultComputerDeletion(String result) {
		
	}
	
}
