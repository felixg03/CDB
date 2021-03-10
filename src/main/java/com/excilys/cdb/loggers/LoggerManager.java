package com.excilys.cdb.loggers;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class LoggerManager {
	
	private static final Logger viewLoggerFile =
	(Logger) LoggerFactory.getLogger("com.excilys.cdb.logger.fileLogger");
	
	private static final Logger viewLoggerConsole =
	(Logger) LoggerFactory.getLogger("STDOUT"); 
	
	
	public static Logger getViewLoggerFile() {
		return viewLoggerFile;
	}
	
	public static Logger getViewLoggerConsole() {
		return viewLoggerConsole;
	}
	
}
