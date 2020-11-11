package com.bankingapp.customexceptions;

public class InsufficientAmountException extends RuntimeException{
	
	public InsufficientAmountException() {

	}
	
	public InsufficientAmountException(String message){
		super(message);
	}
	
	InsufficientAmountException(String message, Throwable clause){
		super(message, clause);
	}
}
