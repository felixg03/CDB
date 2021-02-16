package com.excilys.formation.java.view;

import com.excilys.formation.java.model.Model;
import com.excilys.formation.java.controller.Controller;
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
		
		boolean loop = true;
		int userInput = 0;
		
		while(loop) {
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
