package com.prashant.coffeeHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.coffeeHouse.model.request.Coffee;
import com.prashant.coffeeHouse.model.response.CoffeeResponse;
import com.prashant.coffeeHouse.service.CoffeeService;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
	
	private CoffeeService coffeeServiceImpl;
	
	@Autowired
	public CoffeeController(CoffeeService coffeeServiceImpl) {
		this.coffeeServiceImpl = coffeeServiceImpl;
	}
	
	@PostMapping
    public ResponseEntity<String> createOrUpdateCoffee(@RequestBody Coffee coffee) throws Exception {
		String status = coffeeServiceImpl.createOrUpdateCoffee(coffee);
        return new ResponseEntity<String>(status, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CoffeeResponse> getCoffeById(@PathVariable("id") Integer id) throws Exception {
		CoffeeResponse coffeeResponse = coffeeServiceImpl.getCoffeeById(id);
        return new ResponseEntity<CoffeeResponse>(coffeeResponse, new HttpHeaders(), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public HttpStatus deleteCoffeeById(@PathVariable("id") Integer id) throws Exception {
		coffeeServiceImpl.deleteCoffeeById(id);
        return HttpStatus.OK;
    }
}
