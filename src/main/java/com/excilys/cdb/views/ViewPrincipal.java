package com.excilys.cdb.views;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.cdb.controller.CliController;
import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.customExceptions.OutOfRangeUserInputException;
import com.excilys.cdb.loggers.LoggerManager;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
public class ViewPrincipal {
	
	@Autowired
	private CliController controller;
	private Scanner scanner = new Scanner(System.in);
	private final int LOWER_BOUND_INPUT = 1;
	private final int UPPER_BOUND_INPUT = 7;

	
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
			System.out.println("7 - Delete a company");
			System.out.println("8 - Exit application");
			
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
				LoggerManager.getViewLoggerFile().error(nbFormatEx.getMessage());
			}
			catch (OutOfRangeUserInputException outOfRangeUserInputEx) {
				LoggerManager.getViewLoggerFile().error(outOfRangeUserInputEx.getMessage());
			}
			catch (InvalidComputerIdException invCompIdEx) {
				LoggerManager.getViewLoggerFile().error(invCompIdEx.getMessage());
			}
			catch (InputMismatchException inputMismatchEx) {
				LoggerManager.getViewLoggerFile().error(inputMismatchEx.getMessage());
			}
		}
		
		System.out.println("Bye");
	}
}