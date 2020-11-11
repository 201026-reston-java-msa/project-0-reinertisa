package com.bankingapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankingapp.models.*;
import com.bankingapp.models.User;
import com.bankingapp.util.DaoConnection;


public class UserDaoImpl implements UserDao {
	
	Connection conn = null;
	PreparedStatement pst = null;
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	@Override
	public HashMap<Integer, User> getAllUsers() {
		
		log.info("UserDao : getAllUsers START");
		
		HashMap<Integer, User> map = new HashMap<>();
		
		try {
			String sql = "SELECT a.userid, a.username, a.PASSWORD, a.firstname, a.lastname, a.email, \r\n" + 
					"b.roleid, b.role, c.accountid, trunc(c.balance :: NUMERIC, 2) AS balance, \r\n" + 
					"d.statusid, d.status, e.typeid , e.type FROM users a \r\n" + 
					"INNER JOIN  role b ON a.roleid = b.roleid\r\n" + 
					"LEFT OUTER JOIN account c ON a.userid = c.userid \r\n" + 
					"LEFT OUTER JOIN accountstatus d ON c.statusid = d.statusid \r\n" + 
					"LEFT OUTER JOIN accounttype e ON c.typeid = e.typeid;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();			
			
			while(rs.next()) {
				
				if(!map.containsKey(rs.getInt("userid"))) {
					User user = new User();					
					
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setEmail(rs.getString("email"));
					user.setRole(new Role(rs.getInt("roleid"), rs.getString("role")));		
					
					
					if(rs.getInt("accountid") > 0) {
						List<Account> accounts = new ArrayList<>();
						Account account = new Account(rs.getInt("accountid"), 
													  rs.getDouble("balance"), 
										  new AccountStatus(rs.getInt("statusid"), rs.getString("status")),
										  new AccountType(rs.getInt("typeid"), rs.getString("type")));
					
						accounts.add(account);
						user.setAccounts(accounts);
					} else {
						List<Account> accounts = null;
						user.setAccounts(accounts);
					}

					map.put(user.getUserId(), user);
					
				} else {
					User user = map.get(rs.getInt("userid"));
					List<Account> accounts = user.getAccounts();
					
					Account account = new Account(rs.getInt("accountid"), 
							  rs.getDouble("balance"), 
							  new AccountStatus(rs.getInt("statusid"), rs.getString("status")),
							  new AccountType(rs.getInt("typeid"), rs.getString("type")));
					accounts.add(account);
					
				}
				
			} 
			
		} catch (SQLException e) {
				System.out.println("\nINFO: Connection failed");
				log.info("UserDao : Connection failed", e);
		} 
		
		log.info("UserDao: getAllUsers END");
		return map;
	}
	

	@Override
	public HashMap<Integer, User> getUserByUserId(int userId) {
		
		log.info("UserDao: getUserByUserId START");
		
		HashMap<Integer, User> map = new HashMap<>();	
		
		try {
			String sql = "SELECT a.userid, a.username, a.PASSWORD, a.firstname, a.lastname, a.email, \r\n" + 
					"b.roleid, b.role, c.accountid, trunc(c.balance :: NUMERIC, 2) AS balance, \r\n" + 
					"d.statusid, d.status, e.typeid , e.type  FROM users a\r\n" + 
					"INNER JOIN role b ON a.roleid = b.roleid \r\n" + 
					"LEFT OUTER JOIN account c ON a.userid = c.userid \r\n" + 
					"LEFT OUTER JOIN accountstatus d ON c.statusid = d.statusid \r\n" + 
					"LEFT OUTER JOIN accounttype e ON c.typeid = e.typeid WHERE a.userid = ?;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();		
			
			while(rs.next()) {
				
				if(!map.containsKey(rs.getInt("userid"))) {
					User user = new User();					
				
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setEmail(rs.getString("email"));
					user.setRole(new Role(rs.getInt("roleid"), rs.getString("role")));		
					
					if(rs.getInt("accountid") > 0) {
						List<Account> accounts = new ArrayList<>();
						Account account = new Account(rs.getInt("accountid"), 
													  rs.getDouble("balance"), 
										  new AccountStatus(rs.getInt("statusid"), rs.getString("status")),
										  new AccountType(rs.getInt("typeid"), rs.getString("type")));
					
						accounts.add(account);
						user.setAccounts(accounts);
					} else {
						List<Account> accounts = null;
						user.setAccounts(accounts);
					}

					map.put(user.getUserId(), user);
					
				} else {
					User user = map.get(rs.getInt("userid"));
					List<Account> accounts = user.getAccounts();
					
					Account account = new Account(rs.getInt("accountid"), 
							  rs.getDouble("balance"), 
							  new AccountStatus(rs.getInt("statusid"), rs.getString("status")),
							  new AccountType(rs.getInt("typeid"), rs.getString("type")));
					accounts.add(account);
					
				}
				
			} 

			
		} catch (SQLException e) {
				System.out.println("\nINFO: Connection failed");
				log.info("UserDao : Connection failed", e);
		} 
		
		log.info("UserDao: getUserByUserId END");
		
		return map;
	}
	
