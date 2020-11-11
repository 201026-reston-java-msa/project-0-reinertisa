package com.bankingapp.service;


import java.util.List;

public interface LocalWeatherServices {

	public List<String> getLocalWeather();
	public boolean addLocalWeatherData();
	
	public void closeResources();
}
