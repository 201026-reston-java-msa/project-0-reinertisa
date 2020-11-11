package com.bankingapp.repository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankingapp.models.StockMarket;
import com.bankingapp.util.DaoConnection;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class StockMarketDaoImpl implements StockMarketDao{

	Connection conn = null;
	PreparedStatement pst = null;
	private static final Logger log = Logger.getLogger(StockMarketDaoImpl.class);
	
	@Override
	public void addFaangStockMarket(List<StockMarket> faang) {
		
		log.info("addFaangStockMarket START");	
		
		try {
			
			String sql1 = "TRUNCATE stockmarket;";
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql1);			
			pst.executeUpdate();
			

			for(StockMarket stockMarket : faang) {
				String sql2 = "INSERT INTO stockmarket(symbol, datetime, open, high, low, close, volume) VALUES(?,?,?,?,?,?,?);";
				conn = DaoConnection.getConnection();
				pst = conn.prepareStatement(sql2);
				
				pst.setString(1, stockMarket.getSymbol());
				pst.setString(2, stockMarket.getDatetime());
				pst.setDouble(3, stockMarket.getOpen());
				pst.setDouble(4, stockMarket.getHigh());
				pst.setDouble(5, stockMarket.getLow());
				pst.setDouble(6, stockMarket.getClose());
				pst.setInt(7, stockMarket.getVolume());
				
				pst.executeUpdate();
			}
		
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("StockMarketDao: Connection failed", e);
		} 
		
		log.info("addFaangStockMarket END");
	}
	
	@Override
	public List<StockMarket> getFaangStockMarket() {

		log.info("getFaangStockMarket START");
		
		List<StockMarket> faang = new ArrayList<>();
		try {
			
			String sql = "SELECT * FROM stockmarket;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();			
		
			while(rs.next()) {
				
				StockMarket stockMarket = new StockMarket();
				
				stockMarket.setSid(rs.getInt("sid"));
				stockMarket.setSymbol(rs.getString("symbol"));
				stockMarket.setDatetime(rs.getString("datetime"));
				stockMarket.setOpen(rs.getDouble("open"));
				stockMarket.setHigh(rs.getDouble("high"));
				stockMarket.setLow(rs.getDouble("low"));
				stockMarket.setClose(rs.getDouble("close"));
				stockMarket.setVolume(rs.getInt("volume"));
				
				faang.add(stockMarket);
			}
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("StockMarketDao: Connection failed", e);
		} 
		
		log.info("getFaangStockMarket END");
		
		return faang;	

	}
	
	
	@Override
	public boolean getStockMarketByCode(String stockCode) {
		
		log.info("getStockMarketByCode START");
		
		try {
					
			String urlFirstPart = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=";
			String apiKey = "&apikey=KAW41TPNKBMQLL64";
			
			String url = urlFirstPart + stockCode + apiKey;
			URL jsonUrl = new URL(url);

			URLConnection request = jsonUrl.openConnection();
			request.connect();

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject rootobj = root.getAsJsonObject();

			Object obj1 = rootobj.getAsJsonObject("Meta Data");
			Object obj2 = rootobj.getAsJsonObject("Time Series (Daily)");

			JsonObject metaData = (JsonObject) obj1;
			JsonObject timeSeries = (JsonObject) obj2;

			String symbol = metaData.get("2. Symbol").getAsString();

			System.out.println("Company Stock Market Code: " + symbol);
			timeSeries.keySet().forEach(keyStr -> {
				Object keyValue = timeSeries.get(keyStr);
				System.out.println(keyStr + " " + keyValue);

			});
			
			return true;

		} catch (Exception e) {
			log.info("StockMarketDao: JSON data not fetch, please wait and try again", e);
		}
		log.info("getStockMarketByCode END");
		
		return false;
	}
	
	@Override
	public void closeResources() {
		
		log.info("closeResources START");
		try {
			
			if (pst != null)
				pst.close();
		
		} catch (SQLException e) {
			System.out.println("\nCould not close prepared statement!");
			log.info("Could not close prepared statement!", e);
		}
		
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("\nCould not close connection!");
			log.info("Could not close connection!", e);
		}
		
		log.info("closeResources END");
	}
	
}
