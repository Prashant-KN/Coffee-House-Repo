package com.prashant.coffeeHouse.service;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.beans.BeanUtils.copyProperties;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prashant.coffeeHouse.entity.CustomerEntity;
import com.prashant.coffeeHouse.handler.CoffeeHouseAppApiError;
import com.prashant.coffeeHouse.model.request.Customer;
import com.prashant.coffeeHouse.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * this service will retrieves all the customers.
	 */
	public List<CustomerEntity> getAllCustomers() throws UserServiceException {
		List<CustomerEntity> employeeList = customerRepository.findAll();
		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			throw new UserServiceException(CoffeeHouseAppApiError.Empty_Custimer_List.toString());
		}
	}

	/**
	 * this service will retrieve a customer by Id.
	 */
	public Customer getCustomerById(Integer id) throws UserServiceException {
		Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
		Customer customer = new Customer();

		if (customerEntity.isPresent()) {
			customer.setFirstName(customerEntity.get().getFirstName());
			customer.setLastName(customerEntity.get().getLastName());
			customer.setEmail(customerEntity.get().getEmail());
			customer.setPhone(customerEntity.get().getPhone());
			customer.setAddressLine1(customerEntity.get().getAddressLine1());
			customer.setAddressLine2(customerEntity.get().getAddressLine2());
			customer.setCustomerId(customerEntity.get().getCustomerId());
			return customer;
		} else
			throw new UserServiceException(CoffeeHouseAppApiError.Invalid_Customer_ID.toString());
	}


	/**
	 * this service will save the customer.
	 */
	public String createOrUpdateCustomer(Customer customer) {
		Optional<CustomerEntity> existingCustomer = customerRepository.findById(customer.getCustomerId());
		if (existingCustomer.isPresent()) {
			CustomerEntity customerFromDb =  existingCustomer.get();
			customerFromDb.setFirstName(customer.getFirstName());
			customerFromDb.setLastName(customer.getLastName());
			customerFromDb.setEmail(customer.getEmail());
			customerFromDb.setPhone(customer.getPhone());
			customerFromDb.setAddressLine1(customer.getAddressLine1());
			customerFromDb.setAddressLine2(customer.getAddressLine2());
		    customerRepository.save(customerFromDb);
		    
			return "success";
		} else {
			CustomerEntity newCustomer = new CustomerEntity();
			BeanUtils.copyProperties(customer, newCustomer);
			customerRepository.save(newCustomer);
			return "success";
		}
	}

	/**
	 * this service will delete the entry of a customer.
	 */
	public void deleteCustomerById(Integer id) throws UserServiceException {
		Optional<CustomerEntity> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			customerRepository.deleteById(id);
		} else
			throw new UserServiceException(CoffeeHouseAppApiError.Invalid_Customer_ID.toString());
	}

	@Override
	public Customer search(String name, String phone) throws UserServiceException {
		Customer customer = new Customer();
		if (isNotEmpty(name)) {
			copyProperties(customerRepository.findByFirstName(name), customer);
			return customer;
		} else if (isNotEmpty(phone)) {
			copyProperties(customerRepository.findByPhone(phone), customer);
			return customer;
		} else
			throw new UserServiceException("atleast one value is mandatory");
	}

}
