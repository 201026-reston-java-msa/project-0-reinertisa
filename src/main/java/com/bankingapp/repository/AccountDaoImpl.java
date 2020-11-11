package com.bankingapp.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankingapp.models.*;
import com.bankingapp.util.DaoConnection;


public class AccountDaoImpl implements AccountDao {

	Connection conn = null;
	PreparedStatement pst = null;
	CallableStatement cstmt = null;
	private static final Logger log = Logger.getLogger(AccountDaoImpl.class);
	
	@Override
	public void createAccount(int userId, Account account) {
		
		log.info("AccountDao : Create Account START");
		
		try {
			String sql = "INSERT INTO account(balance, userid, statusid, typeid, owner, createdate) VALUES(?,?,?,?,?,?)";
		
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setDouble(1, account.getBalance());
			pst.setInt(2, userId);
			pst.setInt(3, account.getStatus().getStatusId());
			pst.setInt(4, account.getType().getTypeId());
			pst.setString(5, account.getOwner());
			pst.setDate(6, Date.valueOf(LocalDate.now()));
			
			pst.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao : Connection failed", e);
		} 
		
		log.info("AccountDao: Create Account END");
	}
/*
	@Override
	public List<Account> getAllAccounts() {
		
		log.info("AccountDao: getAllAccounts START");
		
		List<Account> accounts = new ArrayList<>();
		
		try {
			String sql = "SELECT a.accountid, a.balance, \r\n"
					+ "b.status , b.statusid ,c.TYPE, c.typeid, a.OWNER, a.createdate \r\n"
					+ "FROM account a \r\n"
					+ "INNER JOIN accountstatus b ON a.statusid = b.statusid \r\n"
					+ "INNER JOIN accounttype c ON a.typeid = c.typeid ORDER BY a.owner;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();			
		
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAllAccounts END");
		
		return accounts;
	}
*/
	
	
	//Stored Functions
	@Override
	public List<Account> getAllAccounts() {
		
		log.info("AccountDao: getAllAccounts START");
		
		List<Account> accounts = new ArrayList<>();
		
		try (Connection conn = DaoConnection.getConnection()) {
			
			String sql = "{call get_all_accounts()}";
			cstmt = conn.prepareCall(sql);
						
			cstmt.execute();
			ResultSet rs = cstmt.getResultSet();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAllAccounts END");
		
		return accounts;
	}
	
	

/*	
	@Override
	public List<Account> getAllAccountsByStatusId(int statusId) {
		
		log.info("AccountDao: getAllAccountsByStatusId START");
		
		List<Account> accounts = new ArrayList<>();
		
		try {
			String sql = "SELECT a.accountid, a.balance, \r\n"
					+ "a.statusid, a.typeid ,b.status ,c.TYPE, a.OWNER, a.createdate FROM account a \r\n"
					+ "INNER JOIN accountstatus b ON a.statusid  = b.statusid \r\n"
					+ "INNER JOIN accounttype c ON a.typeid = c.typeid \r\n"
					+ "WHERE a.statusid = ? ORDER BY a.owner;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, statusId);
			ResultSet rs = pst.executeQuery();			
		
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));	
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAllAccountsByStatusId END");
		
		return accounts;
	}
*/	
	//Stored Procedure 
	@Override
	public List<Account> getAllAccountsByStatusId(int statusId) {
		
		log.info("AccountDao: getAllAccountsByStatusId START");
		
		List<Account> accounts = new ArrayList<>();
		
		try (Connection conn = DaoConnection.getConnection()) {
			
			String sql = "{call get_all_accounts_by_statusid(?)}";
			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, statusId);			
			cstmt.execute();
			
			ResultSet rs = cstmt.getResultSet();		
		
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));	
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAllAccountsByStatusId END");
		
		return accounts;
		
	}	

