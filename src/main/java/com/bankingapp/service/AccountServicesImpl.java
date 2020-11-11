package com.bankingapp.service;


import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.customexceptions.*;
import com.bankingapp.models.*;
import com.bankingapp.repository.*;


public class AccountServicesImpl implements AccountServices{

	private static final Logger log = Logger.getLogger(AccountServicesImpl.class);
	private AccountDao accountDao = new AccountDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	Scanner sc = new Scanner(System.in);
	

	@Override
	public void createAccount(int userId, double balance, int typeId) {
		
		log.info("createAccount START");
		
		String firstName = userDao.getFirstNameByUserId(userId);
		String lastName = userDao.getLastNameByUserId(userId);
		
		String owner = firstName + " " + lastName;
		
		Account account = new Account(balance, new AccountStatus(), new AccountType(typeId), owner);
		accountDao.createAccount(userId, account);			
	}	
	
	
	@Override
	public boolean isCreateCheckingAccount(int userId, String amount) {
		
		log.info("isCreateCheckingAccount START");
	
		try { 
		
			int typeId = 1;			
			isValidAmountToCreateCheckingAccount(amount);
			double balance = Double.parseDouble(amount);	
			createAccount(userId, balance, typeId);
			return true;
			
		} catch(NullPointerException e) {
			log.warn("WARNING: Empty Amount!");
			System.out.println("\nWARNING: Empty Amount!");
			
		} catch (NumberFormatException e) { 
			log.warn("WARNING: Invalid amount!");
			System.out.println("\nWARNING: Invalid amount!");
			
		} catch (NegativeAmountException e) {
			log.warn("WARNING: Amount must have a positive number!");		
			System.out.println("\nWARNING: Amount must have a positive number!");
			
		} catch (InsufficientAmountException e) {
			log.warn("WARNING: Not enough money to open a checking account!");
			System.out.println("\nWARNING: Not enough money to open a checking account!");
		}
		
		return false;
	}
	
	/* Test Cases - Create Checking Account - Test Data: Amount */
	@Override
	public void isValidAmountToCreateCheckingAccount(String money) {
		
		log.info("isValidAmountToCreateCheckingAccount START");
		
		if(money == null || money.length() == 0) {
			throw new NullPointerException("Empty Amount");
		}
		
		double amount = 0.0D;
		
		try {
			amount = Double.parseDouble(money);			
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid Amount");
		} 
			
		if(amount < 0.0D) {
			throw new NegativeAmountException("Negative Amount");
		} 		
		
		if(amount < 25.0D) {
			throw new InsufficientAmountException("Insufficient Amount");
		}

		log.info("isValidAmountToCreateCheckingAccount END");
	}
	
	@Override
	public boolean isCreateSavingsAccount(int userId, String amount) {
		
		log.info("isCreateSavingsAccount START");
		
		try { 
			
			int typeId = 2;			
			isValidAmountToCreateSavingsAccount(amount);
			double balance = Double.parseDouble(amount);	
			createAccount(userId, balance, typeId);
			return true;
            
            
		} catch (NullPointerException e) {
			log.warn("WARNING: Empty Amount!");
			System.out.println("\nWARNING: Empty Amount!");
			
		} catch (NumberFormatException e) { 
			log.warn("WARNING: Invalid amount!");
			System.out.println("\nWARNING: Invalid amount!");
			
		} catch (NegativeAmountException e) {
			log.warn("WARNING: Amount must have a positive number!");
			System.out.println("\nWARNING: Amount must have a positive number!");
			
        } catch (InsufficientAmountException e) {
        	log.warn("WARNING: Not enough money to open a savings account");   
        	System.out.println("\nWARNING: Not enough money to open a savings account");
        }		
		
		return false;
	}
	
	/* Test Cases - Create Saving Account - Test Data: Amount */
	@Override
	public void isValidAmountToCreateSavingsAccount(String money) {
		
		log.info("isValidAmountToCreateSavingsAccount START");
		
		if(money == null || money.length() == 0) {
			throw new NullPointerException("Empty Amount");
		}
		
		double amount = 0.0D;
		
		try {
			amount = Double.parseDouble(money);			
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid Amount");
		}
			
		if(amount < 0.0D) {
			throw new NegativeAmountException("Negative Amount");
		} 		
		
		if(amount < 100.0D) {
			throw new InsufficientAmountException("Insufficient Amount");
		}
		
		log.info("isValidAmountToCreateSavingsAccount END");
	}

