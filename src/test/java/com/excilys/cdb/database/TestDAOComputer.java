package com.excilys.cdb.database;

import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Company.CompanyBuilder;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Computer.ComputerBuilder;
import com.excilys.cdb.models.Page;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestDAOComputer extends TestCase {
	
	private final static int DEFAULT_PAGE_NUMBER = 1;
	private final static int DEFAULT_PAGE_SIZE = 10;
	private final static List<Computer> DEFAULT_PAGE_CONTENT = createDefaultPageContent();
	
	private final static DateTimeFormatter dateTimeFormatter = 
	DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
	
	
	@Test
	public void requestPageComputerTest() {
		DAOComputer daoComputer = DAOComputer.getInstance();
		Page<Computer> pageComputerExpected = new Page<>(DEFAULT_PAGE_NUMBER
													   , DEFAULT_PAGE_SIZE);
		pageComputerExpected.setContent(DEFAULT_PAGE_CONTENT);
		
		Page<Computer> pageArgument = new Page<>(DEFAULT_PAGE_NUMBER
											   , DEFAULT_PAGE_SIZE);
		
		assertEquals(daoComputer.requestPageComputer(pageArgument)
				  , pageComputerExpected);
	}
	
	
	
	
	
	/*
	 * ##################
	 * ###			  ###
	 * ###    TOOLS   ###
	 * ### 			  ###
	 * ##################
	 */
	private static List<Computer> createDefaultPageContent() {
		List<Computer> defaultListComputer = new ArrayList<>();
		Company appleInc = new CompanyBuilder().setId(1)
											   .build();
		Company thinkingMachines = new CompanyBuilder().setId(2)
													   .build();
		
		LocalDate introduced = LocalDate.parse("1991-01-01"
	 										  , dateTimeFormatter);
		
		// #### 1 ####
		Computer computer = new ComputerBuilder().setId(1)
												 .setName("MacBook Pro 15.4 inch")
												 .setCompany(appleInc)
												 .build();
		defaultListComputer.add(computer);
		
	
		
		
		// #### 2 ####
		computer = new ComputerBuilder().setId(2)
										.setName("CM-2a")
										.setCompany(thinkingMachines)
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		// #### 3 ####
		computer = new ComputerBuilder().setId(3)
									    .setName("CM-200")
									    .setCompany(thinkingMachines)
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		// #### 4 ####
		computer = new ComputerBuilder().setId(4)
									    .setName("CM-5e")
									    .setCompany(thinkingMachines)
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		// #### 5 ####
		computer = new ComputerBuilder().setId(5)
										.setName("CM-5")
										.setIntroduced(introduced)
										.setCompany(thinkingMachines)
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		// #### 6 ####
		introduced = LocalDate.parse("2006-01-10 00:00:00"
						 			, dateTimeFormatter);
		computer = new ComputerBuilder().setId(6)
										.setName("MacBook Pro")
										.setIntroduced(introduced)
										.setCompany(appleInc)
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		
		// #### 7 ####
		computer = new ComputerBuilder().setId(7)
										.setName("Apple IIe")
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		
		// #### 8 ####
		computer = new ComputerBuilder().setId(8)
										.setName("Apple IIc")
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		
		// #### 9 ####
		computer = new ComputerBuilder().setId(9)
										.setName("Apple IIGS")
										.build();
		defaultListComputer.add(computer);
		
		
		
		
		
		// #### 10 ####
		computer = new ComputerBuilder().setId(10)
										.setName("Apple IIc Plus")
										.build();
		defaultListComputer.add(computer);
		
		
		return defaultListComputer;
										
	}
}
