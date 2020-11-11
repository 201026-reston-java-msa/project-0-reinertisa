package com.bankingapp.repository;

import com.bankingapp.models.LocalWeather;

public interface LocalWeatherDao {
	
	public LocalWeather getLocalWeather();
	public void addLocalWeatherData(LocalWeather localWeather);	
	
	public void closeResources();
}
