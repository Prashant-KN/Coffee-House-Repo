package com.prashant.coffeeHouse.model.response;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrderResponse {
	private String orderNumber;
	private String customerName;
	private LocalDate orderDate;
	private List<CoffeeOrderResponse> coffeeIdAndQty;
	private double grossTotal;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate localDate) {
		this.orderDate = localDate;
	}

	public List<CoffeeOrderResponse> getCoffeeIdAndQty() {
		return coffeeIdAndQty;
	}

	public void setCoffeeIdAndQty(List<CoffeeOrderResponse> coffeeIdAndQty) {
		this.coffeeIdAndQty = coffeeIdAndQty;
	}

	public double getGrossTotal() {
		return grossTotal;
	}

	public void setGrossTotal(double grossTotal) {
		this.grossTotal = grossTotal;
	}

}
