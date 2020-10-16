package com.prashant.coffeeHouse.model.response;

import org.springframework.stereotype.Component;

@Component
public class CoffeeOrderResponse {
	private int coffeeId;
	private int quantity;
	private String coffeeType;
	private double price;
	private double subTotal;
	
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
	public String getCoffeeType() {
		return coffeeType;
	}
	public void setCoffeeType(String coffeeType) {
		this.coffeeType = coffeeType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
}
