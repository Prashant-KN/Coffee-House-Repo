package com.prashant.coffeeHouse.model.response;

public class ReportForTheDay {
	private String coffieVeriety;
	private int totalServingsAvailableForTheDay;
	private int totalServingsSoldForTheDay;
	public String getCoffieVeriety() {
		return coffieVeriety;
	}
	public void setCoffieVeriety(String coffieVeriety) {
		this.coffieVeriety = coffieVeriety;
	}
	public int getTotalServingsAvailableForTheDay() {
		return totalServingsAvailableForTheDay;
	}
	public void setTotalServingsAvailableForTheDay(int totalServingsAvailableForTheDay) {
		this.totalServingsAvailableForTheDay = totalServingsAvailableForTheDay;
	}
	public int getTotalServingsSoldForTheDay() {
		return totalServingsSoldForTheDay;
	}
	public void setTotalServingsSoldForTheDay(int totalServingsSoldForTheDay) {
		this.totalServingsSoldForTheDay = totalServingsSoldForTheDay;
	}
}
