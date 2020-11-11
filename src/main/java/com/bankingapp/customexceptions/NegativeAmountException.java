package com.bankingapp.customexceptions;

public class NegativeAmountException extends RuntimeException{
	
	public NegativeAmountException() {
		
	}
	
	public NegativeAmountException(String message) {
		super(message);
	}
	
	public NegativeAmountException(String message, Throwable clause) {
		super(message, clause);
	}

}
