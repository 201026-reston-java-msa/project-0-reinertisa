package com.bankingapp.service;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankingapp.models.*;
import com.bankingapp.repository.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class LocalWeatherServicesImpl implements LocalWeatherServices {

	private static final Logger log = Logger.getLogger(LocalWeatherServicesImpl.class);

	LocalWeatherDao localWeatherDao = new LocalWeatherDaoImpl();

	@Override
	public List<String> getLocalWeather() {
		
		log.info("getLocalWeather START");
		
		List<String> formatedLocalWeather = new ArrayList<>();
		try {
			
			LocalWeather localWeather = localWeatherDao.getLocalWeather();	
			
			String city = localWeather.getCityName() + ", " + localWeather.getCountryName();
			String weather = "Weather: " + localWeather.getWeather();
			String temp = "Temperature: " + localWeather.getTemp() + " F";
			String feels_like = "Feels Like: " + localWeather.getFeels_like() + " F";
					
			formatedLocalWeather = Arrays.asList(city, weather, temp, feels_like);
			
		} catch (Exception e) {
			System.out.println("\nConnection Failed");
			log.info("Connection Failed", e);
		}

		log.info("getLocalWeather END");

		return formatedLocalWeather;
	}

	@Override
	public boolean addLocalWeatherData() {

		log.info("addLocalWeatherData START");

		try {

			String url = "http://api.openweathermap.org/data/2.5/weather?q=Sunnyvale&appid=a874d574b44f4f70bdbb9b661600937d&units=imperial";
			URL jsonUrl = new URL(url);

			URLConnection request = jsonUrl.openConnection();
			request.connect();

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject rootobj = root.getAsJsonObject();

			JsonArray weatherJsonArr = rootobj.getAsJsonArray("weather");
			JsonObject mainJsonObj = rootobj.getAsJsonObject("main");
			JsonObject sysJsonObj = rootobj.getAsJsonObject("sys");

			String weather = weatherJsonArr.get(0).getAsJsonObject().get("main").getAsString();
			double temp = mainJsonObj.get("temp").getAsDouble();
			double feels_like = mainJsonObj.get("feels_like").getAsDouble();
			String cityName = rootobj.get("name").getAsString();
			String countryName = sysJsonObj.get("country").getAsString();

			LocalWeather localWeather = new LocalWeather(cityName, countryName, temp, feels_like, weather);

			localWeatherDao.addLocalWeatherData(localWeather);
			
			return true;

		} catch (Exception e) {
			System.out.println("\nJSON data not fetch, please wait and try again");
			log.info("JSON data not fetch, please wait and try again", e);
		}

		log.info("addLocalWeatherData END");
		
		return false;

	}

	@Override
	public void closeResources() {
		localWeatherDao.closeResources();
		
	}
}
