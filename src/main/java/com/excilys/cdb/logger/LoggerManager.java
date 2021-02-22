package com.excilys.cdb.logger;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.cdb.customExceptions.OutOfRangeUserInputException;

public final class LoggerManager {
	
	private static LoggerManager instance;
	
	private final Logger viewLoggerFile =
	(Logger) LoggerFactory.getLogger("com.excilys.cdb.logger.fileLogger");
	private final Logger viewLoggerConsole =
	(Logger) LoggerFactory.getLogger("STDOUT");
	
	
	public static LoggerManager getInstance() {
		if (instance == null) {
			instance = new LoggerManager();
		}
		return instance;
	}
	
	public void logInLogFile(Exception e) {
		if (e.getClass() == NumberFormatException.class) {
			viewLoggerFile.error("Invalid char input: Must be an INTEGER (between 1 and 7)", e);
		}
		else if (e.getClass() == OutOfRangeUserInputException.class) {
			viewLoggerFile.error("Out of range input: Integer must be between 1 and 7", e);
		}
	}
}
