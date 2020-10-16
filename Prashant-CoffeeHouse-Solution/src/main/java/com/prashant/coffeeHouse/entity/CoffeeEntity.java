package com.prashant.coffeeHouse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="COFFEE")
public class CoffeeEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int coffeeId;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private double price;
	
	@OneToMany(cascade = CascadeType.ALL) 
	@JoinColumn(name = "coffeeId", updatable = false, insertable = false)
	protected List<OrderEntity> orderList = new ArrayList<OrderEntity>();
	
	@Column
	@JsonIgnoreProperties
	private int totoalServingsAvailableForADay;
	
	public List<OrderEntity> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderEntity> orderList) {
		this.orderList = orderList;
	}

	public int getCoffeeId() {
		return coffeeId;
	}

	public void setCoffeeId(int coffeeId) {
		this.coffeeId = coffeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTotoalServingsAvailableForADay() {
		return totoalServingsAvailableForADay;
	}

	public void setTotoalServingsAvailableForADay(int totoalServingsAvailableForADay) {
		this.totoalServingsAvailableForADay = totoalServingsAvailableForADay;
	}

}
