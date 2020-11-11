package com.bankingapp.homeview;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.authorizationview.*;
import com.bankingapp.service.*;

public class StockMarketViews {

	private static final Logger log = Logger.getLogger(StockMarketViews.class);
	private AccountServices accountServices = new AccountServicesImpl();
	private static LocalWeatherServices localWeatherServices = new LocalWeatherServicesImpl();
	private static StockMarketServices stockMarketServices = new StockMarketServicesImpl();
	HelpViews helper = new HelpViews();
	Scanner sc = new Scanner(System.in);
	
	public static final String RESET = "\033[0m";
	public static final String GREEN_BOLD = "\033[1;32m";
	public static final String RED_BOLD = "\033[1;31m";  
	
	/////////////////STOCK MARKET MENU///////////////////////////
	public void stockMarketView(int userId) {
		
		log.info("stockMarketView START");
		String str = null;
		String role = accountServices.getUserRole(userId);
		
		if(role.equals("Admin")) {
			
			do {
				helper.printSubTitle("STOCK MARKET MENU");	
				sc = new Scanner(System.in);
		
				System.out.println("1. Show Stock Market");	
				System.out.println("2. Update Web Portal");					
				System.out.println("3. Main Menu");	
				System.out.println("0. Sign out");
				
					str = sc.nextLine();
					switch (str) {
					  case "1":
						  showStockMarket();
						  break;
					  case "2":
						  updateWebPortal();
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
			
		} else {
			
			do {
				helper.printSubTitle("STOCK MARKET MENU");	
				sc = new Scanner(System.in);
		
				System.out.println("1. Show Stock Market");	
				System.out.println("2. Main Menu");	
				System.out.println("0. Sign out");
				
					str = sc.nextLine();
					switch (str) {
					  case "1":
						  showStockMarket();
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
			
		}
		
		log.info("stockMarketView END");	
	}
	
	
	public void showStockMarket() {
		
		log.info("showStockMarket START");
		
		String stockCode = "";
		boolean result = false;
		
		do {
			
			System.out.println("\nEnter your company stock market code: ");

			stockCode = sc.nextLine();					
			
			result = stockMarketServices.getStockMarketByCode(stockCode);
			
			if(result == false) {
				log.info("This compant stock market code is invalid, Please try again!");
				System.out.println("\nWARNING: This compant stock market code is invalid, Please try again!");
			}
			
		}while(!result);

		log.info("showStockMarket END");
		
	}
	
	public void updateWebPortal() {
		
		log.info("updateWebPortal START");
		
		if(localWeatherServices.addLocalWeatherData()) {
			log.info("Local Weather data updated successfully");
			System.out.println(GREEN_BOLD + "\nINFO: Local Weather data updated successfully" + RESET);
		} else {
			log.info("Local Weather data update failed");
			System.out.println(RED_BOLD + "\nINFO: Local Weather data update failed" + RESET);
		}
		
		if(stockMarketServices.addFaangStockMarket()) {
			log.info("Stock Market data updated successfully");
			System.out.println(GREEN_BOLD + "\nINFO: Stock Market data updated successfully" + RESET);
		} else {
			log.info("Stock Market data update failed");
			System.out.println(RED_BOLD + "\nINFO: Stock Market data update failed" + RESET);
		}
		
		log.info("updateWebPortal END");
	}
	
}
