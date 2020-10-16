package com.prashant.coffeeHouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.coffeeHouse.model.request.OrderRequestBean;
import com.prashant.coffeeHouse.model.response.OrderResponse;
import com.prashant.coffeeHouse.model.response.ReportForTheDay;
import com.prashant.coffeeHouse.service.OrderService;
import com.prashant.coffeeHouse.service.UserServiceException;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	OrderService orderServiceImpl;
	
	@Autowired
	public OrderController(OrderService orderServiceImpl) {
		this.orderServiceImpl = orderServiceImpl;
	}
	
	@GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrderByOrderId(@PathVariable("orderNumber") String orderNumber) throws UserServiceException {
		OrderResponse response = orderServiceImpl.getOrderByOrderId(orderNumber);
        return new ResponseEntity<OrderResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }
	
	@PostMapping
	public OrderResponse createOrder(@RequestBody OrderRequestBean orderRequestBean) throws UserServiceException {
		OrderResponse orderResponse = orderServiceImpl.createOrder(orderRequestBean);
		return orderResponse;
	}
	
	@GetMapping("/report")
    public ResponseEntity<List<ReportForTheDay>> fetchReportForTheDay() throws UserServiceException {
		List<ReportForTheDay> reportForTheDayList = orderServiceImpl.fetchReportForTheDay();
        return new ResponseEntity<List<ReportForTheDay>>(reportForTheDayList, new HttpHeaders(), HttpStatus.OK);
    }
}