	@Override
	public boolean getAllAccounts() {
		
		log.info("getAllAccounts START");
		
		List<Account> accounts = accountDao.getAllAccounts();
		if(accounts.size() != 0) {
			
			System.out.println("AccountId(#)\tBalance($)\tStatus\t\tType\t\tAccount Owner\t\tCreated Date");
			
			for(Account account : accounts) {
				account.printAccounts();
			}
			return true;	
			
		}		
		return false;
	}
	
	@Override
	public boolean getAllAccountsByUserId(int userId) {
		
		log.info("getAllAccountsByUserId START");
		
		List<Account> accounts = accountDao.getAllAccountsByUserId(userId);
		if(accounts.size() != 0) {
			System.out.println("AccountId(#)\tBalance($)\tStatus\t\tType\t\tAccount Owner\t\tCreated Date");
			
			for(Account account : accounts) {
				account.printAccounts();
			}
			return true;
		}		
		return false;
	}
	
	@Override
	public boolean getAllAccountsByStatus(int statusId) {
		
		log.info("getAllAccountsByStatus START");
		
		List<Account> accounts = accountDao.getAllAccountsByStatusId(statusId);
		
		if(accounts.size() != 0) {		
			
			System.out.println("AccountId(#)\tBalance($)\tStatus\t\tType\t\tAccount Owner\t\tCreated Date");
			
			for(Account account : accounts) {
				account.printAccounts();
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean getAccountByAccountId(int accountId) {
		
		log.info("getAccountByAccountId START");
		
		Account account = accountDao.getAccountByAccountId(accountId);
		if(account != null) {
			
			System.out.println("AccountId(#)\tBalance($)\tStatus\t\tType\t\tAccount Owner\t\tCreated Date");
			
			account.printAccounts();
			
			return true;
		}		
		return false;
	}


	@Override
	public boolean isMakeAWithdrawal(int accountId, double amount) {
		
		log.info("isMakeAWithdrawal START");
		
		if(hasEnoughBalanceForWithdrawal(accountId, amount)) {
			return accountDao.makeAWithdrawal(accountId, amount);
		} else {
			return false;
		}				
	}
	
	@Override
	public boolean hasEnoughBalanceForWithdrawal(int accountId, double amount) {
		
		log.info("hasEnoughBalanceForWithdrawal START");
		
		boolean result = false;
		
		try {
			if(!(accountDao.getBalanceByAccountId(accountId) >= amount)) {
				throw new InsufficientAmountException("Insufficient Amount");
			}  			
			result = true;			
		} catch (InsufficientAmountException e) {
			log.warn("WARNING: Insufficient funds to make this transaction in your account");
			System.out.println("\nWARNING: Insufficient funds to make this transaction in your account");
        }		
		return result;		
	}
	
	@Override
	public boolean isMakeADeposit(int accountId, double amount) {
		
		log.info("isMakeADeposit START");
		
		return accountDao.makeADeposit(accountId, amount);
	}

	@Override
	public boolean hasEnoughOpenAccounts(int userId) {
		
		log.info("hasEnoughOpenAccounts START");
		
		return accountDao.getOpenAccountId(userId) > 0;		
	}
	
	public boolean hasEnoughOpenAccountsForTransferMoneyInYourAccounts(int userId) {
		
		log.info("hasEnoughOpenAccountsForTransferMoneyInYourAccounts START");
		
		return accountDao.getOpenAccountId(userId) > 1;		
	}

	@Override
	public boolean isEqualAccountId(int firstAccount, int secondAccount, int userId) {
		
		log.info("isEqualAccountId START");
		return accountDao.isEqualAccountId(firstAccount, secondAccount, userId);
	}

	@Override
	public boolean updateAccountStatus(int accountId, int statusId) {

		log.info("updateAccountStatus START");
		
		return accountDao.updateAccountStatusByAccountId(accountId, statusId);
	}

	
	@Override
	public String getUserRole(int userId) {
		
		log.info("getUserRole START");
		
		return userDao.getUserRolebyUserId(userId);
	}

	@Override
	public boolean hasAccountIdByUserId(int userId) {
		
		log.info("hasAccountIdByUserId START");
		
		return accountDao.getAccountIdByUserId(userId) == -1 ? false : true;
	}

	@Override
	public boolean checkAccountId(String accountNum, int userId) {
		
		log.info("checkAccountId START");
		
		String role = getUserRole(userId);
		
		if(role.equals("Admin")) {
			
			int accountId = 0;
			if(isValidAccoutNumType(accountNum, userId)) {
				accountId = Integer.parseInt(accountNum);
				
				if(!isValidAccountNum(accountId, userId)) {
					return false;
				} else {
					if(!isOpenAccount(accountId, userId)) {
						return false;
					} else {
						return true;
					}
				}
			} else {
				return false;
			}	
			
		} else {
			
			int accountId = 0;
			if(isValidAccoutNumType(accountNum, userId)) {
				accountId = Integer.parseInt(accountNum);
				
				if(!isValidAccountNum(accountId, userId)) {
					return false;
				} else {
					if(!isYourAccountNum(accountId, userId)) {
						return false;
					} else {
						if(!isYourOpenAccount(accountId, userId)) {
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				return false;
			}		
		}
	}
	
	@Override
	public boolean checkTargetAccountId(String accountNum, int userId) {
		
		log.info("checkTargetAccountId START");
		
		String role = getUserRole(userId);
		
		if(role.equals("Admin")) {
			
			int accountId = 0;
			if(isValidAccoutNumType(accountNum, userId)) {
				accountId = Integer.parseInt(accountNum);
				
				if(!isValidAccountNum(accountId, userId)) {
					return false;
				} else {
					if(!isOpenAccount(accountId, userId)) {
						return false;
					} else {
						return true;
					}
				}
			} else {
				return false;
			}	
			
		} else {
			
			int accountId = 0;
			if(isValidAccoutNumType(accountNum, userId)) {
				accountId = Integer.parseInt(accountNum);
				
				if(!isValidAccountNum(accountId, userId)) {
					return false;
				} else {
					if(!isNotYourAccountNum(accountId, userId)) {
						return false;
					} else {
						if(!isNotYourOpenAccount(accountId, userId)) {
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				return false;
			}			
		}
	}
	
	@Override
	public boolean checkApproveDenyAccountId(String accountNum, int userId) {
		
		log.info("checkApproveDenyAccountId START");
		
		int accountId = 0;
		if(isValidAccoutNumType(accountNum, userId)) {
			accountId = Integer.parseInt(accountNum);
			
			if(!isValidAccountNum(accountId, userId)) {
				return false;
			} else {
				if(!isNotYourAccountNum(accountId, userId)) {
					return false;
				} else {
					if(!isPendingAccount(accountId, userId)) {
						return false;
					} else {
						return true;
					}
				}
			}
		} else {
			return false;
		}	
	}
	
	@Override
	public boolean checkApproveDenyCancelAccountId(String accountNum, int userId) {
		
		log.info("checkApproveDenyCancelAccountId START");
		
		int accountId = 0;
		if(isValidAccoutNumType(accountNum, userId)) {
			accountId = Integer.parseInt(accountNum);
			
			if(!isValidAccountNum(accountId, userId)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}	
	}
	
	@Override
	public boolean isPendingAccount(int accountId, int userId) {
		
		log.info("isPendingAccount START");
			
		boolean result = false;
		try {   						
			if(!accountDao.getAccountStatus(accountId).equals("Pending")) {
				throw new NotPendingAccountException("Not Pending Account");
			}						
			result = true;
			
		} catch (NotPendingAccountException e) {			
			log.warn("WARNING: Account not pending!");	
			System.out.println("\nWARNING: Account not pending!");
		} 		
		return result;		
	}
		
	public boolean isValidAccoutNumType(String accountNum, int userId) {
		
		log.info("isValidAccoutNumType START");
		
		boolean result = false;
		
		try {
			Integer.parseInt(accountNum);
			result=true;
			
		}catch (NumberFormatException e) {			
			log.warn("WARNING: Invalid number!");	
			System.out.println("\nWARNING: Invalid number!");
		} 			
		return result;
	}
	
	@Override
	public boolean isValidAccountNum(int accountNum, int userId) {
		
		log.info("isValidAccountNum START");
		
		boolean result = false;
		try {   			
			if(!accountDao.hasAccount(accountNum)) {
				throw new InvalidAccountException("Invalid Account");
			}						
			
			result = true;
		} catch (InvalidAccountException e) {		
			log.warn("WARNING: This account number is not in our system");
			System.out.println("\nWARNING: This account number is not in our system");
		} 	
		return result;
	}
		
	@Override
	public boolean isYourAccountNum(int accountNum, int userId) {
		
		log.info("isYourAccountNum START");
		
		boolean result = false;
		try {   						
			if(!accountDao.isAccountOwnedByUser(accountNum, userId)) {
				throw new NotYourAccountException("Not Your Account");
			}			
			result = true;	
		} catch (NotYourAccountException e) {			
			log.warn("WARNING: This is not your account number!");		
			System.out.println("\nWARNING: This is not your account number!");
		} 			
		return result;
	}
	
	public boolean isNotYourAccountNum(int accountNum, int userId) {
		
		log.info("isNotYourAccountNum START");
		
		boolean result = false;
		try {   						
			if(accountDao.isAccountOwnedByUser(accountNum, userId)) {
				throw new YourAccountException("Your Account");
			}			
			result = true;	
		} catch (YourAccountException e) {			
			log.warn("WARNING: This is your account number!");		
			System.out.println("\nWARNING: This is your account number!");
		} 			
		return result;
	}
	
	@Override
	public boolean isYourOpenAccount(int accountNum, int userId) {
		
		log.info("isYourOpenAccount START");
		
		boolean result = false;
		try {   						
			if(!accountDao.getAccountStatus(accountNum).equals("Open")) {
				throw new NotOpenAccountException("Not Open Account");
			}						
			result = true;
			
		} catch (NotOpenAccountException e) {			
			log.warn("WARNING: Account not open!");	
			System.out.println("\nWARNING: Account not open!");
		} 		
		return result;		
	}
	
	@Override
	public boolean isNotYourOpenAccount(int accountNum, int userId) {
		
		log.info("isNotYourOpenAccount START");
		
		boolean result = false;
		try {   						
			if(!accountDao.getAccountStatus(accountNum).equals("Open")) {
				throw new NotOpenAccountException("Not Open Account");
			}						
			result = true;
			
		} catch (NotOpenAccountException e) {			
			log.warn("WARNING: Account not open!");	
			System.out.println("\nWARNING: Account not open!");
		} 		
		return result;		
	}
	
	@Override
	public boolean isOpenAccount(int accountNum, int userId) {
		
		log.info("isOpenAccount START");
		
		boolean result = false;
		try {   						
			if(!accountDao.getAccountStatus(accountNum).equals("Open")) {
				throw new NotOpenAccountException("Not Open Account");
			}						
			result = true;
			
		} catch (NotOpenAccountException e) {			
			log.warn("WARNING: Account not open!");	
			System.out.println("\nWARNING: Account not open!");
		} 		
		return result;	
	}
	
	@Override
	public boolean isValidMoney(String money, int userId) {
		
		log.info("isValidMoney START");
		
		boolean result = false;
		
		try {
			double amount = Double.parseDouble(money);	   
							
			if(amount <= 0.0D) {
				throw new NegativeAmountException("Negative Amount");
			} 
			
			result = true;		
		} catch (NumberFormatException e) { 
			log.warn("WARNING: Invalid number!");
			System.out.println("\nWARNING: Invalid number!");
				
		} catch (NegativeAmountException e) {
			log.warn("WARNING: Amount must have a positive number");
			System.out.println("\nWARNING: Amount must have a positive number");
        }
		
		return result;
	}
	

	/* Test Cases - Make A Transaction - Test Data: Amount */
	@Override
	public boolean isValidAmountToMakeATransaction(String money) {
		
		log.info("isValidAmountToMakeATransaction START");
		
		if(money == null || money.length() == 0) {
			throw new NullPointerException("Empty Amount");
		}
		
		for(int i=0; i < money.length(); i++) {
			char c = money.charAt(i);
			
			if(i == 0 && c == '-') {
				continue;
			}
			
			if(!Character.isDigit(c)) {
				throw new NumberFormatException("Invalid Amount");
			}
		}
		
		double amount = Double.parseDouble(money);	  
			
		if(amount < 0.0D) {
			throw new NegativeAmountException("Negative Amount");
		} 				

		return true;
	}

	/* Test Cases - Make A Transaction - Test Data: Account */
	@Override
	public boolean isValidAccountToMakeATransaction(String accountId) {
		
		log.info("isValidAccountToMakeATransaction START");
		
		if(accountId == null || accountId.length() == 0) {
			throw new NullPointerException("Empty AccountId");
		}
		
		for(char c : accountId.toCharArray()) {

			if(!Character.isDigit(c)) {
				throw new InvalidAccountException("Invalid AccountId");
			}
		}
		
		int accountNum = Integer.parseInt(accountId);	  
			
		if(accountNum <= 0) {
			throw new InvalidAccountException("Invalid AccountId");
		} 				

		return true;
	}


	@Override
	public void closeResources() {
		accountDao.closeResources();
		
	}
	
}

