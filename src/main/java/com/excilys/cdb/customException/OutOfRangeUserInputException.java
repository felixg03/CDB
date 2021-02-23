package com.excilys.cdb.customException;

public class OutOfRangeUserInputException extends Exception {
	
	private String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public OutOfRangeUserInputException(int wrongInput) {
		this.message = "For input: \"" + wrongInput + "\"" ;
	}
}