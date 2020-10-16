package com.prashant.coffeeHouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prashant.coffeeHouse.entity.CoffeeEntity;

@Repository
public interface CoffeeRepository extends CrudRepository<CoffeeEntity, Integer>  {
	
}
