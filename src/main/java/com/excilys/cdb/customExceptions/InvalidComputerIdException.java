package com.excilys.cdb.customExceptions;

public class InvalidComputerIdException extends Exception {
	
	private final String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public InvalidComputerIdException(long wrongInput) {
		this.message = "For input: \"" + wrongInput + "\"";
	}
}
