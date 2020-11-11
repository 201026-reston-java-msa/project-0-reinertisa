package com.bankingapp.service;


import java.time.LocalDateTime;

public interface UserServices {

	public boolean register(String username, String password, String firstName, String lastName, String email, int roleId);
	
	public boolean getUser(int userId);
	public boolean getAllUsers();
	
	public boolean checkUsernameAndPassword(String username, String password);
	public boolean usernameIsAvailable(String username);
	public boolean emailIsAvailable(String email);
	
	public int getLoginId(String username, String password);
	public String getUsername(int userId);
	public String getUserRole(int userId);
	
	public void setLoginTime(int userId);
	public LocalDateTime getLastLoginTime(int userId);
	
	public String getHashedPassword(String username);
	
	public void closeResources();

}
