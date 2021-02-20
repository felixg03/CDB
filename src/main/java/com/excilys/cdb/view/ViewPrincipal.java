package com.excilys.cdb.view;

import com.excilys.cdb.service.Model;
import com.excilys.cdb.controller.Controller;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewPrincipal {
	
	private Model model;
	private Controller controller;
	private ViewComputer viewComputer;
	private ViewCompany viewCompany;
	private Scanner scanner = new Scanner(System.in);
	
	public ViewPrincipal(Model model) {
		super();
		this.model = model;
		this.viewComputer = new ViewComputer();
		this.viewCompany = new ViewCompany();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	public ViewComputer getViewComputer() {
		return viewComputer;
	}
	public ViewCompany getViewCompany() {
		return viewCompany;
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
}