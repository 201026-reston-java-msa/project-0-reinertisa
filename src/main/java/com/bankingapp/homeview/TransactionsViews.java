package com.bankingapp.homeview;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.authorizationview.*;
import com.bankingapp.service.*;

public class TransactionsViews {
	
	private AccountServices accountServices = new AccountServicesImpl();
	private static final Logger log = Logger.getLogger(TransactionsViews.class);
	Scanner sc = new Scanner(System.in);
	static boolean[] result = new boolean[1];
	static boolean[] flag = new boolean[1];
	static int accountId = 0;
	HelpViews helper = new HelpViews();
	
	public static final String RESET = "\033[0m";
	public static final String GREEN_BOLD = "\033[1;32m";
	public static final String RED_BOLD = "\033[1;31m";  

	
	/////////////////WITHDRAWAL MENU///////////////////////////
	public void makeAWithdrawalView(int userId) {
		
		log.info("Make a Withdrawal View START");
		
		String str = null;
		
		do {
			helper.printSubTitle("WITHDRAWAL MENU");		
			sc = new Scanner(System.in);
	
			System.out.println("1.Start Transaction");
			System.out.println("2.Show Accounts");
			System.out.println("3.Main Menu");
			System.out.println("0.Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  makeAWithdrawal(userId);
				    break;
				  case "2":
					  new AccountViews().showUserAccounts(userId);
				    break;
				  case "3":
					  new HomeView().roleMenu(userId);;
				    break;
				  case "0":
					new LoginView().signOut();
				    break;
				  default:
					  log.warn("WARNING: Invalid choice. Please try again");
					  System.out.println("\nWARNING: Invalid choice. Please try again");
				}
			} while(!str.equals("0"));	
		
		log.info("Make a Withdrawal View END");
		
	}
	
	
	public void makeAWithdrawal(int userId) {
		
		log.info("Make a Withdrawal START");
		
		String role = accountServices.getUserRole(userId);
		sc = new Scanner(System.in);
		int transactionId = 1;
		
		if(role.equals("Admin")) {
			
			int accountNum = getAccountIdFromUser(userId, transactionId);		
			double amount = getMoneyFromUser(userId);
			
			if(accountServices.isMakeAWithdrawal(accountNum, amount)) {
				log.info("Withdrawal successful");
				System.out.println(GREEN_BOLD + "\nINFO: Withdrawal successful" + RESET);
				new AccountViews().showAccountByAccountId(accountNum, userId);
			} else {					
				log.info("Withdrawal failed");
				System.out.println(RED_BOLD + "\nINFO: Withdrawal failed" + RESET);
			}
			
		} else {
			if(!accountServices.hasEnoughOpenAccounts(userId)) {
				log.info("You do not have any open account to withdraw money");
				System.out.println("\nINFO: You do not have any open account to withdraw money");
				log.info("Check your account status if you already created");
				System.out.println("\nINFO: Check your account status if you already created");
			} else {
				
				int accountNum = getAccountIdFromUser(userId, transactionId);		
				double amount = getMoneyFromUser(userId);
				
				if(accountServices.isMakeAWithdrawal(accountNum, amount)) {
					log.info("Withdrawal successful");
					System.out.println(GREEN_BOLD + "\nINFO: Withdrawal successful" + RESET);
					new AccountViews().showAccountByAccountId(accountNum, userId);
				} else {					
					log.info("Withdrawal failed");
					System.out.println(RED_BOLD + "\nINFO: Withdrawal failed" + RESET);
				}
			}	
		}
		
		log.info("Make a Withdrawal END");
	
	}		
	
	/////////////////////// DEPOSIT MENU/////////////////////////////////
	
