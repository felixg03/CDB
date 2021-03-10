package com.excilys.cdb.views;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.cdb.models.Company;

@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
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
		System.out.println("Precedent page --> type 1");
		System.out.println("Next page --> type 2");
		System.out.println("Back to menu --> type anything else");
		
		String userInputToReturn = this.scanner.nextLine();
		int intToReturn = -1;
		
		try {
			intToReturn = Integer.valueOf(userInputToReturn);
		}
		catch (NumberFormatException e) {
			intToReturn = 0; // default value
		}
		
		return intToReturn;
	}
	
	public long getCompanyId() {
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("Enter the id of the company you wish to delete");
		System.out.println();
		String userInput = this.scanner.nextLine();
		long companyId = -1;
		try {
			companyId = Long.valueOf( userInput );
		}
		catch ( NumberFormatException nbFormatEx ) {
			nbFormatEx.printStackTrace();
		}
		return companyId;
	}
}
