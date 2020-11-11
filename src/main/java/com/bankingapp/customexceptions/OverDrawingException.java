package com.bankingapp.customexceptions;

public class OverDrawingException extends RuntimeException{

	public OverDrawingException() {
	
	}
	
	public OverDrawingException(String message) {
		super(message);
	}
	
	public OverDrawingException(String message, Throwable clause) {
		super(message, clause);
	}
}
