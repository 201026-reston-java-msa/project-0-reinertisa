package com.bankingapp.models;

public class AccountType {

	private int typeId; // primary key
	private String type; // not null, unique
	
	public AccountType() {
	}
	
	public AccountType(int typeId) {
		this.typeId = typeId;
	}
	
	public AccountType(int typeId, String type) {
		this.typeId = typeId;
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AccountType [typeId=" + typeId + ", type=" + type + "]";
	}
}
