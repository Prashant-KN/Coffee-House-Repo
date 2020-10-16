package com.prashant.coffeeHouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prashant.coffeeHouse.entity.OrderEntity;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

	@Query("select o from OrderEntity o where o.om_orderNumber = :orderNumber")
	public List<OrderEntity> findByOrderNumber(@Param("orderNumber") String orderNumber);
	
	@Query("select c.firstName from OrderEntity o left join CustomerEntity c on o.customerId = c.customerId where o.customerId = :customerId")
	public String findCutomerNameById(@Param("customerId") int customerId);
	
	
}
