package com.bankingapp.service;


public interface AccountServices {

	//Create account methods
	public void createAccount(int userId, double balance, int typeId);
	public boolean isCreateCheckingAccount(int userId, String amount);
	public boolean isCreateSavingsAccount(int userId, String amount);
	
	//Show account methods
	public boolean getAllAccounts();
	public boolean getAllAccountsByStatus(int statusId);
	public boolean getAllAccountsByUserId(int userId);
	public boolean getAccountByAccountId(int accountId);
	
	//Transaction methods
	public boolean isMakeAWithdrawal(int accountId, double amount);
	public boolean isMakeADeposit(int accountId, double amount);
	
	//Transaction account control methods
	public boolean hasEnoughBalanceForWithdrawal(int accountId, double balance);
	public boolean hasAccountIdByUserId(int userId);
	public boolean hasEnoughOpenAccounts(int userId);
	public boolean hasEnoughOpenAccountsForTransferMoneyInYourAccounts(int userId);
	public boolean isEqualAccountId(int firstAccount, int secondAccount, int userId);
	public boolean checkAccountId(String accountNum, int userId);
	public boolean checkTargetAccountId(String accountNum, int userId);
	public boolean checkApproveDenyAccountId(String accountNum, int userId);
	public boolean checkApproveDenyCancelAccountId(String accountNum, int userId);
	
	//Transaction data control methods
	public boolean isYourOpenAccount(int accountId, int userId);
	public boolean isNotYourOpenAccount(int accountId, int userId);
	public boolean isOpenAccount(int accountId, int userId);
	public boolean isPendingAccount(int accountId, int userId);
	public boolean isValidAccountNum(int accountId, int userId);
	public boolean isYourAccountNum(int accountId, int userId);
	public boolean isNotYourAccountNum(int accountId, int userId);
	public boolean isValidMoney(String money, int userId);	

	//Approve / deny / cancel / methods
	public boolean updateAccountStatus(int accountId, int statusId);	

	
	//Cancel account methods
	public String getUserRole(int userId);
	
	//Test cases
	public void isValidAmountToCreateCheckingAccount(String amount);
	public void isValidAmountToCreateSavingsAccount(String amount);
	public boolean isValidAmountToMakeATransaction(String amount);
	public boolean isValidAccountToMakeATransaction(String accountId);
	
	public void closeResources();

	

}
