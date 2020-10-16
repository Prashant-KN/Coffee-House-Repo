package com.prashant.coffeeHouse.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Order_Master")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int om_id;
	
	@Column
	private int om_quantity;
	
	@Column
	private String om_orderNumber;
	
	private int customerId;
	
	private int coffeeId;
	
	@Column
	private String om_createdBy;
	
	@Column
	private LocalDate om_createdOn;

	public int getOm_id() {
		return om_id;
	}

	public void setOm_id(int om_id) {
		this.om_id = om_id;
	}

	public int getOm_quantity() {
		return om_quantity;
	}

	public void setOm_quantity(int om_quantity) {
		this.om_quantity = om_quantity;
	}

	public String getOm_orderNumber() {
		return om_orderNumber;
	}

	public void setOm_orderNumber(String om_orderNumber) {
		this.om_orderNumber = om_orderNumber;
	}

	public String getOm_createdBy() {
		return om_createdBy;
	}

	public void setOm_createdBy(String om_createdBy) {
		this.om_createdBy = om_createdBy;
	}

	public LocalDate getOm_createdOn() {
		return om_createdOn;
	}

	public void setOm_createdOn(LocalDate localDate) {
		this.om_createdOn = localDate;
	}

	public int getCoffeeId() {
		return coffeeId;
	}

	public void setCoffeeId(int coffeeId) {
		this.coffeeId = coffeeId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
