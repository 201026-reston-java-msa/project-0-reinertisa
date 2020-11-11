package com.bankingapp.homeview;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.authorizationview.*;
import com.bankingapp.service.*;

public class HelpViews {

	Scanner sc = new Scanner(System.in);
	private static AccountServices accountServices = new AccountServicesImpl();
	private static UserServices userServices = new UserServicesImpl();

	private static final Logger log = Logger.getLogger(HelpViews.class);
	
	
	public static final String RESET = "\033[0m"; 
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE    
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; 
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
	public static final String GREEN_BOLD = "\033[1;32m";
	public static final String RED_BOLD = "\033[1;31m";  
	public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
	public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
	public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
	public static final String GREEN_UNDERLINED = "\033[4;32m";
	public static final String CYAN = "\033[0;36m";
	public static final String BLACK = "\033[0;30m";   // BLACK

	public static final String RED = "\033[0;31m"; // RED
	public static final String GREEN = "\033[0;32m"; // GREEN
	public static final String YELLOW = "\033[0;33m"; // YELLOW
	public static final String BLUE = "\033[0;34m"; // BLUE
	public static final String PURPLE = "\033[0;35m"; // PURPLE

	public static final String WHITE = "\033[0;37m"; // WHITE
	
	
	
	public void helpViewForCreateAccount(int userId) {
		
		log.info("Help View to Create Account START");
		String str = null;
		
		do {
			
			printSubTitle("LET US HELP YOU");
			
			System.out.println("HELP >>>>> 1. Main Menu");
			System.out.println("HELP >>>>> 2. Continue");	
			System.out.println("HELP >>>>> 0. Sign Out");
			
			str = sc.nextLine();
		
			if(str.equals("1")) {
				new HomeView().roleMenu(userId);
				str = "0";
			} else if(str.equals("2") ) {
				log.info("Sure...Let's Continue");
				System.out.println("\nINFO: Sure...Let's Continue");
				str = "0";
			} else if(str.equals("0")){
				new LoginView().signOut();
			} else {
				log.warn("WARNING: Invalid choice. Please try again");
				System.out.println("\nWARNING: Invalid choice. Please try again");
			}					
		} while(!str.equals("0"));
		
		log.info("Help View to Create Account END");
	}	

	public void helpViewForNoAccount(int userId) {
		
		log.info("Help View for No Account START");
		String str = null;
		
		do {
			printSubTitle("LET US HELP YOU");
			
			System.out.println("HELP >>>>> 1. Open Account");
			System.out.println("HELP >>>>> 2. Main Menu");
			System.out.println("HELP >>>>> 3. Continue");
			System.out.println("HELP >>>>> 0. Sign out");			
			
			str = sc.nextLine();
			if(str.equals("1")) {
				new AccountViews().createAccount(userId);
				str = "0";
			} else if(str.equals("2") ) {
				new HomeView().roleMenu(userId);
				str = "0";
			} else if(str.equals("3")){
				log.info("Sure...Let's Continue");
				System.out.println("Sure...Let's Continue");
				str = "0";
			} else if(str.equals("0")){
				new LoginView().signOut();
			} else {
				log.warn("WARNING: Invalid choice. Please try again");	
				System.out.println("\nWARNING: Invalid choice. Please try again");
			}
			
		} while(!str.equals("0"));
		
		log.info("Help View for No Account END");
	}	
	
	public void helpViewMoneyTransaction(int userId) {
		
		log.info("Help View for Money Transaction START");
		
		String role = accountServices.getUserRole(userId);
		String str = null;
		
		if(role.equals("Admin")) {
			
			do {
				
				printSubTitle("LET US HELP YOU");
				
				System.out.println("\nHELP >>>>> 1. Show Your Accounts");
				System.out.println("HELP >>>>> 2. Show All Accounts");
				System.out.println("HELP >>>>> 3. Main Menu");
				System.out.println("HELP >>>>> 4. Continue");	
				System.out.println("HELP >>>>> 0. Sign Out");	
				
				str = sc.nextLine();
			
				if(str.equals("1")) {
					new AccountViews().showUserAccounts(userId);
					str = "0";
				} else if(str.equals("2") ) {
					new AccountViews().showAllAccounts();
					str = "0";
				} else if(str.equals("3") ) {
					new HomeView().roleMenu(userId);
					str = "0";
				} else if(str.equals("4")){
					log.info("Sure...Let's Continue");
					System.out.println("\nINFO: Sure...Let's Continue");
					str = "0";
				} else if(str.equals("0")){
					new LoginView().signOut();
				} else {
					log.warn("WARNING: Invalid choice. Please try again");
					System.out.println("\nWARNING: Invalid choice. Please try again");
				}					
			} while(!str.equals("0"));
			
		} else {
			
			do {

				printSubTitle("LET US HELP YOU");
				
				System.out.println("\nHELP >>>>> 1. Show Your Accounts");
				System.out.println("HELP >>>>> 2. Main Menu");
				System.out.println("HELP >>>>> 3. Continue");	
				System.out.println("HELP >>>>> 0. Sign Out");	
				
				str = sc.nextLine();
			
				if(str.equals("1")) {
					new AccountViews().showUserAccounts(userId);
					str = "0";
				} else if(str.equals("2") ) {
					new HomeView().roleMenu(userId);
					str = "0";
				} else if(str.equals("3")){
					log.info("Sure...Let's Continue");
					System.out.println("\nINFO: Sure...Let's Continue");
					str = "0";
				} else if(str.equals("0")){
					new LoginView().signOut();
				} else {
					log.warn("WARNING: Invalid choice. Please try again");
					System.out.println("\nWARNING: Invalid choice. Please try again");
				}					
			} while(!str.equals("0"));
		}
		
		log.info("Help View for Money Transaction END");
		
	}	
	
