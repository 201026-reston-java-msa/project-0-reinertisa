package com.bankingapp.authorizationview;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.homeview.*;
import com.bankingapp.service.*;

public class HomeView {
	
	private UserServices userServices = new UserServicesImpl();
	private static final Logger log = Logger.getLogger(HomeView.class);
	HelpViews helper = new HelpViews();
	Scanner sc = new Scanner(System.in);
	

	public void welcomeHeader(int userId, LocalDateTime lastLoginTime) {
		
		log.info("welcomeHeader START");
		
		String username = userServices.getUsername(userId);
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        String lastLoginTimeFormated = lastLoginTime.format(formatter);
        
        helper.printTitle(userId, username, lastLoginTimeFormated);
        
    	log.info("welcomeHeader END");
	}	
	
	public void roleMenu(int userId) {

		log.info("Role Menu START");
		String role = userServices.getUserRole(userId);
		
		if(role.equals("Standard")) {
			userMenu(userId);
		} else if(role.equals("Employee")) {
			employeeMenu(userId);
		} else if(role.equals("Admin")) {
			adminMenu(userId);
		}
		log.info("Role Menu END");
				
	}
	
	public void userMenu(int userId) {
		
		log.info("User Menu START");
		String str = null;
			
		
		do {
			helper.printSubTitle("USER MAIN MENU");
			sc = new Scanner(System.in);
		
			System.out.println("1. Accounts");	
			System.out.println("2. Withdraw Money");
			System.out.println("3. Deposit Money");
			System.out.println("4. Transfer Money");
			System.out.println("5. Personal Information");
			System.out.println("6. Stock Market");
			System.out.println("0. Sign out");
			
			str = sc.nextLine();			
			switch (str) {			
				  case "1":
					new AccountViews().userAccountView(userId);
				    break;
				  case "2":
				    new TransactionsViews().makeAWithdrawal(userId);
				    break;
				  case "3":
					new TransactionsViews().makeADeposit(userId);
				    break;
				  case "4":
					new TransactionsViews().transferFunds(userId);
				    break;
				  case "5":
					new UserViews().userPersonalInfoView(userId);
				    break;	
				  case "6":
					new StockMarketViews().stockMarketView(userId);
				    break;	
				  case "0":
					  new LoginView().signOut();
					 break;
				  default:	
					  System.out.println("\nWARNING: Invalid choice. Please try again");
					  log.warn("WARNING: Invalid choice. Please try again");
			}
			
		} while(!str.equals("0"));
		
		log.info("User Menu END");
		
	}
	
	
	public void employeeMenu(int userId) {
		
		log.info("Employee Menu START");
		String str = null;
		
		do {
			helper.printSubTitle("EMPLOYEE MAIN MENU");		
			sc = new Scanner(System.in);
		
			System.out.println("1. Accounts");	
			System.out.println("2. Withdraw Money");
			System.out.println("3. Deposit Money");
			System.out.println("4. Transfer Money");
			System.out.println("5. Customer Information");
			System.out.println("6. Stock Market");
			System.out.println("0. Sign out");
			
			str = sc.nextLine();			
			switch (str) {			
				  case "1":
					new AccountViews().employeeAccountView(userId);
				    break;
				  case "2":
					new TransactionsViews().makeAWithdrawal(userId);
					break;
				  case "3":
					new TransactionsViews().makeADeposit(userId);
					break;
				  case "4":
					new TransactionsViews().transferFunds(userId);
					break;
				  case "5":
					new UserViews().employeeCostumerPersonalInfoView(userId);
					break;
				  case "6":
					new StockMarketViews().stockMarketView(userId);
				    break;	
				  case "0":
					new LoginView().signOut();
					break;
				  default:
					  System.out.println("WARNING: Invalid choice. Please try again");
					  log.warn("WARNING: Invalid choice. Please try again");
			}
			
		} while(!str.equals("0"));
		
		log.info("Employee Menu END");
	}
	
	
	public void adminMenu(int userId) {
		
		log.info("Admin Menu START");
		String str = null;
		
		do {
			helper.printSubTitle("ADMIN MAIN MENU");
			sc = new Scanner(System.in);
		
			System.out.println("1. Accounts");	
			System.out.println("2. Withdraw Money");
			System.out.println("3. Deposit Money");
			System.out.println("4. Transfer Money");
			System.out.println("5. Customer Information");
			System.out.println("6. Stock Market");
			System.out.println("0. Sign out");
			
			str = sc.nextLine();			
			switch (str) {			
				  case "1":
					new AccountViews().adminAccountView(userId);
				    break;
				  case "2":
					new TransactionsViews().makeAWithdrawal(userId);
					break;
				  case "3":
					new TransactionsViews().makeADeposit(userId);
					break;
				  case "4":
				    new TransactionsViews().transferFunds(userId);
					break;
				  case "5":
					new UserViews().adminCostumerPersonalInfoView(userId);
					break;
				  case "6":
					new StockMarketViews().stockMarketView(userId);
				    break;	
				  case "0":
					new LoginView().signOut();
					break;
				  default:
					  System.out.println("\nWARNING: Invalid choice. Please try again");
					  log.warn("WARNING: Invalid choice. Please try again");
			}
			
		} while(!str.equals("0"));
		
		log.info("Admin Menu END");
		
	}
	
	
	
}
