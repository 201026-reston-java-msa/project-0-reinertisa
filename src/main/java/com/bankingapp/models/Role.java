package com.bankingapp.models;

public class Role {

	private int roleId; // primary key
	private String role; // not null, unique
	
	public Role() {
		this.roleId=3;
		this.role = "Standard";		
	}
	
	public Role(int roleId) {
		this.roleId = roleId;
	}
	public Role(int roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}	
}
