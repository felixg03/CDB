package com.excilys.cdb.loggers;

import ch.qos.logback.classic.Logger;

import java.sql.SQLException;
import java.util.InputMismatchException;

import org.slf4j.LoggerFactory;

import com.excilys.cdb.customExceptions.InvalidComputerIdException;
import com.excilys.cdb.customExceptions.OutOfRangeUserInputException;

public final class LoggerManager {
	
	private static final Logger viewLoggerFile =
	(Logger) LoggerFactory.getLogger("com.excilys.cdb.logger.fileLogger");
	/*private static final Logger viewLoggerConsole =
	(Logger) LoggerFactory.getLogger("STDOUT"); */

	/*public static void logInLogFile(SQLException sqlException) {
		viewLoggerFile.error("Out of range input: Integer must be between 1 and 7", sqlException);
	}*/
	
	public static void logInLogFile(OutOfRangeUserInputException outOfRangeUserInputEx) {
		viewLoggerFile.error("Out of range input: Integer must be between 1 and 7", outOfRangeUserInputEx);
	}
	
	public static void logInLogFile(NumberFormatException nbFormatEx) {
		viewLoggerFile.error("Invalid input: Must be an integer", nbFormatEx);
	}
	
	public static void logInLogFile(InvalidComputerIdException invCompIdEx) {
		viewLoggerFile.error("Invalid computer id input: The id doesn't exist in database", invCompIdEx);
	}
	
	public static void logInLogFile(InputMismatchException inputMismatchEx) {
		viewLoggerFile.error("Invalid input", inputMismatchEx);
	}
}
