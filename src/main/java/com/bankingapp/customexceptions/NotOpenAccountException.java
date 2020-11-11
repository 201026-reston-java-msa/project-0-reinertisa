package com.bankingapp.customexceptions;

public class NotOpenAccountException extends RuntimeException{

	public NotOpenAccountException() {

	}
	
	public NotOpenAccountException(String message) {
		super(message);
	}
	
	public NotOpenAccountException(String message, Throwable clause) {
		super(message, clause);
	}
}
