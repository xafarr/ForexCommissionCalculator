package io.codecrafts.forexcommissioncalculator.services;

/**
 * @author Muhammad Zafar
 */
public class ForexCommissionCalculator {
	private double spotPrice;
	private double pips;
	private double commPerc;
	private double amount;
	
	public ForexCommissionCalculator(double spotPrice, double pips, double commPerc, double amount) {
		this.spotPrice = spotPrice;
		this.pips = pips;
		this.commPerc = commPerc;
		this.amount = amount;
	}
	
	public ForexCommissionCalculator() {
	}
	
	private double getSpotPrice() {
		return spotPrice;
	}
	
	public void setSpotPrice(double spotPrice) {
		this.spotPrice = spotPrice;
	}
	
	private double getPips() {
		return pips;
	}
	
	public void setPips(double pips) {
		this.pips = pips;
	}
	
	private double getCommPerc() {
		return commPerc;
	}
	
	public void setCommPerc(double commPerc) {
		this.commPerc = commPerc;
	}
	
	private double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	private double getCommissionAmount(double withoutCommValue) {
		return (getCommPerc() / 100) * withoutCommValue;
	}
	
	public double getTotalAmountWhenBuyingFromCustomer() {
		try {
			double buyPrice = getSpotPrice() + (getPips() / 10000);
			double withoutCommValue = getAmount() / buyPrice;
			double commission = getCommissionAmount(withoutCommValue);
			return withoutCommValue - commission;
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	public double getTotalAmountWhenSellingToCustomer() {
		try {
			double sellPrice = getSpotPrice() - (getPips() / 10000);
			double withoutCommValue = getAmount() / sellPrice;
			double commission = getCommissionAmount(withoutCommValue);
			return withoutCommValue + commission;
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	public double getTotalAmountWhenBuyingFromWholesaler() {
		try {
			double wholesalerPrice = getSpotPrice() - (0.01 * getSpotPrice());
			return amount / wholesalerPrice;
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	public double getTotalAmountWhenSellingToWholesaler() {
		try {
			double wholesalerPrice = getSpotPrice() + (0.01 * getSpotPrice());
			return amount / wholesalerPrice;
		} catch (Exception e) {
			return 0.0;
		}
	}
}
