package com.prashant.coffeeHouse.model.request;

import java.util.List;

public class OrderRequestBean {
	private int customerId;
	private List<CoffeeOrderRequest> coffeeIdAndQty;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<CoffeeOrderRequest> getCoffeeIdAndQty() {
		return coffeeIdAndQty;
	}

	public void setCoffeeIdAndQty(List<CoffeeOrderRequest> coffeeIdAndQty) {
		this.coffeeIdAndQty = coffeeIdAndQty;
	}

}
