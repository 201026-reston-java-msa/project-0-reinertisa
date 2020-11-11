package com.bankingapp.repository;

import java.util.List;

import com.bankingapp.models.*;


public interface AccountDao {

	public void createAccount(int userid, Account account);
	
	public List<Account> getAllAccounts();
	public List<Account> getAllAccountsByStatusId(int statusId);
	public List<Account> getAllAccountsByUserId(int userId);
	public Account getAccountByAccountId(int accountId);
	
	public double getBalanceByAccountId(int accountId);
	public int getOpenAccountId(int userId);
	public int getAccountIdByUserId(int userId);

	public String getAccountStatus(int accountId);

	public boolean updateAccountStatusByAccountId(int accountId, int statusId);	
	
	public boolean makeAWithdrawal(int accountId, double amount);
	public boolean makeADeposit(int accountId, double amount);

	public boolean isAccountOwnedByUser(int accountId, int userId);
	public boolean isEqualAccountId(int firstAccountId, int secondAccountId, int userId);
	public boolean hasAccount(int accountId);
	
	public void closeResources();
	
}

