package com.bankingapp.models;

public class AccountStatus {

	private int statusId; // primary key
	private String status; // not null, unique
	
	public AccountStatus() {		
		this.statusId=1;
	}
	
	public AccountStatus(int statusId) {
		this.statusId=statusId;
	}
	
	
	public AccountStatus(int statusId, String status) {
		this.statusId=statusId;
		this.status = status;
	}
	
	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountStatus [statusId=" + statusId + ", status=" + status + "]";
	}
}
