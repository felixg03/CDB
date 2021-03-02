package com.excilys.cdb.customExceptions;

public final class InvalidUserInputException extends Exception {
	
	private final String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public InvalidUserInputException(String message) {
		this.message = message;
	}
}
