package com.bankingapp.service;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankingapp.models.*;
import com.bankingapp.repository.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class StockMarketServicesImpl implements StockMarketServices {

	private StockMarketDao stockMarketDao = new StockMarketDaoImpl();
	private static final Logger log = Logger.getLogger(StockMarketServicesImpl.class);
	
	@Override
	public boolean addFaangStockMarket() {
		
		log.info("addFaangStockMarket START");	
		
		try {

			List<StockMarket> faang = new ArrayList<>();
			
			String[] stockCodes = {"FB", "AAPL", "AMZN", "NFLX", "GOOGL"};
			
			String urlFirstPart = "https://api.twelvedata.com/time_series?symbol=";
			String urlSecondPart = "&interval=30min&outputsize=12&apikey=5751b4c3d088489db1f2e867c18b4783&source=docs";
			
			
			for (String stockCode : stockCodes) {
		
				String url = urlFirstPart + stockCode + urlSecondPart;
				URL jsonUrl = new URL(url);

				URLConnection request = jsonUrl.openConnection();
				request.connect();
				
				
				JsonParser jsonParser = new JsonParser();
				JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
								
				JsonObject rootobj = root.getAsJsonObject();
				JsonObject meta = rootobj.getAsJsonObject("meta");	
				JsonArray values = rootobj.getAsJsonArray("values");

				String symbol = meta.get("symbol").getAsString();
				
				JsonObject value = values.get(0).getAsJsonObject(); // values(0) first object of array
				
				String datetime = value.get("datetime").getAsString();
				double open = value.get("open").getAsDouble();
				double high = value.get("high").getAsDouble();
				double low = value.get("low").getAsDouble();
				double close = value.get("close").getAsDouble();
				int volume = value.get("volume").getAsInt();
				
				StockMarket stockMarket = new StockMarket(symbol, datetime, open, high, low, close, volume);
				
				faang.add(stockMarket);
				
			}

			stockMarketDao.addFaangStockMarket(faang);
			
			return true;
			
		} catch (Exception e) {

			System.out.println("\nJSON data not fetch, please wait and try again");
			log.info("StockMarketDao: JSON data not fetch, please wait and try again", e);
		} 
		
		log.info("addFaangStockMarket END");
		
		return false;
	}
	
	
	@Override
	public List<String> getFaangStockMarket() {
		
		log.info("getFaangStockMarket START");
		
		List<String> formatedFaang = new ArrayList<>();
		try {
			
			List<StockMarket> faang = stockMarketDao.getFaangStockMarket();
			
			String title = "\tOpen($)\t\tHigh($)\t\tLow($)";
			formatedFaang.add(title);			
			
			for (StockMarket stockMarket : faang) {
				
				StringBuilder sb = new StringBuilder();

				sb.append(stockMarket.getSymbol() + ": \t");
				
				String open = String.valueOf(stockMarket.getOpen());
				open = open.length() > 6 ? open.substring(0,6) : open;
				sb.append(open + "\t\t");

				String high = String.valueOf(stockMarket.getHigh());			
				high = high.length() > 6 ? high.substring(0,6) : high;
				sb.append(high + "\t\t");

				String low = String.valueOf(stockMarket.getLow());			
				low = low.length() > 6 ? low.substring(0,6) : low;
				sb.append(low);

				formatedFaang.add(sb.toString());
			}

		} catch (Exception e) {
			System.out.println("\nJSON data not fetch, please wait and try again");
			log.info("StockMarketDao: JSON data not fetch, please wait and try again", e);
		}
		
		log.info("getFaangStockMarket END");
		
		return formatedFaang;
	
	}
	
	@Override
	public boolean getStockMarketByCode(String stockCode) {
		return stockMarketDao.getStockMarketByCode(stockCode);		
	}


	@Override
	public void closeResources() {
		stockMarketDao.closeResources();
		
	}

	
}
