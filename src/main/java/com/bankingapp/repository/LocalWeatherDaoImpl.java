package com.bankingapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bankingapp.models.*;
import com.bankingapp.util.DaoConnection;


public class LocalWeatherDaoImpl implements LocalWeatherDao{

	Connection conn = null;
	PreparedStatement pst = null;
	private static final Logger log = Logger.getLogger(LocalWeatherDaoImpl.class);
	
	@Override
	public LocalWeather getLocalWeather() {
		
		log.info("getLocalWeatherData START");
		
		try {
			
			LocalWeather localWeather = new LocalWeather();
			
			String sql = "SELECT * FROM localweather;";
				
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();			
		
			while(rs.next()) {
				
				localWeather.setWid(rs.getInt("wid"));
				localWeather.setCityName(rs.getString("cityname"));
				localWeather.setCountryName(rs.getString("countryname"));
				localWeather.setTemp(rs.getDouble("temp"));
				localWeather.setFeels_like(rs.getDouble("feels_like"));
				localWeather.setWeather(rs.getString("weather"));
			}
			
			return localWeather;
			
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("LocalWeatherDao: Connection failed", e);
		} 
		
		log.info("getLocalWeatherData END");
		
		return null;		
		
	}

	@Override
	public void addLocalWeatherData(LocalWeather localWeather) {
		
		log.info("addLocalWeatherData START");
	
		
		try {
			
			String sql1 = "TRUNCATE localweather;";
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql1);			
			pst.executeUpdate();
			
			
			String sql2 = "INSERT INTO localweather(cityname, countryname, temp, feels_like, weather) VALUES(?,?,?,?,?)";
					
			conn = DaoConnection.getConnection();
			pst = conn.prepareStatement(sql2);
			
			pst.setString(1, localWeather.getCityName());
			pst.setString(2, localWeather.getCountryName());
			pst.setDouble(3, localWeather.getTemp());
			pst.setDouble(4, localWeather.getFeels_like());
			pst.setString(5, localWeather.getWeather());

			
			pst.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("\nConnection failed");
			log.info("LocalWeatherDao : Connection failed", e);
		} 
		
		log.info("addLocalWeatherData END");
		
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
