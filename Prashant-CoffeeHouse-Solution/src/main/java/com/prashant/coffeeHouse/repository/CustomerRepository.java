package com.prashant.coffeeHouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prashant.coffeeHouse.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	
	public CustomerEntity findByFirstName(String firstName);
	public CustomerEntity findByPhone(String phone);
  
}