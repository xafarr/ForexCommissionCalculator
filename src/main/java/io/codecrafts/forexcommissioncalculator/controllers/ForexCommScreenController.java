package io.codecrafts.forexcommissioncalculator.controllers;

import io.codecrafts.forexcommissioncalculator.services.ForexCommissionCalculator;
import io.codecrafts.forexcommissioncalculator.views.FloatingPointTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class ForexCommScreenController implements Initializable {
	private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
	@FXML
	private FloatingPointTextField spotPriceTextField;
	@FXML
	private FloatingPointTextField pipsTextField;
	@FXML
	private FloatingPointTextField commPercTextField;
	@FXML
	private FloatingPointTextField amountTextField;
	@FXML
	private Button buyButton, sellButton;
	@FXML
	private Label audTotalCustomerLabel, audTotalWholesaleLabel, difference;
	
	public ForexCommScreenController() {
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
	}
	
	private EventHandler<ActionEvent> eventHandler = (event) -> {
		Double spotPrice = Double.parseDouble(this.spotPriceTextField.getText());
		Double pips = Double.parseDouble(this.pipsTextField.getText());
		Double commPerc = Double.parseDouble(this.commPercTextField.getText());
		Double amount = Double.parseDouble(this.amountTextField.getText());
		ForexCommissionCalculator forexCommCalculator = new ForexCommissionCalculator(spotPrice, pips, commPerc, amount);
		double totalAmountForCustomer;
		double totalAmountForWholesaler;
		double difference;
		if (spotPrice > 0) {
			if (event.getSource() == this.buyButton) {
				totalAmountForCustomer = forexCommCalculator.getTotalAmountWhenBuyingFromCustomer();
				totalAmountForWholesaler = forexCommCalculator.getTotalAmountWhenSellingToWholesaler();
				difference = totalAmountForWholesaler - totalAmountForCustomer;
			} else {
				totalAmountForCustomer = forexCommCalculator.getTotalAmountWhenSellingToCustomer();
				totalAmountForWholesaler = forexCommCalculator.getTotalAmountWhenBuyingFromWholesaler();
				difference = totalAmountForCustomer - totalAmountForWholesaler;
			}
			this.audTotalCustomerLabel.setText(numberFormat.format(totalAmountForCustomer));
			this.audTotalWholesaleLabel.setText(numberFormat.format(totalAmountForWholesaler));
			this.difference.setText(numberFormat.format(difference));
			
		}
	};
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buyButton.setOnAction(eventHandler);
		this.sellButton.setOnAction(eventHandler);
	}
}
