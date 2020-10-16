package com.prashant.coffeeHouse.service;

import com.prashant.coffeeHouse.handler.CoffeeHouseAppApiError;

public class UserServiceException extends Exception {
	private static final long serialVersionUID = -470180507998010368L;
	
	CoffeeHouseAppApiError errorType; 

	public UserServiceException() {
		super();
	}

	public UserServiceException(final String message) {
		super(message);
	}
	
	public UserServiceException(CoffeeHouseAppApiError sie) {
		this.errorType = sie;
	}
	
	public UserServiceException(CoffeeHouseAppApiError sie, Throwable cause) {
		super(cause);
		this.errorType = sie;	
	}

}
