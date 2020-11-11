package com.bankingapp.homeview;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.authorizationview.*;
import com.bankingapp.service.*;

public class AccountViews {	
	
	Scanner sc = new Scanner(System.in);
	HelpViews helper = new HelpViews();
	static boolean[] result = new boolean[1];
	static boolean[] flag = new boolean[1];
	static int accountId = 0;	
	private AccountServices accountServices = new AccountServicesImpl();	
	private static final Logger log = Logger.getLogger(AccountViews.class);
	
	public static final String RESET = "\033[0m";
	public static final String GREEN_BOLD = "\033[1;32m";
	public static final String RED_BOLD = "\033[1;31m";  
	
	/////////////////USER ACCOUNTS MENU///////////////////////////
	public void userAccountView(int userId) {
		
		log.info("User Account View START");
		String str = null;
		
		do {
			helper.printSubTitle("USER ACCOUNT MENU");	
			sc = new Scanner(System.in);
	
			System.out.println("1. Show Accounts");	
			System.out.println("2. Create Account");
			System.out.println("3. Main Menu");	
			System.out.println("0. Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  showUserAccounts(userId);
					  break;
				  case "2":
					  createAccount(userId);
					  break;
				  case "3":
					  new HomeView().roleMenu(userId);
					  break;
				  case "0":
					  new LoginView().signOut();
					  break;
				  default:
					log.warn("WARNING: Invalid choice. Please try again");
					System.out.println("\nWARNING: Invalid choice. Please try again");
				}
			} while(!str.equals("0"));
		
		log.info("User Account View END");
		
	}
	
	
	/////////////////EMPLOYEE ACCOUNTS MENU///////////////////////////
	public void employeeAccountView(int userId) {
		
		log.info("Employee Account View START");
		String str = null;
		
		do {
			
			helper.printSubTitle("EMPLOYEE ACCOUNT MENU");	
			sc = new Scanner(System.in);
	
			System.out.println("1. Approve / Deny Account");
			System.out.println("2. Show Pending Accounts");
			System.out.println("3. Show Open Accounts");
			System.out.println("4. Show All Accounts");	
			System.out.println("5. Create Account");
			System.out.println("6. Main Menu");	
			System.out.println("0. Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  updateAccount(userId);
					  break;
				  case "2":
					  showAllAccountsByStatus(1);				
					  break;
				  case "3":
					  showAllAccountsByStatus(2);
					  break;
				  case "4":
					  showAllAccounts();
					  break;
				  case "5":
					  createAccount(userId);
					  break;
				  case "6":
					  new HomeView().roleMenu(userId);
					  break;
				  case "0":
					  new LoginView().signOut();
					  break;
				  default:
					  log.warn("WARNING: Invalid choice. Please try again");
					  System.out.println("\nWARNING: Invalid choice. Please try again");
				}
			} while(!str.equals("0"));
		
		log.info("Employee Account View END");
		
	}

	/////////////////ADMIN ACCOUNTS MENU///////////////////////////
	public void adminAccountView(int userId) {
		
		log.info("Admin Account View START");
		String str = null;
		
		do {
			helper.printSubTitle("ADMIN ACCOUNT MENU");
			sc = new Scanner(System.in);
	
			System.out.println("1. Approve / Deny / Cancel Account");
			System.out.println("2. Show Pending Accounts");
			System.out.println("3. Show Open Accounts");
			System.out.println("4. Show All Accounts");	
			System.out.println("5. Create Account");
			System.out.println("6. Main Menu");	
			System.out.println("0. Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  updateAccount(userId);
					  break;
				  case "2":
					  showAllAccountsByStatus(1);				
					  break;
				  case "3":
					  showAllAccountsByStatus(2);
					  break;
				  case "4":
					  showAllAccounts();
					  break;
				  case "5":
					  createAccount(userId);
					  break;
				  case "6":
					  new HomeView().roleMenu(userId);
					  break;
				  case "0":
					  new LoginView().signOut();
					  break;
				  default:
					  log.warn("WARNING: Invalid choice. Please try again");
					  System.out.println("\nWARNING: Invalid choice. Please try again");
				}
			} while(!str.equals("0"));
		log.info("Admin Account View END");
	}
	
	
	/////////////////SHOW ACCOUNTS///////////////////////////
	
	public void showUserAccounts(int userId) {
		
		log.info("Show User Accounts START");
		helper.printSubTitle("ACCOUNTS");
		boolean hasAccounts = accountServices.getAllAccountsByUserId(userId);
		
		if(!hasAccounts) {
			log.warn("WARNING: You do not have any account!");
			System.out.println("\nWARNING: You do not have any account!");
			HelpViews help = new HelpViews();
			help.helpViewForNoAccount(userId);
		}	
		
		log.info("Show User Accounts END");
	}