/*
	@Override
	public List<Account> getAllAccountsByUserId(int userId) {
		
		log.info("AccountDao: getAllAccountsByUserId START");
		
		List<Account> accounts = new ArrayList<>();
		
		try {
			String sql = "SELECT a.accountid, a.balance, \r\n"
					+ "a.statusid, a.typeid ,b.status ,c.TYPE, a.OWNER, a.createdate  \r\n"
					+ "FROM account a \r\n"
					+ "INNER JOIN accountstatus b ON a.statusid = b.statusid \r\n"
					+ "INNER JOIN accounttype c ON a.typeid = c.typeid \r\n"
					+ "WHERE userid=? ORDER BY a.accountid DESC;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();			
		
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));	
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAllAccountsByUserId END");
		
		return accounts;
	}
*/
	//Stored Procedure 
	@Override
	public List<Account> getAllAccountsByUserId(int userId) {
		
		log.info("AccountDao: getAllAccountsByUserId START");
		
		List<Account> accounts = new ArrayList<>();
		
		try (Connection conn = DaoConnection.getConnection()) {
			
			String sql = "{call get_all_accounts_by_userid(?)}";
			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, userId);			
			cstmt.execute();
			
			ResultSet rs = cstmt.getResultSet();				
		
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));	
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAllAccountsByUserId END");
		
		return accounts;
	}
	
