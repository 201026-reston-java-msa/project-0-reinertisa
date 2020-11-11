package com.bankingapp.customexceptions;

public class NotYourAccountException extends RuntimeException{
	
	public NotYourAccountException() {
	
	}
	
	public NotYourAccountException(String message) {
		super(message);
	}
	
	public NotYourAccountException(String message, Throwable clause) {
		super(message, clause);
	}
	

}
