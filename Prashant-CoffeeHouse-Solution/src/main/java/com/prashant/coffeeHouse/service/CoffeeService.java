package com.prashant.coffeeHouse.service;

import com.prashant.coffeeHouse.model.request.Coffee;
import com.prashant.coffeeHouse.model.response.CoffeeResponse;

public interface CoffeeService {
	public String createOrUpdateCoffee(Coffee coffee) throws Exception;
	public CoffeeResponse getCoffeeById(Integer id) throws Exception;
	public void deleteCoffeeById(Integer id) throws Exception;
}
