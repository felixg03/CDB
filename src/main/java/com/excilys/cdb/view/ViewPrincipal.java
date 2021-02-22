package com.excilys.cdb.view;

import com.excilys.cdb.service.Model;
import com.excilys.cdb.controller.Controller;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.customExceptions.OutOfRangeUserInputException;
import com.excilys.cdb.logger.LoggerManager;

import java.util.Scanner;

public class ViewPrincipal {
	
	private Model model;
	private Controller controller;
	private ViewComputer viewComputer = new ViewComputer();;
	private ViewCompany viewCompany = new ViewCompany();;
	private Scanner scanner = new Scanner(System.in);
	private final int LOWER_BOUND_INPUT = 1;
	private final int UPPER_BOUND_INPUT = 7;
	
	public ViewPrincipal(Model model) {
		super();
		this.model = model;
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
		
		while(loop) {
			System.out.println("What do you want to do ?");
			System.out.println("1 - List computers");
			System.out.println("2 - List companies");
			System.out.println("3 - Show one computer details");
			System.out.println("4 - Create a computer");
			System.out.println("5 - Update a computer");
			System.out.println("6 - Delete a computer");
			System.out.println("7 - Exit application");
			
			try {
				String userInput = scanner.nextLine();
				int userInputInteger = Integer.valueOf(userInput); // throws NumberFormatException
				if (userInputInteger < LOWER_BOUND_INPUT 
				 || userInputInteger > UPPER_BOUND_INPUT) {
					throw new OutOfRangeUserInputException(userInputInteger);
				}
				
				loop = controller.action(userInputInteger);
			}
			catch (NumberFormatException nbFormatEx) {
				LoggerManager.logInLogFile(nbFormatEx);
			}
			catch (OutOfRangeUserInputException outOfRangeUserInputEx) {
				LoggerManager.logInLogFile(outOfRangeUserInputEx);
			}
			catch (InvalidComputerIdException invCompIdEx) {
				LoggerManager.logInLogFile(invCompIdEx);
			}
		}
		
		System.out.println("Bye");
	}
}