	public void helpViewUpdateAccount(int userId) {
		
		log.info("Help View to Update Account START");		
		
		String str = null;
		do {
			
			printSubTitle("LET US HELP YOU");
			
			System.out.println("\nHELP >>>>> 1. Show Pending Accounts");
			System.out.println("HELP >>>>> 2. Show All Accounts");
			System.out.println("HELP >>>>> 3. Main Menu");
			System.out.println("HELP >>>>> 4. Continue");	
			System.out.println("HELP >>>>> 0. Sign Out");	
			
			str = sc.nextLine();
		
			if(str.equals("1")) {
				new AccountViews().showAllAccountsByStatus(1);
				str = "0";
			} else if(str.equals("2") ) {
				new AccountViews().showAllAccounts();
				str = "0";
			} else if(str.equals("3") ) {
				new HomeView().roleMenu(userId);
				str = "0";
			} else if(str.equals("4")){
				log.info("Sure...Let's Continue");
				System.out.println("\nINFO: Sure...Let's Continue");
				str = "0";
			} else if(str.equals("0")){
				new LoginView().signOut();
			} else {
				log.warn("WARNING: Invalid choice. Please try again");
				System.out.println("\nWARNING: Invalid choice. Please try again");
			}					
		} while(!str.equals("0"));
		
		log.info("Help View to Update Account END");
	}	
	
	public void printSubTitle(String title){
		
		log.info("printSubTitle START");	
		boolean isTitleInsert = false;
		
		int lineLen = 120;
		
		int half = (lineLen - title.length()) / 2;
		int count=1; 
		char ch = '+';
		
		
		while(count <= 3) {
			if(count % 2 == 0) {
				
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i < half) {				
						System.out.print(" ");			
					} else if(!isTitleInsert){				
						i += title.length();
						System.out.print(title);
						isTitleInsert = true;
					} else {
						System.out.print(" ");	
					}
				}
				System.out.print(ch);
				
			} else {
				
				for(int i=0; i<lineLen; i++) {			
					System.out.print(ch);
				}				
			}
			
