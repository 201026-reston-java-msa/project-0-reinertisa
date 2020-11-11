package com.bankingapp.authorizationview;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankingapp.homeview.*;
import com.bankingapp.service.*;



public class MainDriver {

	private static final Logger log = Logger.getLogger(MainDriver.class);
	private static HelpViews helper = new HelpViews();
	private static LocalWeatherServices localWeatherServices = new LocalWeatherServicesImpl();
	private static StockMarketServices stockMarketServices = new StockMarketServicesImpl();
	
	public static void main(String[] args) throws IOException {

		log.info("BANKING APPLICATION START");
		startBankApplication();
	}
	
	public static void startBankApplication() {
		MainDriver.settingHeader();
		LoginView login = new LoginView();
		login.loginView();
	}
	
	public static void settingHeader() {
		
		log.info("settingHeader START");
		
		List<String> localWeather = localWeatherServices.getLocalWeather();
		List<String> faang = stockMarketServices.getFaangStockMarket();
		String caption = "WELCOME TO ADDIE'S BANK";
		String localTime = getLocalTime();
		
		helper.printHeader(caption, localWeather, localTime, faang);	
		
		log.info("settingHeader END");
	}
	
	public static String getLocalTime() {
		
		log.info("getLocalTime START");
		
		LocalDateTime localTime = LocalDateTime.now();				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");    
		String lt = localTime.format(formatter);
	    
		log.info("getLocalTime END");
		
	    return lt;  
	    
	}
}
