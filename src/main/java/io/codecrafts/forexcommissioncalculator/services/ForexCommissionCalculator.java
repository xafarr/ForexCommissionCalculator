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
	
	private double getTotalAmountWithoutApplyingCommission() {
		double buyPrice = getSpotPrice() + (getPips() / 10000);
		return (getAmount() / buyPrice);
	}
	
	private double getCommissionAmount(double withoutCommValue) {
		return (getCommPerc() / 100) * withoutCommValue;
	}
	
	public double getTotalAmountWhenBuyingFromCustomer() {
		double buyPrice = getSpotPrice() + (getPips() / 10000);
		double withoutCommValue = getAmount() / buyPrice;
		double commission = getCommissionAmount(withoutCommValue);
		double withCommValue = withoutCommValue - commission;
		return Math.round(withCommValue);
	}
	
	public double getTotalAmountWhenSellingToCustomer() {
		double sellPrice = getSpotPrice() - (getPips() / 10000);
		double withoutCommValue = getAmount() / sellPrice;
		double commission = getCommissionAmount(withoutCommValue);
		double withCommValue = withoutCommValue + commission;
		return Math.round(withCommValue);
	}
	
	public double getTotalAmountWhenBuyingFromWholesaler() {
		double wholesalerPrice = getSpotPrice() - (0.01 * getSpotPrice());
		return Math.round(amount / wholesalerPrice);
	}
	
	public double getTotalAmountWhenSellingToWholesaler() {
		double wholesalerPrice = getSpotPrice() + (0.01 * getSpotPrice());
		return Math.round(amount / wholesalerPrice);
	}
}