	public void showAllAccounts() {
		
		log.info("Show All Accounts START");
		helper.printSubTitle("ALL USER ACCOUNTS");

		boolean hasAccounts = accountServices.getAllAccounts();
		
		if(!hasAccounts) {
			log.warn("WARNING: There is no any account in the system!");
			System.out.println("\nWARNING: There is no any account in the system!");
		}	
		
		log.info("Show All Accounts END");
	}
	
	public void showAllAccountsByStatus(int statusId) {
		
		log.info("Show All Accounts By Status START");

		if(statusId == 1) {
			helper.printSubTitle("ALL PENDING ACCOUNTS");
		} else if(statusId == 2) {
			helper.printSubTitle("ALL OPEN ACCOUNTS");
		} 
		
 		
		boolean hasAccounts = accountServices.getAllAccountsByStatus(statusId);
		
		if(!hasAccounts) { 
			
			if(statusId == 1) {
				log.warn("WARNING: Pending account not found in the system!");
				System.out.println("\nWARNING: Pending account not found in the system!");
			} else if(statusId == 2) {
				log.warn("WARNING: Open account not found in the system!");
				System.out.println("\nWARNING: Open account not found in the system!");
			}
		}		
		
		log.info("Show All Accounts By Status END");
	}
	
	
	public void showAccountByAccountId(int accountId, int userId) {
		
		log.info("Show User Account By AccountId START");
		
		helper.printSubTitle("ACCOUNTS");
		boolean hasAccounts = accountServices.getAccountByAccountId(accountId);
		
		if(!hasAccounts) {
			log.warn("WARNING: You do not have any account!");
			System.out.println("\nWARNING: You do not have any account!");
			HelpViews help = new HelpViews();
			help.helpViewForNoAccount(userId);
		}	
		
		log.info("Show User Account By AccountId END");
	}
	
	
	
