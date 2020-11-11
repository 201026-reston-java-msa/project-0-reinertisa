package com.bankingapp.service;

import java.util.List;

public interface StockMarketServices {
	
	
	public List<String> getFaangStockMarket();	
	public boolean addFaangStockMarket();	
	public boolean getStockMarketByCode(String stockCode);

	public void closeResources();
}
