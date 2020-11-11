package com.bankingapp.authorizationview;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.service.*;


public class LoginView {
	
	private static final Logger log = Logger.getLogger(LoginView.class);
	private UserServices userServices = new UserServicesImpl();
	private AccountServices accountServices = new AccountServicesImpl();
	private LocalWeatherServices localWeatherServices = new LocalWeatherServicesImpl();
	private StockMarketServices stockMarketServices = new StockMarketServicesImpl();
	
	Scanner sc = new Scanner(System.in);
	
	public static final String RESET = "\033[0m";
	public static final String GREEN_BOLD = "\033[1;32m";
	public static final String RED_BOLD = "\033[1;31m"; 
	
	public void loginView() {
	
		log.info("Login View START");
		
		String str = null;	
		
		do {
			
			System.out.println("1.Sign in");
			System.out.println("2.Sign up");
			System.out.println("0.Sign out");
			System.out.println("7.Exit");
			
			str = sc.nextLine();
			
			if(str.equals("1")) {	
				login();
				
			} else if(str.equals("2")) {					
				register();	
				
			} else if(str.equals("0")) {
				signOut();
		
			} else if(str.equals("7")) {
				System.out.println("Banking Application Ended successfully");
				log.warn("BANKING APPLICATION END");
				System.exit(0);
		
			} else {
				
				System.out.println("\nWARNING: Invalid choice. Please try again");
				log.warn("WARNING: Invalid choice. Please try again");
			}
		} while(!str.equals("7"));

		log.info("Login View END");
		
	}
	
	
	public void login() {
		
		log.info("Login START");
		
		String username = null;
		String password = null;
		
		System.out.print("Enter your username: ");
		username = sc.nextLine();
		System.out.print("Enter your password: ");
		password = sc.nextLine();
		
		if(userServices.checkUsernameAndPassword(username, password)) {
			log.info("Sign in successful");
			int userId = userServices.getLoginId(username, password);
			LocalDateTime lastLoginTime = userServices.getLastLoginTime(userId);
			
			HomeView homePage = new HomeView();
			
			homePage.welcomeHeader(userId, lastLoginTime);			
			userServices.setLoginTime(userId);
			
			homePage.roleMenu(userId);
			
		} else {
			log.warn("WARNING: Wrong password! Please try again or enroll!");
			System.out.println("\nWARNING: Wrong password! Please try again or enroll!");
		}	
		
		log.info("Login END");
		
	}
	
	
	public void register() {
		
		String username=null;
		int roleId = 3;
		
		log.info("Register START");
		
		do {			
			sc = new Scanner(System.in);
			System.out.println("Enter your username: ");
			username = sc.nextLine();
			username=username.trim();
			
			if(!userServices.usernameIsAvailable(username)) {
				log.warn("WARNING: This username is not available. Try a new one!");
				System.out.println("WARNING: This username is not available. Try a new one!");
			} else if(username.length() == 0) {
				log.warn("WARNING: Enter a valid username!");
				System.out.println("WARNING: Enter a valid username!");
			}
			
		} while(!userServices.usernameIsAvailable(username));
		
		
		String firstPassword=null;
		String secondPassword=null;
		
		do {
			System.out.println("Enter your password: ");
			firstPassword = sc.nextLine();
			firstPassword = firstPassword.trim();
			
			System.out.println("Confirm your password: ");
			secondPassword = sc.nextLine();
			secondPassword = secondPassword.trim();
			
			if(!firstPassword.equals(secondPassword)){
				log.warn("WARNING: Your passwords do not match. Try again!");
				System.out.println("\nWARNING: Your passwords do not match. Try again!");
			} else if(firstPassword.length() == 0 || secondPassword.length() == 0) {
				log.warn("WARNING: Enter a valid password!");
				System.out.println("\nWARNING: Enter a valid password!");
			}
			
		} while(!firstPassword.equals(secondPassword) || firstPassword.length() == 0 || secondPassword.length() == 0);
	
		
		String firstName = null;
		do {
			
			System.out.println("Enter your first name: ");
			firstName = sc.nextLine();
			firstName = firstName.trim();
			
			if(firstName.length() == 0) {
				log.warn("WARNING: Enter a valid first name!");
				System.out.println("\nWARNING: Enter a valid first name!");
			}
			
		} while(firstName.length() == 0);
		
		String lastName = null;
		do {
			
			System.out.println("Enter your last name: ");
			lastName = sc.nextLine();
			lastName = lastName.trim();
			
			if(lastName.length() == 0) {
				log.warn("WARNING: Enter a valid last name!");
				System.out.println("\nWARNING: Enter a valid last name!");
			}
			
		} while(lastName.length() == 0);
		
		String email=null;
		
		do {	
			sc = new Scanner(System.in);
			System.out.println("Enter your email: ");
			email = sc.nextLine();
			email=email.trim();
			
			if(!userServices.emailIsAvailable(email)) {
				log.warn("WARNING: This email is not available. Try a new one!");
				System.out.println("\nWARNING: This email is not available. Try a new one!");
			} else if(email.length() == 0) {
				log.warn("WARNING: Please enter valid email!");
				System.out.println("\nWARNING: Please enter valid email!");
			}
			
		} while(!userServices.emailIsAvailable(email) || email.length() == 0);
		
		boolean isUserRegistered = userServices.register(username, secondPassword, firstName, lastName, email,roleId);
		
		if(isUserRegistered) {
			log.info("Congrats. You enrolled in ADDIE's Bank");
			System.out.println(GREEN_BOLD + "\nINFO: Congrats. You enrolled in ADDIE's Bank" + RESET);
		} else {
			log.info("User not created");
			System.out.println(RED_BOLD + "\nINFO User not enrolled" + RESET);
		}
		
		log.info("Register END");
		
	}
	
	@SuppressWarnings("static-access")
	public void signOut() {
		log.info("Sign Out: Good Bye, Have a good one START");
		System.out.println(GREEN_BOLD + "Good Bye, Have a good one" + RESET);
		log.info("BANKING APPLICATION END");
		
		userServices.closeResources();	
		accountServices.closeResources();
		localWeatherServices.closeResources();
		stockMarketServices.closeResources();
		
		new MainDriver().startBankApplication();
		
		System.exit(0);		
	}

}
