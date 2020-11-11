package com.bankingapp.repository;

import java.util.List;

import com.bankingapp.models.StockMarket;

public interface StockMarketDao {
	
	public void addFaangStockMarket(List<StockMarket> faang);
	public List<StockMarket> getFaangStockMarket();
	public boolean getStockMarketByCode(String stockCode);
	
	public void closeResources();
}
