package com.prashant.coffeeHouse.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.prashant.coffeeHouse.entity.CoffeeEntity;
import com.prashant.coffeeHouse.model.request.Coffee;
import com.prashant.coffeeHouse.model.response.CoffeeResponse;
import com.prashant.coffeeHouse.repository.CoffeeRepository;

@RunWith(SpringRunner.class)
public class CoffeeServiceImplTest {
	
	@Mock
	CoffeeRepository coffeeRepository;

	CoffeeService coffeeServiceImpl;
	
	@Before
	public void setup() {
		coffeeServiceImpl = new CoffeeServiceImpl(coffeeRepository);
	}
	
	@Test
	public void coffeSaveTestForExistingCoffee() throws Exception {

		Coffee coffee = getCoffee();
		CoffeeEntity coffeeEntity = getCoffeEntity();

		Mockito.when(coffeeRepository.findById(coffee.getCoffeeId())).thenReturn(Optional.of(coffeeEntity));
		Mockito.when(coffeeRepository.save(coffeeEntity)).thenReturn(coffeeEntity);

		String response = coffeeServiceImpl.createOrUpdateCoffee(coffee);
		assertEquals("success", response);
	}


	@Test
	public void coffeSaveTestForNonExistingCoffee() throws Exception {

		CoffeeEntity coffeeEntity = getCoffeEntity();
		Coffee coffee = getCoffee();

		Mockito.when(coffeeRepository.findById(coffee.getCoffeeId())).thenReturn(Optional.of(new CoffeeEntity()));
		Mockito.when(coffeeRepository.save(coffeeEntity)).thenReturn(coffeeEntity);

	   String response = coffeeServiceImpl.createOrUpdateCoffee(coffee);

	   assertEquals("success", response);
	}

	@Test(expected = Exception.class)
	public void coffeSaveThrowsException() throws Exception {
		Coffee coffee = getCoffee();
		Mockito.when(coffeeRepository.findById(coffee.getCoffeeId())).thenThrow(new RuntimeException());
		coffeeServiceImpl.createOrUpdateCoffee(coffee);
	}
	
	@Test
	public void coffeeGetForExisting() throws Exception {
		CoffeeEntity coffeEntity = getCoffeEntity();
		
		Mockito.when(coffeeRepository.findById(coffeEntity.getCoffeeId())).thenReturn(Optional.of(coffeEntity));
		CoffeeResponse response = coffeeServiceImpl.getCoffeeById(1);
		assertEquals(1, response.getCoffeeId());
	}

	@Test(expected = UserServiceException.class)
	public void coffeeGetForNonExistingCoffee() throws Exception {
		Coffee coffee = getCoffee();
		coffeeServiceImpl.getCoffeeById(1);
	}

	@Test(expected = Exception.class)
	public void coffeeGetThrowsExcption() throws Exception {
		CoffeeEntity coffeEntity = getCoffeEntity();
		Mockito.when(coffeeRepository.findById(coffeEntity.getCoffeeId())).thenThrow(new RuntimeException());
		coffeeServiceImpl.getCoffeeById(1);
	}

	@Test
	public void coffeeDeleteForExisting() throws Exception {
		CoffeeEntity coffeEntity = getCoffeEntity();

		Mockito.when(coffeeRepository.findById(coffeEntity.getCoffeeId())).thenReturn(Optional.of(coffeEntity));
		CoffeeResponse response = coffeeServiceImpl.getCoffeeById(1);
		assertEquals(1, response.getCoffeeId());
	}

	@Test(expected = UserServiceException.class)
	public void coffeeDeleteForNonExistingCoffee() throws Exception {
		Coffee coffee = getCoffee();
		coffeeServiceImpl.getCoffeeById(1);
	}

	private Coffee getCoffee() {
		Coffee coffee = new Coffee();
		coffee.setCoffeeId(1);
		coffee.setName("capachino");
		coffee.setDescription("good");
		coffee.setPrice(100.0);
		coffee.setTotoalServingsAvailableForADay(50);
		return coffee;
	}

	private CoffeeEntity getCoffeEntity() {
		CoffeeEntity coffeeEntity = new CoffeeEntity();
		coffeeEntity.setCoffeeId(1);
		coffeeEntity.setName("capachino");
		coffeeEntity.setDescription("good");
		coffeeEntity.setPrice(100.0);
		coffeeEntity.setTotoalServingsAvailableForADay(50);
		return coffeeEntity;
	}


}