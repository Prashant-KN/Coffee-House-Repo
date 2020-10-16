package com.prashant.coffeeHouse.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.prashant.coffeeHouse.entity.CustomerEntity;
import com.prashant.coffeeHouse.model.request.Customer;
import com.prashant.coffeeHouse.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

	@Mock
	CustomerRepository customerRepository;

	CustomerService customerServiceImpl;

	@Before
	public void setup() {
		customerServiceImpl = new CustomerServiceImpl(customerRepository);
	}

	@Test
	public void saveForExistingCustomerTest() throws UserServiceException {
		CustomerEntity customerEntity = getCustomerEntity(1);
		Mockito.when(customerRepository.findById(customerEntity.getCustomerId())).thenReturn(Optional.of(customerEntity));
		Mockito.when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
		String response = customerServiceImpl.createOrUpdateCustomer(getCustomer(1));
		assertEquals("success", response);
	}

	@Test
	public void saveForNonExistingCustomerTest() throws UserServiceException {
		CustomerEntity customerEntity = getCustomerEntity(1);
		Mockito.when(customerRepository.findById(customerEntity.getCustomerId())).thenReturn(Optional.of(new CustomerEntity()));
		Mockito.when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
		String response = customerServiceImpl.createOrUpdateCustomer(getCustomer(1));
		assertEquals("success", response);
	}


	@Test(expected = Exception.class)
	public void saveCustomerThrowsExceptionTest() throws UserServiceException {
		CustomerEntity customerEntity = getCustomerEntity(1);
		Mockito.when(customerRepository.findById(customerEntity.getCustomerId())).thenThrow(new RuntimeException());
		customerServiceImpl.createOrUpdateCustomer(getCustomer(1));
	}

	@Test
	public void fetchCustomerByIdForExisting() throws UserServiceException {
		CustomerEntity customerEntity = getCustomerEntity(1);
		Mockito.when(customerRepository.findById(customerEntity.getCustomerId())).thenReturn(Optional.of(customerEntity));
		Customer response = customerServiceImpl.getCustomerById(1);
		assertEquals(1, response.getCustomerId());
	}

	@Test(expected = UserServiceException.class)
	public void fetchCustomerByIdForNonExisting() throws UserServiceException {
		CustomerEntity customerEntity = getCustomerEntity(1);
	    customerServiceImpl.getCustomerById(1);
	}

	@Test(expected = UserServiceException.class)
	public void deleteCustomerByIdForNonExisting() throws UserServiceException {
		CustomerEntity customerEntity = getCustomerEntity(1);
		customerServiceImpl.deleteCustomerById(1);
	}

	private CustomerEntity getCustomerEntity(int i) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerId(i);
		customerEntity.setAddressLine1("jp nagar");
		customerEntity.setAddressLine2("6th phase");
		customerEntity.setEmail("test@mail.com");
		customerEntity.setFirstName("prashant");
		customerEntity.setLastName("k");
		customerEntity.setPhone("9999999999");
		return customerEntity;
	}

	private Customer getCustomer(int i) {
		Customer customer = new Customer();
		customer.setCustomerId(i);
		customer.setAddressLine1("jp nagar");
		customer.setAddressLine2("6th phase");
		customer.setEmail("test@mail.com");
		customer.setFirstName("prashant");
		customer.setLastName("k");
		customer.setPhone("9999999999");
		return customer;
	}

}