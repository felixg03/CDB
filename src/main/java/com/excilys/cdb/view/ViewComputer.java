package com.excilys.cdb.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Computer;

public class ViewComputer {
	
	private Scanner scanner = new Scanner(System.in);
	
	
	public int displayListComputers(List<Computer> listComputers) {
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("RESULT");
		System.out.println("List of companies:");
		System.out.println();
		for (Computer computer : listComputers) {
			System.out.println(computer);
		}
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Precedent page --> type 0");
		System.out.println("Next page --> type 1");
		System.out.println("Back to menu --> type 2");
		return this.scanner.nextInt();
	}
	
	public void displayOneComputerDetails(Computer computer) {
		System.out.println();
		System.out.println("RESULT");
		System.out.println();
		System.out.println(computer);
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
	}
	
	public void displayResultComputerCreation(String result) {
		System.out.println();
		System.out.println("RESULT");
		System.out.println();
		System.out.println(result);
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
	}

	public void displayResultComputerUpdate(String result) {
		System.out.println();
		System.out.println("RESULT");
		System.out.println();
		System.out.println(result);
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
	}

	public void displayResultComputerDeletion(String result) {
		System.out.println();
		System.out.println("RESULT");
		System.out.println();
		System.out.println(result);
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
	}

	public long getComputerId(int userInput) {
		if (userInput == 3) {
			System.out.println("--------------------------------------");
			System.out.println();
			System.out.println("- You chose show one computer details -");
			System.out.println();
			System.out.println("Enter the id of the computer that you wish to see the details:");
			return this.scanner.nextInt();
		}
		else { // if (userInput == 6) {
			System.out.println("--------------------------------------");
			System.out.println();
			System.out.println("- You chose to delete one computer -");
			System.out.println();
			System.out.println("Enter the id of the computer that you wish to delete:");
			return this.scanner.nextInt();
		}
	}

	public Computer getComputerInfoToUpdate() {
		long idOfComputerToUpdate;
		String newName = null;
		LocalDate newIntroducedDate = null;
		LocalDate newDiscontinuedDate = null;
		long newCompanyId = 0;
		
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("- You chose to update one computer's information -");
		System.out.println();
		System.out.println("Enter the id of the computer that you wish to update:");
		idOfComputerToUpdate = this.scanner.nextInt();
		
		System.out.println();
		System.out.println("Do you wish to update the computer name ? (1 = yes, 0 = no)");
		if (this.scanner.nextInt() == 1) {
			System.out.println("Enter the new computer name:");
			newName = this.scanner.next();
		}
		
		System.out.println();
		System.out.println("Do you wish to update the introduced date ? (1 = yes, 0 = no)");
		if (this.scanner.nextInt() == 1) {
			int year;
			int month;
			int day;
			System.out.println();
			System.out.println("Enter the YEAR of the new introduced date:");
			year = this.scanner.nextInt();
			System.out.println("Enter the NUMBER OF THE MONTH of the new introduced date:");
			month = this.scanner.nextInt();
			System.out.println("Enter the DAY of the new introduced date:");
			day = this.scanner.nextInt();
			
			newIntroducedDate = LocalDate.of(year, month, day);
		}
		
		System.out.println();
		System.out.println("Do you wish to update the discontinued date ? (1 = yes, 0 = no)");
		if (this.scanner.nextInt() == 1) {
			int year;
			int month;
			int day;
			System.out.println();
			System.out.println("Enter the YEAR of the new discontinued date:");
			year = this.scanner.nextInt();
			System.out.println("Enter the NUMBER OF THE MONTH of the new discontinued date:");
			month = this.scanner.nextInt();
			System.out.println("Enter the DAY of the new discontinued date:");
			day = this.scanner.nextInt();
			
			newDiscontinuedDate = LocalDate.of(year, month, day);
		}
		
		System.out.println();
		System.out.println("Do you wish to update the company id ? (1 = yes, 0 = no)");
		if (this.scanner.nextInt() == 1) {
			System.out.println("Enter the new company id");
			newCompanyId = this.scanner.nextInt();
		}
		
		return new Computer(idOfComputerToUpdate,
							newName, 
							newIntroducedDate, 
				 			newDiscontinuedDate, 
				 			newCompanyId
				 			);
	}

	public Computer getComputerToCreate() {
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		long companyId = 0;
		
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("- You chose to create a new computer -");
		System.out.println();
		System.out.println("Enter the name:");
		name = this.scanner.next();
		
		System.out.println();
		System.out.println("Do you wish to add an introduced date ? (1 = yes, 0 = no)");
		if (this.scanner.nextInt() == 1) {
			int year;
			int month;
			int day;
			System.out.println();
			System.out.println("Enter the YEAR:");
			year = this.scanner.nextInt();
			System.out.println("Enter the NUMBER OF THE MONTH:");
			month = this.scanner.nextInt();
			System.out.println("Enter the DAY:");
			day = this.scanner.nextInt();
			
			introduced = LocalDate.of(year, month, day);
		}
		
		System.out.println();
		System.out.println("Do you wish to add a discontinued date ? (1 = yes, 0 = no)");
		if (this.scanner.nextInt() == 1) {
			int year;
			int month;
			int day;
			System.out.println();
			System.out.println("Enter the YEAR:");
			year = this.scanner.nextInt();
			System.out.println("Enter the NUMBER OF THE MONTH:");
			month = this.scanner.nextInt();
			System.out.println("Enter the DAY:");
			day = this.scanner.nextInt();
			
			discontinued = LocalDate.of(year, month, day);
		}
		
		System.out.println();
		System.out.println("Enter the company id:");
		companyId = this.scanner.nextInt();
		
		
		return new Computer(0, 
							name, 
							introduced, 
							discontinued, 
							companyId
							);
	}
}
