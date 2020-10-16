package com.prashant.coffeeHouse.model.request;

public class CoffeeOrderRequest {
	private int coffeeId;
	private int quantity;

	public int getCoffeeId() {
		return coffeeId;
	}

	public void setCoffeeId(int coffeeId) {
		this.coffeeId = coffeeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