/*
	@Override
	public Account getAccountByAccountId(int accountId) {
		
		log.info("AccountDao: getAccountByAccountId START");
		
		try {
			String sql = "SELECT a.accountid, a.balance, \r\n"
					+ "a.statusid, a.typeid ,b.status ,c.TYPE, a.OWNER, a.createdate \r\n"
					+ "FROM account a \r\n"
					+ "INNER JOIN accountstatus b ON a.statusid = b.statusid \r\n"
					+ "INNER JOIN accounttype c ON a.typeid = c.typeid \r\n"
					+ "WHERE a.accountid = ?;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, accountId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));	
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				return account;
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao:  getAccountByAccountId END");
		
		return null;
	}
*/
	
	//Stored Procedure 
	@Override
	public Account getAccountByAccountId(int accountId) {
		
		log.info("AccountDao: getAccountByAccountId START");
		
		try (Connection conn = DaoConnection.getConnection()) {
			
			String sql = "{call get_account_by_accountid(?)}";
			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, accountId);			
			cstmt.execute();
			
			ResultSet rs = cstmt.getResultSet();			
		
			if(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setStatus(new AccountStatus(rs.getInt("statusid"), rs.getString("status")));
				account.setType(new AccountType(rs.getInt("typeId"), rs.getString("type")));	
				account.setOwner(rs.getString("owner"));
				account.setCreateDate(rs.getDate("createdate").toLocalDate());
				return account;
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao:  getAccountByAccountId END");
		
		return null;
	}
	
	
	@Override
	public double getBalanceByAccountId(int accountId) {

		log.info("AccountDao: getBalanceByAccountId START");
		
		double balance = 0.0D;
		
		try {
				
			String sql = "SELECT a.balance FROM account a WHERE a.accountid = ?";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, accountId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				balance = rs.getDouble("balance");
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getBalanceByAccountId END");
		
		return balance;
		
	}
	
	@Override
	public boolean makeAWithdrawal(int accountId, double cash) {
		
		log.info("AccountDao: getBalanceByAccountId START");
		
		try {
			String sql = "UPDATE account SET balance = trunc((balance - ?) :: NUMERIC, 2) WHERE accountid = ?;";
		
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setDouble(1, cash);
			pst.setInt(2, accountId);
			
			int count = pst.executeUpdate();
			
			if(count > 0)
				return true;
		
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getBalanceByAccountId END");
		
		return false;
	}

	@Override
	public boolean makeADeposit(int accountId, double cash) {
		
		log.info("AccountDao: makeADeposit START");
		
		try {
			String sql = "UPDATE account SET balance = trunc((balance + ?) :: NUMERIC, 2) WHERE accountid = ?;";
		
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setDouble(1, cash);
			pst.setInt(2, accountId);
			
			int count = pst.executeUpdate();
			
			if(count > 0)
				return true;
		
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: makeADeposit END");
		
		return false;		
	}

	@Override
	public boolean updateAccountStatusByAccountId(int accountId, int statusId) {
		
		log.info("AccountDao: updateAccountStatusByAccountId START");
		
		try {
			String sql = "UPDATE account SET statusid=? WHERE accountid = ?;";
		
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, statusId);
			pst.setInt(2, accountId);
			
			int count = pst.executeUpdate();
		
			if(count > 0) 
				return true;
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: updateAccountStatusByAccountId END");
		
		return false;
	}


	@Override
	public int getAccountIdByUserId(int userId) {
		
		log.info("AccountDao: getAccountIdByUserId START");
		
		try {
				
			String sql = "SELECT accountid FROM account WHERE userid = ?";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				return rs.getInt("accountid");
			}
			
		} catch (SQLException e) {
				System.out.println("\nConnection failed");
				log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAccountIdByUserId END");
		
		return -1;
	}

	
	
	@Override
	public boolean hasAccount(int accountId) {
		
		log.info("AccountDao: getAccountIdbyAccountId START");		
		
		try {
			
			String sql = "SELECT accountid FROM account WHERE accountid = ?;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, accountId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
				System.out.println("\nConnection failed");
				log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAccountIdbyAccountId END");
		
		return false;
	}
	
	@Override
	public String getAccountStatus(int accountId) {
		
		log.info("AccountDao: getAccountStatus START");
		
		String str = "";
		
		try {
			
			String sql = "SELECT a.status FROM accountstatus a \r\n" + 
					"INNER JOIN account b ON a.statusid = b.statusid \r\n" + 
					"WHERE b.accountid = ?;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, accountId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				return rs.getString("status");
			}
			
		} catch (SQLException e) {
				System.out.println("\nConnection failed");
				log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getAccountStatus END");
		
		return str;
	}
	
	

	@Override
	public boolean isAccountOwnedByUser(int accountId, int userId) {
		
		log.info("AccountDao: isAccountOwnedByUser START");
		
		try {
			
			String sql = "SELECT accountid FROM account WHERE accountid = ? AND userid = ?;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, accountId);
			pst.setInt(2, userId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
				System.out.println("\nConnection failed");
				log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: isAccountOwnedByUser END");
		
		return false;
	}

	@Override
	public int getOpenAccountId(int userId) {
		
		log.info("AccountDao: getOpenAccountId START");
		
		try {
			
			String sql = "SELECT count(*) openAccountNum FROM account WHERE userid = ? AND statusid = 2;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				return rs.getInt("openAccountNum");
			}
			
		} catch (SQLException e) {
				System.out.println("\nConnection failed");
				log.info("AccountDao: Connection failed", e);
		} 
		
		log.info("AccountDao: getOpenAccountId END");
		
		return 0;
	}


	@Override
	public boolean isEqualAccountId(int firstAccountId, int secondAccountId, int userId) {

		log.info("AccountDao: isEqualAccountId START");
		
		try {
			
			String sql = "SELECT * FROM account WHERE "
					+ "accountid = ? AND accountid = ? AND userid=?;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, firstAccountId);
			pst.setInt(2, secondAccountId);
			pst.setInt(3, userId);
			ResultSet rs = pst.executeQuery();			
		
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
				System.out.println("\nConnection failed");
				log.info("AccountDao: Connection failed", e);
		} 		
		
		
		log.info("AccountDao: isEqualAccountId END");
		
		return false;
	}
	
	@Override
	public void closeResources() {
		
		log.info("closeResources START");
		try {
			
			if (pst != null)
				pst.close();
		
		} catch (SQLException e) {
			System.out.println("\nCould not close prepared statement!");
			log.info("AccountDao: Could not close prepared statement!", e);
		}
		
		try {
			
			if (cstmt != null)
				cstmt.close();
		
		} catch (SQLException e) {
			System.out.println("\nCould not close callable statement!");
			log.info("Could not close callable statement!", e);
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
