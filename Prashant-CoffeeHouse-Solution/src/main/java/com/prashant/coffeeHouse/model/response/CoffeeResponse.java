package com.prashant.coffeeHouse.model.response;

public class CoffeeResponse {
	private int coffeeId;
	private String name;
	private String description;
	private double price;
	private int totoalServingsAvailableForADay;

	public int getCoffeeId() {
		return coffeeId;
	}

	public void setCoffeeId(int coffeeId) {
		this.coffeeId = coffeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTotoalServingsAvailableForADay() {
		return totoalServingsAvailableForADay;
	}

	public void setTotoalServingsAvailableForADay(int totoalServingsAvailableForADay) {
		this.totoalServingsAvailableForADay = totoalServingsAvailableForADay;
	}

}
