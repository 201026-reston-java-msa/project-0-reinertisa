package com.bankingapp.models;

public class StockMarket {
	
	private int sid;
	private String symbol;
	private String datetime;
	private double open;
	private double high;
	private double low;
	private double close;
	private int volume;
	
	public StockMarket() {
		
	}

	public StockMarket(String symbol, String datetime, double open, double high, double low, double close, int volume) {
		super();
		this.symbol = symbol;
		this.datetime = datetime;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "StockMarket [sid=" + sid + ", symbol=" + symbol + ", datetime=" + datetime + ", open=" + open
				+ ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume + "]";
	}

	
}
