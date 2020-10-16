package com.prashant.coffeeHouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.coffeeHouse.model.request.Customer;
import com.prashant.coffeeHouse.service.CustomerService;
import com.prashant.coffeeHouse.service.UserServiceException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerServiceImpl;
	
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) throws UserServiceException {
    	Customer customer = customerServiceImpl.getCustomerById(id);
        return new ResponseEntity<Customer>(customer, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Customer> search(@RequestParam(required = false) String name, @RequestParam(required = false) String phone) throws UserServiceException {
    	Customer customer = customerServiceImpl.search(name, phone);
        return new ResponseEntity<Customer>(customer, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<String> createOrUpdateCustomer(@RequestBody Customer customer) throws UserServiceException {
    	String status = customerServiceImpl.createOrUpdateCustomer(customer);
        return new ResponseEntity<String>(status, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteCustomerById(@PathVariable("id") Integer id) throws Exception {
    	customerServiceImpl.deleteCustomerById(id);
        return HttpStatus.OK;
    }

}
