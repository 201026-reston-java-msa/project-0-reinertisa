package com.bankingapp.models;

public class LocalWeather {
	
	private int wid;
	private String cityName;
	private String countryName;
	private double temp;
	private double feels_like;
	private String weather;
	
	public LocalWeather() {
		
	}

	public LocalWeather(String cityName, String countryName, double temp, double feels_like, String weather) {
		super();
		this.cityName = cityName;
		this.countryName = countryName;
		this.temp = temp;
		this.feels_like = feels_like;
		this.weather = weather;
	}


	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "LocalWeather [wid=" + wid + ", cityName=" + cityName + ", countryName=" + countryName + ", temp=" + temp
				+ ", feels_like=" + feels_like + ", weather=" + weather + "]";
	}
	
	
	

}
