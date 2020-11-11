package com.bankingapp.customexceptions;

public class NotPendingAccountException extends RuntimeException{

	public NotPendingAccountException() {
	
	}
	
	public NotPendingAccountException(String message) {
		super(message);
	}
	
	public NotPendingAccountException(String message, Throwable clause) {
		super(message, clause);
	}
}