	@Override
	public int getUserId(String username, String password) {
		
		log.info("UserDao: getUserId START");
		
		try {
			String sql = "SELECT userid FROM users WHERE username=? AND password=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, username);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
					return rs.getInt("userid");			
			
		} catch (SQLException e) {
			log.info("UserDao : Connection failed", e);
		} 
		
		log.info("UserDao: getUserId END");
		
		return -1;		
	}

	@Override
	public boolean getUsername(String username) {
		
		log.info("UserDao: getUsername START");
		
		try {
			String sql = "SELECT * FROM users WHERE username=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return false;
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		} 
		log.info("UserDao: getUsername END");
		return true;
	}

	@Override
	public boolean getEmail(String email) {
		
		log.info("UserDao: getEmail START");
		
		try {
			String sql = "SELECT * FROM users WHERE email=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return false;
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		} 
		log.info("UserDao: getEmail END");
		
		return true;
	}

	@Override
	public boolean createUser(User user) {
		
		log.info("UserDao: createUser START");
		
		try {
			String sql = "INSERT INTO users(username, password, firstName, lastName, email, roleid) "
					+ "VALUES(?,?,?,?,?,?);";
		
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFirstName());
			pst.setString(4, user.getLastName());
			pst.setString(5, user.getEmail());
			pst.setInt(6, user.getRole().getRoleId());
			
			int count = pst.executeUpdate();
			if(count > 0)
				return true;
		
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		} 
		
		log.info("UserDao: createUser END");
		
		return false;
		
	}

	@Override
	public String getUserRolebyUserId(int userId) {
		
		log.info("UserDao: getUserRolebyUserId START");
		
		try {
			String sql = "SELECT role FROM role a \r\n" + 
					"INNER JOIN users b ON a.roleid = b.roleid \r\n" + 
					"WHERE userid=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return rs.getString("role");
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		} 
		
		log.info("UserDao: getUserRolebyUserId END");
		
		return null;
	}


	@Override
	public String getFirstNameByUserId(int userId) {
		log.info("UserDao: getFirstNameByUserId START");
		
		try {
			String sql = "SELECT firstname FROM users WHERE userid=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return rs.getString("firstname");
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		} 
		
		log.info("UserDao: getFirstNameByUserId END");
		
		return null;
	}


	@Override
	public String getLastNameByUserId(int userId) {
		
		log.info("UserDao: getLastNameByUserId START");
		
		try {
			String sql = "SELECT lastname FROM users WHERE userid=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return rs.getString("lastname");
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		}
		
		log.info("UserDao: getLastNameByUserId END");
		
		return null;
	}


	@Override
	public void setLoginTime(int userId, LocalDateTime loginTime) {
		
		log.info("UserDao: setLoginTime START");
		
		try {
			String sql = "UPDATE users SET logintime=current_timestamp WHERE userid=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		}
		
		log.info("UserDao: setLoginTime END");		
	}

	@Override
	public LocalDateTime getLastLoginTime(int userId) {
		
		log.info("UserDao: getLastLoginTime START");

		LocalDateTime lastLoginTime = LocalDateTime.now();
		
		try {
			String sql = "SELECT logintime FROM users WHERE userid=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();
	
			
			if(rs.next()) {
				lastLoginTime = rs.getTimestamp("logintime").toLocalDateTime();
			}
			
		} catch (NullPointerException e) {
			log.info("UserDao : Login time is null");
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		}
		
		log.info("UserDao: getLastLoginTime END");
		
		return lastLoginTime;
	}
	
	@Override
	public String getUsername(int userId) {
		log.info("UserDao: getUsername START");
		
		try {
			String sql = "SELECT username FROM users WHERE userid=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return rs.getString("username");
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		} 
		log.info("UserDao: getUsername END");
		return null;
	}


	@Override
	public String getHashedPassword(String username) {
		
		log.info("UserDao: getHashedPassword START");
		
		try {
			String sql = "SELECT password FROM users WHERE username=?;";
			
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return rs.getString("password");
			
		} catch (SQLException e) {
			System.out.println("\nINFO: Connection failed");
			log.info("UserDao : Connection failed", e);
		} 
		log.info("UserDao: getHashedPassword END");
		return null;
	}
	
	
	@Override
	public void closeResources() {
		
		log.info("closeResources START");
		try {
			
			if (pst != null)
				pst.close();
		
		} catch (SQLException e) {
			System.out.println("\nCould not close prepared statement!");
			log.info("Could not close prepared statement!", e);
		}
		
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("\nCould not close connection!");
			log.info("Could not close connection!", e);
		}
		
		log.info("closeResources END");
	}


}