	public void makeADepositView(int userId) {
		
		log.info("Make a Deposit View START");
		
		String str = null;
		
		do {
			helper.printSubTitle("DEPOSIT MENU");	
			sc = new Scanner(System.in);
	
			System.out.println("1.Start Transaction");
			System.out.println("2.Show Accounts");
			System.out.println("3.Main Menu");
			System.out.println("0.Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  makeADeposit(userId);
				    break;
				  case "2":
					  new AccountViews().showUserAccounts(userId);
				    break;
				  case "3":
					  new HomeView().roleMenu(userId);;
				    break;
				  case "0":
					new LoginView().signOut();
				    break;
				  default:
					  log.warn("WARNING: Invalid choice. Please try again");
					  System.out.println("WARNING: Invalid choice. Please try again");
				}
			} while(!str.equals("0"));	
		
		log.info("Make a Deposit View END");
	}
	
	
	public void makeADeposit(int userId) {
		
		log.info("Make a Deposit START");
		
		String role = accountServices.getUserRole(userId);
		sc = new Scanner(System.in);		
		int transactionId = 2;
		
		
		if(role.equals("Admin")) {
			
			int accountNum = getAccountIdFromUser(userId, transactionId);
			double amount = getMoneyFromUser(userId);

			if (accountServices.isMakeADeposit(accountNum, amount)) {
				log.info("Deposit successful");
				System.out.println(GREEN_BOLD + "\nINFO: Deposit successful" + RESET);
				new AccountViews().showAccountByAccountId(accountNum, userId);
			} else {					
				log.info("Deposit failed");
				System.out.println(RED_BOLD + "\nINFO: Deposit failed" + RESET);
			}
			
			
		
		} else {
			if(!accountServices.hasEnoughOpenAccounts(userId)) {
				
				log.info("You do not have any open account to deposit money");
				System.out.println("\nINFO: You do not have any open account to deposit money");
				log.info("Check your account status if you already created");
				System.out.println("\nINFO: Check your account status if you already created");
				
			} else {
				
				int accountNum = getAccountIdFromUser(userId, transactionId);		
				double amount = getMoneyFromUser(userId);
				
				if(accountServices.isMakeADeposit(accountNum, amount)) {
					log.info("Deposit successful");
					System.out.println(GREEN_BOLD + "\nINFO: Deposit successful" + RESET);
					new AccountViews().showAccountByAccountId(accountNum, userId);
				} else {					
					log.info("Deposit failed");
					System.out.println(RED_BOLD + "\nINFO: Deposit failed" + RESET);
				}
			}	
		}	
		
		log.info("Make a Deposit END");
	}	
	
	
	/////////////////////// TRANSFER FUNDS MENU/////////////////////////////////
	public void transferFundsView(int userId) {
		
		log.info("Transfer Funds View START");
		
		String str = null;
		
		do {
			helper.printSubTitle("TRANSFER FUNDS MENU");	
			sc = new Scanner(System.in);
	
			System.out.println("1.Start Transaction");
			System.out.println("2.Show Accounts");
			System.out.println("3.Main Menu");
			System.out.println("0.Sign out");
			
			str = sc.nextLine();
			switch (str) {
			case "1":
				transferFunds(userId);
				break;	
			case "2":
				new AccountViews().showUserAccounts(userId);
				break;
			case "3":
				new HomeView().roleMenu(userId);;
				break;
			case "0":
				new LoginView().signOut();
			    break;  
			default:
				log.warn("WARNING: Invalid choice. Please try again");		
				System.out.println("\nWARNING: Invalid choice. Please try again");
			}
		} while(!str.equals("0"));	
		
		log.info("Transfer Funds View END");
		
	}
	
