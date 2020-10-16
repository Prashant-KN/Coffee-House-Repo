package com.prashant.coffeeHouse.service;

import java.util.List;

import com.prashant.coffeeHouse.model.request.OrderRequestBean;
import com.prashant.coffeeHouse.model.response.OrderResponse;
import com.prashant.coffeeHouse.model.response.ReportForTheDay;

public interface OrderService {
	public OrderResponse createOrder(OrderRequestBean orderRequestBean) throws UserServiceException;
	public OrderResponse getOrderByOrderId(String orderNumber) throws UserServiceException;
	public List<ReportForTheDay> fetchReportForTheDay();
}
