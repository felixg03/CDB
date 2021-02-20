package com.excilys.cdb.view;

import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Company;

public class ViewCompany {
	
	private Scanner scanner = new Scanner(System.in);
	
	public int displayListCompanies(List<Company> listCompanies) {
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("RESULT");
		System.out.println("List of companies:");
		System.out.println();
		for (Company company : listCompanies) {
			System.out.println(company);
		}
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("Precedent page --> type 0");
		System.out.println("Next page --> type 1");
		System.out.println("Back to menu --> type 2");
		return this.scanner.nextInt();
	}
}
