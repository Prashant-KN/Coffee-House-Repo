package com.prashant.coffeeHouse.service;

import java.util.List;

import com.prashant.coffeeHouse.entity.CustomerEntity;
import com.prashant.coffeeHouse.model.request.Customer;

public interface CustomerService {
	public List<CustomerEntity> getAllCustomers() throws UserServiceException;
	public Customer getCustomerById(Integer id) throws UserServiceException;
	public Customer search(String name, String phone) throws UserServiceException;
	public String createOrUpdateCustomer(Customer entity) throws UserServiceException;
	public void deleteCustomerById(Integer id) throws UserServiceException;
}
