package com.bankingapp.service;


import java.time.LocalDateTime;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.bankingapp.models.*;
import com.bankingapp.repository.*;


public class UserServicesImpl implements UserServices{

	private UserDao userDao = new UserDaoImpl();
	private static final Logger log = Logger.getLogger(UserServicesImpl.class);
	
	
	@Override
	public boolean register(String username, String password, String firstName, String lastName, String email, int roleId) {
		
		log.info("register START");
		
		User user = new User(username, password,firstName, lastName, email, new Role(roleId));	
		
		String tempPass = user.getPassword();
		String hashPass = hashPassword(tempPass);
		user.setPassword(hashPass);
		
		
		return userDao.createUser(user);	
	}	
	
	
	public String hashPassword(String plainTextPassword){
		
		log.info("hashPassword START");
		
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
	}
	
	public boolean checkPass(String plainPassword, String hashedPassword) {
		
		log.info("checkPass START");
		
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
	
	@Override
	public String getHashedPassword(String username) {

		log.info("getHashedPassword START");
		
		return userDao.getHashedPassword(username);
	}
	
	@Override
	public boolean usernameIsAvailable(String username) {
		
		log.info("usernameIsAvailable START");
		
		return userDao.getUsername(username);	
	}

	@Override
	public boolean emailIsAvailable(String email) {
		
		log.info("emailIsAvailable START");
		
		return userDao.getEmail(email);
	}
	
	@Override
	public boolean checkUsernameAndPassword(String username, String password) {
		
		log.info("checkUsernameAndPassword START");
		
		String hashedPassword = getHashedPassword(username);
		try {
			if(BCrypt.checkpw(password, hashedPassword)) {
				return userDao.getUserId(username, hashedPassword) != -1;
			}				
		} catch (Exception e) {
			log.info("No user in DB");
		}
		
		return false;
		

	}

	@Override
	public int getLoginId(String username, String password) {
		
		log.info("getLoginId START");
		
		String hashedPassword = getHashedPassword(username);
		
		if(BCrypt.checkpw(password, hashedPassword)) {
			return userDao.getUserId(username, hashedPassword);
		} else {
			return -1;
		}	
		
	}

	@Override
	public String getUserRole(int userId) {
		
		log.info("getUserRole START");
		
		return userDao.getUserRolebyUserId(userId);
	
	}
	
	@Override
	public String getUsername(int userId) {
		
		log.info("getUsername START");
		
		return userDao.getUsername(userId);
	
	}

	@Override
	public boolean getUser(int userId) {
		
		log.info("getUser START");
		
		HashMap<Integer, User> users = userDao.getUserByUserId(userId);
		
		if(users.size() != 0) {
			System.out.println("UserId(#)\tUsername\tFirstName\tLastName\tRole\t\tEmail");
			for(User user : users.values()) {
				user.printUsers();
			}
			return true;
		}		
		return false;
	}
	
	@Override
	public boolean getAllUsers() {
		
		log.info("getAllUsers START");
		
		HashMap<Integer, User> users = userDao.getAllUsers();
		if(users.size() != 0) {
			System.out.println("UserId(#)\tUsername\tFirstName\tLastName\tRole\t\tEmail");
			for(User user : users.values()) {
				user.printUsers();
			}
			return true;
		}		
		return false;
	}

	@Override
	public void setLoginTime(int userId) {
		
		log.info("setLoginTime START");
		
		userDao.setLoginTime(userId, LocalDateTime.now());
		
	}

	@Override
	public LocalDateTime getLastLoginTime(int userId) {
		
		log.info("getLastLoginTime START");
		
		return userDao.getLastLoginTime(userId);
	}


	@Override
	public void closeResources() {
		userDao.closeResources();
		
	}
	

}
