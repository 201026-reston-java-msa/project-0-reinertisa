package com.bankingapp.models;

import java.time.LocalDate;

public class Account {

	private int accountId; // primary key
	private double balance;  // not null
	private String owner;
	private LocalDate createDate;
	private AccountStatus status;
	private AccountType type;
	
	public Account() {		

	}
	
	public Account(double balance, AccountStatus status, AccountType type, String owner) {
		this.balance = balance;
		this.status = status;
		this.type = type;
		this.owner = owner;
		this.createDate = LocalDate.now();
	}
		
	public Account(int accountId, double balance, AccountStatus status, AccountType type) {
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
		this.createDate = LocalDate.now();
	}

	public Account(int accountId, double balance, String owner, LocalDate createDate, AccountStatus status,
			AccountType type) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.owner = owner;
		this.createDate = createDate;
		this.status = status;
		this.type = type;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}
	  
	public int getAccountId() {
		return accountId;
	}

	public AccountStatus getStatus() {
		return status;
	}
	
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void printAccounts() {
		System.out.printf("%-15d %-15.2f %-15s %-15s %-23s %-15s %n", 
				accountId, balance, status.getStatus(), type.getType(), owner, createDate);
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", owner=" + owner + ", status=" + status
				+ ", type=" + type + "]";
	}


}
