package com.bankingapp.repository;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.bankingapp.models.User;

public interface UserDao {

	public boolean createUser(User user);
	
	public HashMap<Integer, User> getAllUsers();
	public HashMap<Integer, User> getUserByUserId(int userId);
	
	public boolean getUsername(String username); 
	public boolean getEmail(String email);
	
	public int getUserId(String username, String password);
	public String getUsername(int userId);
	public String getFirstNameByUserId(int userId);
	public String getLastNameByUserId(int userId);
	public String getUserRolebyUserId(int userId);	
	
	
	public void setLoginTime(int userId, LocalDateTime loginTime);
	public LocalDateTime getLastLoginTime(int userId);
	
	public String getHashedPassword(String username);
	
	public void closeResources();
}