	public void transferFunds(int userId) {
		
		log.info("Transfer Funds START");
		
		String role = accountServices.getUserRole(userId);
		String str = null;
		
		if(role.equals("Admin")) {
			do {
				helper.printSubTitle("TRANSFER FUNDS");		
				sc = new Scanner(System.in);
		
				System.out.println("1.Transfer money one account to another");
				System.out.println("2.Show All Accounts");
				System.out.println("3.Main Menu");
				System.out.println("0.Sign out");
				
					str = sc.nextLine();
					switch (str) {
					  case "1":
						  transferFundsForAdmin(userId);
					    break;
					  case "2":
						  new AccountViews().showAllAccounts();
					    break;
					  case "3":
						  new HomeView().roleMenu(userId);;
					    break;
					  case "0":
						new LoginView().signOut();
					    break;
					  default:
						  log.warn("WARNING: Invalid choice. Please try again");
						  System.out.println("\nWARNING: Invalid choice. Please try again");
					}
				} while(!str.equals("0"));	
			
		} else {
			do {
				helper.printSubTitle("TRANSFER FUNDS");		
				sc = new Scanner(System.in);
		
				System.out.println("1.Transfer money one account to another");
				System.out.println("2.Transfer money to someone else's account");
				System.out.println("3.Show Accounts");
				System.out.println("4.Main Menu");
				System.out.println("0.Sign out");
				
					str = sc.nextLine();
					switch (str) {
					  case "1":
						  transferFundsInMyAccounts(userId);
					    break;
					  case "2":
						  transferFundsSomeoneElseAccount(userId);
					    break;
					  case "3":
						  new AccountViews().showUserAccounts(userId);
					    break;
					  case "4":
						  new HomeView().roleMenu(userId);;
					    break;
					  case "0":
						new LoginView().signOut();
					    break;
					  default:
						  log.warn("WARNING: Invalid choice. Please try again");
						  System.out.println("\nWARNING: Invalid choice. Please try again");
					}
				} while(!str.equals("0"));	
		}
		
		log.info("Transfer Funds END");
	}
	
	
	public void transferFundsInMyAccounts(int userId) {
		
		log.info("Transfer Funds in My Accounts START");
		 
		boolean isTransferSuccess = false;
		double transferMoney = 0;
		int sourceAccount = 0;
		
		sc = new Scanner(System.in);
		
		int transactionId = 3;
		
	
		if(!accountServices.hasEnoughOpenAccountsForTransferMoneyInYourAccounts(userId)) {
			log.info("You must have more than one open accounts to transfer funds");
			System.out.println("\nINFO: You must have more than one open accounts to transfer funds");
		} else {
			
			do {				
				sourceAccount = getAccountIdFromUser(userId, transactionId);		
				transferMoney = getMoneyFromUser(userId);	
				
				if(accountServices.hasEnoughBalanceForWithdrawal(sourceAccount, transferMoney)) {
					
					transactionId = 4;
					int targetAccount = getAccountIdFromUser(userId, transactionId);
					
					if(accountServices.isEqualAccountId(sourceAccount, targetAccount, userId)) {
						
						log.warn("WARNING: Same account number, please enter to different account number");
						System.out.println("\nWARNING: Same account number, please enter to different account number");
						log.warn("WARNING: Transaction failed. Let's start again!");	
						System.out.println("\nWARNING: Transaction failed. Let's start again!");
					} else {
						
						accountServices.isMakeAWithdrawal(sourceAccount, transferMoney);
						accountServices.isMakeADeposit(targetAccount, transferMoney);
						
						log.info("Transfer completed successfully");
						new AccountViews().showAccountByAccountId(sourceAccount, userId);
						new AccountViews().showAccountByAccountId(targetAccount, userId);
						System.out.println(GREEN_BOLD + "\nINFO: Transfer completed successfully" + RESET);
						isTransferSuccess = true;
					}					
					
				} else {
					log.warn("WARNING: Transaction failed. Let's start again!");
					System.out.println(RED_BOLD + "\nWARNING: Transaction failed. Let's start again!" + RESET);
					HelpViews help = new HelpViews();
					help.helpViewMoneyTransaction(userId);
					
				}
				
			}while(!isTransferSuccess);				
		}
		
		log.info("Transfer Funds in My Accounts END");
	}
	
	public void transferFundsSomeoneElseAccount(int userId) {
		
		log.info("Transfer Funds in Someone Else Account START");
		boolean isTransferSuccess = false;
		double transferMoney = 0;
		int sourceAccount = 0;
		int transactionId = 3;
		
		if(!accountServices.hasEnoughOpenAccounts(userId)) {
			log.info("You must have an open account to transfer funds");
			System.out.println("\nINFO: You must have an open account to transfer funds");
		} else {
			
			do {
				sourceAccount = getAccountIdFromUser(userId, transactionId);		
				transferMoney = getMoneyFromUser(userId);	
				
				if(accountServices.hasEnoughBalanceForWithdrawal(sourceAccount, transferMoney)) {
					
					int targetAccount = getTargetAccountId(userId, transactionId);
					
					accountServices.isMakeAWithdrawal(sourceAccount, transferMoney);
					accountServices.isMakeADeposit(targetAccount, transferMoney);
					
					log.info("Transfer completed successfully");
					System.out.println(GREEN_BOLD + "\nINFO: Transfer completed successfully" + RESET);
					new AccountViews().showAccountByAccountId(sourceAccount, userId);
					isTransferSuccess = true;
					
				} else {
					log.warn("WARNING: Transaction failed!");
					System.out.println("\nWARNING: Transaction failed!");
					HelpViews help = new HelpViews();
					help.helpViewMoneyTransaction(userId);
				}
				
			}while(!isTransferSuccess);				
		}
		
		log.info("Transfer Funds in Someone Else Account END");
	}
	
