package com.bankingapp.homeview;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.authorizationview.*;
import com.bankingapp.service.*;

public class UserViews {

	private UserServices userServices = new UserServicesImpl();
	Scanner sc = new Scanner(System.in);
	private static final Logger log = Logger.getLogger(UserViews.class);
	HelpViews helper = new HelpViews();
	
	/////////////////USER PERSONAL INFO MENU///////////////////////////
	public void userPersonalInfoView(int userId) {
		
		log.info("User Personal Info View START");
		String str = null;
		
		do {
			helper.printSubTitle("USER PERSONAL INFO MENU");		
			sc = new Scanner(System.in);
	
			System.out.println("1. Show Personal Info");	
			System.out.println("2. Main Menu");	
			System.out.println("0. Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  showUserPersonalInfo(userId);
					  break;
				  case "2":
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
		
		log.info("User Personal Info View END");
		
	}
	
	
	/////////////////EMPLOYEE CUSTOMER INFO MENU///////////////////////////
	public void employeeCostumerPersonalInfoView(int userId) {
		
		log.info("Employee Customer Info View START");
		
		String str = null;
		
		do {
			helper.printSubTitle("CUSTOMERS PERSONAL INFO MENU");		
			sc = new Scanner(System.in);
	
			System.out.println("1. Show All Customers Personal Info");	
			System.out.println("2. Main Menu");	
			System.out.println("0. Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  showAllUserPersonalInfo();
					  break;
				  case "2":
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
		
		log.info("Employee Customer Info View END");
		
	}
	
	/////////////////ADMIN CUSTOMER INFO MENU///////////////////////////
	public void adminCostumerPersonalInfoView(int userId) {
		
		log.info("Admin Customer Info View START");
		String str = null;
		
		do {

			helper.printSubTitle("CUSTOMERS PERSONAL INFO MENU");		
			sc = new Scanner(System.in);
	
			System.out.println("1. Show All Custormers Personal Info");	
			System.out.println("2. Main Menu");	
			System.out.println("0. Sign out");
			
				str = sc.nextLine();
				switch (str) {
				  case "1":
					  showAllUserPersonalInfo();
					  break;
				  case "2":
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
		log.info("Admin Customer Info View END");
	}
	
	
	public void showUserPersonalInfo(int userId) {
		
		log.info("Show User Personal Info START");
		helper.printSubTitle("YOUR PERSONAL INFO MENU");	
		boolean hasPersonalInfo = userServices.getUser(userId);
		
		if(!hasPersonalInfo) {
			log.warn("WARNING: You do not have any personal info!");
			System.out.println("\nWARNING: You do not have any personal info!");
		}	
		
		log.info("Show User Personal Info END");
	}

	public void showAllUserPersonalInfo() {
		
		log.info("Show All Users Personal Info START");
		helper.printSubTitle("ALL CUSTOMERS PERSONAL INFO MENU");
		boolean hasPersonalInfo = userServices.getAllUsers();
		
		if(!hasPersonalInfo) {
			log.warn("WARNING: No user in the system yet!");
			System.out.println("\nWARNING: No user in the system yet!");

		}	
		
		log.info("Show All Users Personal Info END");
	}
	
	
}