	/////////////////CREATE ACCOUNTS///////////////////////////
	
	
	public void createAccount(int userId) {
		
		log.info("Create Account START");
		String str = null;
		
		do {
			helper.printSubTitle("OPEN AN ACCOUNT MENU");
			System.out.println("1. Open Checking account");
			System.out.println("2. Open Savings account");
			System.out.println("3. Main Menu");
			System.out.println("0. Sign out");			
			
			str = sc.nextLine();
			switch (str) {
			  case "1":
				  createCheckingAccount(userId);
			    break;
			  case "2":
				  createSavingsAccount(userId);
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
		
		log.info("Create Account END");
	}

	public void createCheckingAccount(int userId) {
		
		log.info("Create Checking Account START");
		
		boolean isAccountCreated = false;
		HelpViews help = new HelpViews();
		
		String amount = null;
		do {
			
			System.out.println("Enter your cash(min 25$): ");	
			amount = sc.nextLine();
			
			isAccountCreated = accountServices.isCreateCheckingAccount(userId, amount);
			
			if(isAccountCreated) {
				log.info("Your checking account created successfully");
				System.out.println(GREEN_BOLD + "\nINFO: Your checking account created successfully" + RESET);
				showUserAccounts(userId);
			} else {
				help.helpViewForCreateAccount(userId);
			}			
			
		} while(!isAccountCreated);
		
		log.info("Create Checking Account END");
		
	}
	
	public void createSavingsAccount(int userId) {
		
		log.info("Create Savings Account START");
		
		boolean isAccountCreated = false;
		HelpViews help = new HelpViews();
		
		String amount = null;
		do {
			
			System.out.println("Enter your cash(min 100$): ");	
			amount = sc.nextLine();
			
			isAccountCreated = accountServices.isCreateSavingsAccount(userId, amount);
			
			if(isAccountCreated) {
				log.info("Your savings account created successfully");
				System.out.println(GREEN_BOLD + "\nINFO: Your savings account created successfully" + RESET);
				showUserAccounts(userId);
			} else {
				help.helpViewForCreateAccount(userId);
			}
			
		} while(!isAccountCreated);
		
		log.info("Create Savings Account END");
		
	}
	
	
	/////////////////UPDATE ACCOUNTS///////////////////////////
	public void updateAccount(int userId) {
		
		log.info("Update Account START");
		
		String role = accountServices.getUserRole(userId);
		String str = null;
		
		if(role.equals("Admin")) {
			
			int accountNum = getAccountIdToUpdateAccountForAdmin(userId); // admin(*all account status)
	
			do {				
				System.out.println("\n1. Approve");
				System.out.println("2. Deny");
				System.out.println("3. Cancel");
				System.out.println("4. Main Menu");
				System.out.println("7. Cancel Transaction");
				System.out.println("0. Sign Out");
				
				str = sc.nextLine();
			
				if(str.equals("1")) {
					approveAccount(accountNum, userId);
					str = "0";
				} else if(str.equals("2") ) {
					denyAccount(accountNum, userId);
					str = "0";
				} else if(str.equals("3") ) {
					cancelAccount(accountNum, userId);
					str = "0";
				} else if(str.equals("4")){
					new HomeView().roleMenu(userId);
					str = "0";
				} else if(str.equals("0")){
					new LoginView().signOut();
				} else if(str.equals("7")){
					log.info("Transaction cancelled succesfully");
					System.out.println(GREEN_BOLD + "\nINFO: Transaction cancelled succesfully" +  RESET);
					str = "0";
				} else {
					log.warn("WARNING: Invalid choice. Please try again");		
					System.out.println("\nWARNING: Invalid choice. Please try again");
				}					
			} while(!str.equals("0"));			
			
		} else {	
			
			int accountNum = getAccountIdToUpdateAccount(userId); // employee(* just pending status)
			
			do {				
				System.out.println("\n1. Approve");
				System.out.println("2. Deny");
				System.out.println("3. Main Menu");
				System.out.println("7. Cancel Transaction");
				System.out.println("0. Sign Out");
				
				str = sc.nextLine();
			
				if(str.equals("1")) {
					approveAccount(accountNum, userId);
					str = "0";
				} else if(str.equals("2") ) {
					denyAccount(accountNum, userId);
					str = "0";
				} else if(str.equals("3")){
					new HomeView().roleMenu(userId);
					str = "0";
				} else if(str.equals("0")){
					new LoginView().signOut();
				} else if(str.equals("7")){
					log.info("Transaction cancelled succesfully");
					System.out.println(GREEN_BOLD + "\nINFO: Transaction cancelled succesfully" + RESET);
					str = "0";
				} else {
					log.warn("WARNING: Invalid choice. Please try again");	
					System.out.println("\nWARNING: Invalid choice. Please try again");
				}					
			} while(!str.equals("0"));		
			
		}		
		
		log.info("Update Account END");
	}	
	
	public void approveAccount(int accountNum, int userId) {
		
		log.info("Approve Account START");
		
		int statusid=2; // it mean status will be open
		
		if(accountServices.updateAccountStatus(accountNum, statusid)) {
			log.info("Approved successful");
			System.out.println(GREEN_BOLD + "\nINFO: Approved successful" + RESET);
			new AccountViews().showAccountByAccountId(accountNum, userId);
		} else {
			log.info("Approved failed");
			System.out.println(RED_BOLD + "\nINFO: Approved failed" + RESET);
		}		
		
		log.info("Approve Account END");
	}
	
	public void denyAccount(int accountNum, int userId) {
		
		log.info("Deny Account START");
		
		int statusid=4; // it mean status will be denied
		
		if(accountServices.updateAccountStatus(accountNum, statusid)) {
			log.info("Denied successful");
			System.out.println(GREEN_BOLD + "\nINFO: Denied successful" + RESET);
			new AccountViews().showAccountByAccountId(accountNum, userId);
		} else {
			log.info("Denied failed");
			System.out.println(RED_BOLD + "\nINFO: Denied failed" + RESET);
		}	
		
		log.info("Deny Account END");
	}	
	
	public void cancelAccount(int accountNum, int userId) {
		
		log.info("Cancel Account START");
		
		int statusid=3; // it mean status will be canceled
		
		if(accountServices.updateAccountStatus(accountNum, statusid)) {
			log.info("Canceled successful");
			System.out.println(GREEN_BOLD + "\nINFO: Canceled successful" + RESET);
			new AccountViews().showAccountByAccountId(accountNum, userId);
		} else {
			log.info("Canceled failed");
			System.out.println(RED_BOLD + "\nINFO: Canceled failed" + RESET);
		}	
		
		log.info("Cancel Account END");
	}	
	
	public int getAccountIdToUpdateAccount(int userId) { // Pending Accounts	

		log.info("Get Account Number to Update Account START");
		
		String accountNum = null;
		HelpViews help = new HelpViews();
		
		do {
			System.out.println("\nWhich account do you want to approve \\ deny: ");
			
			accountNum = sc.nextLine();					
			
			result[0] = accountServices.checkApproveDenyAccountId(accountNum, userId);
			
			if(result[0]) {
				accountId = Integer.parseInt(accountNum);
				return accountId;
			} else {
				help.helpViewUpdateAccount(userId);
				getAccountIdToUpdateAccount(userId);
			}
	
			
		}while(!result[0]);
		
		log.info("Get Account Number to Update Account END");
		
		return accountId;		
	}		
	
	public int getAccountIdToUpdateAccountForAdmin(int userId) {		
		
		log.info("Get Account Number to Update Account For Admin START");

		String accountNum = null;
		HelpViews help = new HelpViews();
		
		do {
			
			System.out.println("\nWhich account do you want to approve \\ deny \\ cancel: ");

			accountNum = sc.nextLine();					
			
			result[0] = accountServices.checkApproveDenyCancelAccountId(accountNum, userId);
			
			if(result[0]) {
				accountId = Integer.parseInt(accountNum);
				return accountId;
			} else {
				help.helpViewUpdateAccount(userId);
				getAccountIdToUpdateAccountForAdmin(userId);
			}
	
			
		}while(!result[0]);
		
		log.info("Get Account Number to Update Account For Admin END");
		
		return accountId;		
	}	
	
	
}
