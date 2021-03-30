package com.excilys.cdb.loggers;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class LoggerManager {
	
	private static final Logger loggerFile =
	(Logger) LoggerFactory.getLogger("com.excilys.cdb.logger.fileLogger");
	
	private static final Logger loggerConsole =
	(Logger) LoggerFactory.getLogger("STDOUT"); 
	
	
	public static Logger getLoggerFile() {
		return loggerFile;
	}
	
	public static Logger getLoggerConsole() {
		return loggerConsole;
	}
	
}