			System.out.println();
			count++;
		}
		
		log.info("printSubTitle END");	
	}
	
	public void printTitle(int userId, String username, String lastLoginTime) {
		
		log.info("printTitle START");	
		
		String role = userServices.getUserRole(userId);
		
		String textColor = "";
		if(role.equals("Admin")){
			
			textColor = PURPLE_BRIGHT;
			
		} else if(role.equals("Employee")) {
			
			textColor = PURPLE_BRIGHT;
			
		} else if(role.equals("Standard")) {
			
			textColor = PURPLE_BRIGHT;
		}
		
		int lineLen = 120;
		
		int count=1; 
		char ch = '+';
		
		String welcome = "Welcome, " + username;
		String login = "Last Login: " + lastLoginTime;
		
		while(count <= 5) {
			if(count == 1|| count == 5) {				
				for(int i=0; i<lineLen; i++) {			
					System.out.print(ch);
				}

			} else if(count % 2 == 0){
				
				for(int i=0; i<lineLen; i++) {			
					if(i == 0 || i == lineLen-1) {
						System.out.print(ch);
					} else {
						System.out.print(" ");
					}
				}
				
			} else {
				
				for(int i=0; i<lineLen; i++) {	
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i == 2) {
						System.out.print(textColor + welcome + RESET);
						i += welcome.length();
					} else if(i + login.length() == lineLen-1){
						System.out.print(textColor + login + RESET);
						i += login.length();
					} else {
						System.out.print(" ");
					}
				}	
				System.out.print(" " + ch);
			}
			System.out.println();
			count++;
		}
		System.out.println();
		log.info("printTitle END");	
		
	}
	
	public void printHeader(String title, List<String> weatherInfo, String localTime, List<String> faang) {
		
		log.info("printHeader START");	
		
		int lineLen = 120;
		
		int count=1; 
		int index = 0;
		char ch = '+';
		
		int half = (lineLen - title.length()) / 2;
		boolean isTitleInsert = false;
		int j=0;
		
		
		while(count <= 9) {
			
			if(count == 1 || count == 9) {
				
				for(int i=0; i<lineLen; i++) {			
					System.out.print(ch);
				}
				
			} else if(count == 2) {
				
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i < half) {				
						System.out.print(" ");			
					} else if(!isTitleInsert){				
						i += title.length();
						System.out.print(RED_BOLD + title + RESET);
						isTitleInsert = true;
					} else {
						System.out.print(" ");	
					}
				}
				
				System.out.print(ch);
				isTitleInsert = false;
				
			} else if(count == 3) {
				isTitleInsert = false;
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i == 1){
						System.out.print(" ");
					} else if(!isTitleInsert) {						
						System.out.print(GREEN_UNDERLINED + localTime + RESET);
						i += localTime.length();
						isTitleInsert = true;
					}  else if(i + faang.get(j).length() == lineLen-15){
						System.out.print(BLUE_UNDERLINED + faang.get(j) + RESET);
						i += faang.get(j).length();
						j++;
						break;
						
					} else {
						System.out.print(" ");	
					}
				}
				System.out.print(" " + ch);
				isTitleInsert = false;		
				
				
			} else if(count == 4) {
				
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i + faang.get(j).length() == lineLen-25){
						System.out.print(CYAN + faang.get(j) + RESET);
						i += faang.get(j).length();
						j++;
						break;
					} else {
						System.out.print(" ");	
					}
				}
				System.out.print(" " + ch);
				
				
			} else if(count == 5){
				isTitleInsert = false;
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i == 1) {				
						System.out.print(" ");			
					}  else if(!isTitleInsert){
						System.out.print(PURPLE_BRIGHT + weatherInfo.get(index) + RESET);
						isTitleInsert = true;
						i += weatherInfo.get(index).length();
						index++;
					} else if(i + faang.get(j).length() == lineLen-24){
						System.out.print(CYAN + faang.get(j) + RESET);
						i += faang.get(j).length();
						j++;
						break;
					
					} else {
						System.out.print(" ");	
					}
				}
				System.out.print(" " + ch);
				isTitleInsert = false;
				
			} else if(count == 6){
				isTitleInsert = false;
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i == 1) {
						System.out.print(" ");	
					} else if(!isTitleInsert){		
						System.out.print(PURPLE_BRIGHT + weatherInfo.get(index) + RESET);		
						i += weatherInfo.get(index).length();
						index++;
						isTitleInsert = true;
					} else if(i + faang.get(j).length() == lineLen-23){
						System.out.print(CYAN + faang.get(j) + RESET);
						i += faang.get(j).length();
						j++;
						break;					
					} else {				
						System.out.print(" ");
					}
				}
				System.out.print(" " + ch);
				isTitleInsert = false;
			}  else if(count == 7){
				isTitleInsert = false;
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i == 1) {
						System.out.print(" ");	
					} else if(!isTitleInsert){		
						System.out.print(PURPLE_BRIGHT + weatherInfo.get(index) + RESET);		
						i += weatherInfo.get(index).length();
						index++;
						isTitleInsert = true;
					} else if(i + faang.get(j).length() == lineLen-23){
						System.out.print(CYAN + faang.get(j) + RESET);
						i += faang.get(j).length();
						j++;
						break;					
					} else {				
						System.out.print(" ");
					}
				}
				System.out.print(" " + ch);
				isTitleInsert = false;
			} else if(count == 8){
				isTitleInsert = false;
				for(int i=0; i<lineLen; i++) {			
					
					if(i == 0) {
						System.out.print(ch);
					} else if(i == 1) {
						System.out.print(" ");	
					} else if(!isTitleInsert){		
						System.out.print(PURPLE_BRIGHT + weatherInfo.get(index) + RESET);		
						i += weatherInfo.get(index).length();
						index++;
						isTitleInsert = true;
					} else if(i + faang.get(j).length() == lineLen-23){
						System.out.print(CYAN + faang.get(j) + RESET);
						i += faang.get(j).length();
						j++;
						break;					
					} else {				
						System.out.print(" ");
					}
				}
				System.out.print(" " + ch);
				isTitleInsert = false;
			} 
			System.out.println();
			count++;
			lineLen = 120;
		}
		System.out.println();
		log.info("printHeader END");	
		
	}

}