	public void transferFundsForAdmin(int userId) {
		
		log.info("Transfer Funds for Admin START");
		
		boolean isTransferSuccess = false;
		double transferMoney = 0;
		int sourceAccount = 0;
		int transactionId = 3;
		
		do {
			sourceAccount = getAccountIdFromUser(userId, transactionId);		
			transferMoney = getMoneyFromUser(userId);	
			
			if(accountServices.hasEnoughBalanceForWithdrawal(sourceAccount, transferMoney)) {
				
				int targetAccount = getTargetAccountId(userId, transactionId);
					
				accountServices.isMakeAWithdrawal(sourceAccount, transferMoney);
				accountServices.isMakeADeposit(targetAccount, transferMoney);

				log.info("Transfer completed successfully");
				System.out.println(GREEN_BOLD + "\nINFO: Transfer completed successfully" + RESET);
				new AccountViews().showAccountByAccountId(sourceAccount, userId);
				new AccountViews().showAccountByAccountId(targetAccount, userId);
				isTransferSuccess = true;
					
			} else {
				log.warn("WARNING: Transaction failed!");
				System.out.println(RED_BOLD + "\nWARNING: Transaction failed!" + RESET);
				HelpViews help = new HelpViews();
				help.helpViewMoneyTransaction(userId);
				
			}
						
		}while(!isTransferSuccess);		

		log.info("Transfer Funds for Admin END");
	}
	
	
	public int getTargetAccountId(int userId, int transactionId) {			

		log.info("Get Target Account Number START");
		
		String accountNum = null;
		HelpViews help = new HelpViews();
		
		do {		
			
			if(transactionId == 3) {
				System.out.println("\nEnter the account number to tranfer TO: ");
			}
			accountNum = sc.nextLine();					
			
			result[0] = accountServices.checkTargetAccountId(accountNum, userId);
			
			if(result[0]) {
				accountId = Integer.parseInt(accountNum);
				return accountId;
			} else {
				help.helpViewMoneyTransaction(userId);	
				getTargetAccountId(userId, transactionId);
			}			
		}while(!result[0]);
		
		log.info("Get Target Account Number END");
		
		return accountId;	
		
	}	
	
	
	public int getAccountIdFromUser(int userId, int transactionId) {	
		
		log.info("Get Account Number from User START");

		String accountNum = null;
		HelpViews help = new HelpViews();
		String role = accountServices.getUserRole(userId);
		
		do {
			
			if(transactionId == 1) {
				System.out.println("\nEnter the account number for this withrawal: ");
			} else if(transactionId == 2) {
				System.out.println("\nEnter the account number for this deposit ");
			} else if(transactionId == 3) {
				System.out.println("\nEnter the account number to tranfer FROM: ");
			} else if(transactionId == 4) {
				System.out.println("\nEnter the account number to tranfer TO: ");
			} 
			accountNum = sc.nextLine();					
			
			if(role.equals("Admin")) {
				result[0] = accountServices.checkAccountId(accountNum, userId);
			} else {
				result[0] = accountServices.checkAccountId(accountNum, userId);
			}			
			
			if(result[0]) {
				accountId = Integer.parseInt(accountNum);
				return accountId;
			} else {
				help.helpViewMoneyTransaction(userId);	
				getAccountIdFromUser(userId, transactionId);
			}
	
			
		}while(!result[0]);
		
		log.info("Get Account Number from User END");
		return accountId;		
	}	
	
	
	public double getMoneyFromUser(int userId) {
		
		log.info("Get Money from User START");
		
		String money = null;
		boolean isValidMoney = false;	
		double amount = 0.0D;

		do {			
			System.out.println("\n2.Enter amount: ");
			money = sc.nextLine();
			
			if(accountServices.isValidMoney(money, userId)) {
				amount = Double.parseDouble(money);
				isValidMoney = true;
			}			
					
		}while(!isValidMoney);
		
		log.info("Get Money from User END");
		return amount;
		
	}

}
