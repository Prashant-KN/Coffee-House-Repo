package com.prashant.coffeeHouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prashant.coffeeHouse.entity.CoffeeEntity;
import com.prashant.coffeeHouse.handler.CoffeeHouseAppApiError;
import com.prashant.coffeeHouse.model.request.Coffee;
import com.prashant.coffeeHouse.model.response.CoffeeResponse;
import com.prashant.coffeeHouse.repository.CoffeeRepository;

@Service
public class CoffeeServiceImpl implements CoffeeService {

	private CoffeeRepository coffeeRepository;

	@Autowired
	public CoffeeServiceImpl(CoffeeRepository coffeeRepository) {
		this.coffeeRepository = coffeeRepository;
	}
	
	/**
     * this service will get all available coffee types.
     */
	public List<CoffeeEntity> getAllCoffeeTypes() throws UserServiceException
    {
        List<CoffeeEntity> coffeeTypes = (List<CoffeeEntity>) coffeeRepository.findAll();
         
        if(coffeeTypes.size() > 0) {
            return coffeeTypes;
        } else {
            throw new UserServiceException(CoffeeHouseAppApiError.Empty_Coffee_List.toString());
        }
    }

	/**
     * this service will add the new coffee type.
     */
	public String createOrUpdateCoffee(Coffee entity) throws Exception {
		Optional<CoffeeEntity> coffee = coffeeRepository.findById(entity.getCoffeeId());

		if (coffee.isPresent())// coffee.isPresent())
		{
			CoffeeEntity coffeeFromDb = coffee.get();
			coffeeFromDb.setName(entity.getName());
			coffeeFromDb.setPrice(entity.getPrice());
			coffeeFromDb.setDescription(entity.getDescription());
			coffeeFromDb.setTotoalServingsAvailableForADay(entity.getTotoalServingsAvailableForADay());
			coffeeRepository.save(coffeeFromDb);
			return "success";
		} else {
			CoffeeEntity newCoffee = new CoffeeEntity();
			BeanUtils.copyProperties(entity, newCoffee);
			coffeeRepository.save(newCoffee);
			return "success";
		}
	}

	/**
     * this service will get coffee details through id.
     */
	public CoffeeResponse getCoffeeById(Integer id) throws UserServiceException {
		Optional<CoffeeEntity> coffee = coffeeRepository.findById(id);
		CoffeeResponse coffeeResponse = null;
		if (coffee.isPresent()) {
			coffeeResponse = new CoffeeResponse();
			coffeeResponse.setCoffeeId(id);
			coffeeResponse.setName(coffee.get().getName());
			coffeeResponse.setPrice(coffee.get().getPrice());
			coffeeResponse.setDescription(coffee.get().getDescription());
			coffeeResponse.setTotoalServingsAvailableForADay(coffee.get().getTotoalServingsAvailableForADay());
			return coffeeResponse;
		} else
			throw new UserServiceException(CoffeeHouseAppApiError.Invalid_Coffee_ID.toString());
	}

	/**
     * this service will delete the entry of a coffee through id.
     */
	public void deleteCoffeeById(Integer id) throws UserServiceException {
		Optional<CoffeeEntity> coffee = coffeeRepository.findById(id);
		if (coffee.isPresent()) {
			coffeeRepository.deleteById(id);
		}else
			throw new UserServiceException(CoffeeHouseAppApiError.Invalid_Coffee_ID.toString());
	}

}
