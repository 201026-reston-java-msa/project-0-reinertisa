package com.bankingapp.customexceptions;

public class InvalidAccountException extends RuntimeException{

	public InvalidAccountException() {

	}
	
	public InvalidAccountException(String message) {
		super(message);
	}
	
	public InvalidAccountException(String message, Throwable clause) {
		super(message, clause);
	}
}
