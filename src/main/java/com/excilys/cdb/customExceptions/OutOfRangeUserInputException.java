package com.excilys.cdb.customExceptions;

public class OutOfRangeUserInputException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public OutOfRangeUserInputException(int wrongInput) {
		this.message = "For input: \"" + wrongInput + "\"" ;
	}
}