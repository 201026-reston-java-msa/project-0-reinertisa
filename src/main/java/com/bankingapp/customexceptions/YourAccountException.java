package com.bankingapp.customexceptions;

public class YourAccountException extends RuntimeException{
	
	public YourAccountException() {
	
	}
	
	public YourAccountException(String message) {
		super(message);
	}
	
	public YourAccountException(String message, Throwable clause) {
		super(message, clause);
	}

}
