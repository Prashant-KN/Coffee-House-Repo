package com.prashant.coffeeHouse.handler;

public enum CoffeeHouseAppApiError {

    /**
     * customerId is invalid
     */
    Invalid_Customer_ID("CH001", "invalid customerId"),
	
	/**
     * empty coffee list
     */
	Empty_Coffee_List("CH002", "coffee list is empty"),
	
	/**
     * coffee id is not present
     */
	Invalid_Coffee_ID("CH003", "invalid coffee id"),
	
	/**
     * empty customer list
     */
	Empty_Custimer_List("CH004", "customer list is empty"),
	
	/**
     * invalid order id
     */
	Invalid_Order_ID("CH003", "invalid order id");
	
    
	CoffeeHouseAppApiError(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * code
     */
    private final String code;
    /**
     * description
     */
    private final String description;

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return readable message of the enum
     */
    @Override
    public String toString() {
        return this.code + ": " + this.description;
    }
}
