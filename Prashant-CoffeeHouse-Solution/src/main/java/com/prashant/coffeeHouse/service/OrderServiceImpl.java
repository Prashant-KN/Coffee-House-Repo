package com.prashant.coffeeHouse.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prashant.coffeeHouse.entity.CoffeeEntity;
import com.prashant.coffeeHouse.entity.OrderEntity;
import com.prashant.coffeeHouse.handler.CoffeeHouseAppApiError;
import com.prashant.coffeeHouse.model.request.CoffeeOrderRequest;
import com.prashant.coffeeHouse.model.request.OrderRequestBean;
import com.prashant.coffeeHouse.model.response.CoffeeOrderResponse;
import com.prashant.coffeeHouse.model.response.OrderResponse;
import com.prashant.coffeeHouse.model.response.ReportForTheDay;
import com.prashant.coffeeHouse.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	/**
     * this service creates the order for multiple varieties of coffee(or only one item) and quantities that one has chosen for
     * order. and saves the order.
     */
	@Override
	public OrderResponse createOrder(OrderRequestBean orderRequestBean) throws UserServiceException {
		OrderResponse orderResponse = new OrderResponse();
		String firstname =	 orderRepository.findCutomerNameById(orderRequestBean.getCustomerId());
		double grossTotal = 0.0;
		List<CoffeeOrderRequest> getListOfCoffeeIdAndQty = orderRequestBean.getCoffeeIdAndQty();
		List<CoffeeOrderResponse> coffeeOrderResponseList = new ArrayList<CoffeeOrderResponse>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		//from orderRequestBean fetch list of coffee id's and corresponding quantites set coffeeOrderResponse object,
		for (CoffeeOrderRequest coffeeOrderRequest : getListOfCoffeeIdAndQty) {
			CoffeeOrderResponse coffeeOrderResponse = new CoffeeOrderResponse();
			CriteriaQuery<Tuple> cq = builder.createTupleQuery();
			Root<CoffeeEntity> root = cq.from(CoffeeEntity.class);
			cq.select(builder.tuple(root.get("name"), root.get("price"), root.get("coffeeId")));
			cq.where(builder.equal(root.get("coffeeId"), coffeeOrderRequest.getCoffeeId()));

			TypedQuery<Tuple> qry = entityManager.createQuery(cq);

			Tuple t = qry.getSingleResult();
				coffeeOrderResponse.setCoffeeId(Integer.parseInt(t.get(2)+""));
				coffeeOrderResponse.setCoffeeType(t.get(0) + "");
				coffeeOrderResponse.setPrice(Double.valueOf(t.get(1) + ""));
				coffeeOrderResponse.setQuantity(coffeeOrderRequest.getQuantity());
				coffeeOrderResponse.setSubTotal(coffeeOrderResponse.getPrice() * coffeeOrderResponse.getQuantity());
				grossTotal += coffeeOrderResponse.getSubTotal();
				coffeeOrderResponseList.add(coffeeOrderResponse);
			
		}
		//set order response object 
		orderResponse.setGrossTotal(grossTotal);
		orderResponse.setOrderDate(LocalDate.now());
		orderResponse.setOrderNumber(orderRequestBean.getCustomerId()+"ch");
		orderResponse.setCoffeeIdAndQty(coffeeOrderResponseList);
		orderResponse.setCustomerName(firstname);
		//save the multiple orders at a time if multiple orders exists.
		saveOrder(orderResponse, orderRequestBean.getCustomerId());
		return orderResponse;
	}

	
	/**
     * this service takes order number and returns
     *  total number of coffee types(line-items) that is been purchased and respective quantities
     * sub-total and gross total.
     * comes under the same order number.
     */
	@Override 
	public OrderResponse getOrderByOrderId(String id) throws UserServiceException {
		OrderResponse orderResponse = new OrderResponse();
		List<OrderEntity> orderList = orderRepository.findByOrderNumber(id);
		List<CoffeeOrderResponse> coffeeOrderResponseList = new ArrayList<CoffeeOrderResponse>();
		if (orderList.size() != 0) {
			List<Tuple> tupleList = entityManager.createQuery(
					"select  cm.firstName ,cf.name, cf.price, om.om_quantity, om.om_orderNumber, om.om_createdOn, om.coffeeId "
							+ "from OrderEntity om " + "left join CoffeeEntity cf on om.coffeeId = cf.coffeeId "
							+ "left join CustomerEntity cm on cm.customerId = om.customerId "
							+ "where om.om_orderNumber = :orderNumber ",
					Tuple.class).setParameter("orderNumber", id).getResultList();

			double grossTotal = 0.0;
			double subtotal = 0.0;

			for (Tuple tuple : tupleList) {
				CoffeeOrderResponse coffeeOrderResponse = new CoffeeOrderResponse();
				orderResponse.setCustomerName(tuple.get(0) + "");
				coffeeOrderResponse.setCoffeeType(tuple.get(1) + "");
				coffeeOrderResponse.setPrice(Double.valueOf(tuple.get(2) + ""));
				coffeeOrderResponse.setQuantity(Integer.parseInt(tuple.get(3) + ""));
				subtotal = Double.parseDouble(tuple.get(2) + "") * Double.parseDouble(tuple.get(3) + "");
				coffeeOrderResponse.setSubTotal(subtotal);
				coffeeOrderResponse.setCoffeeId(Integer.parseInt(tuple.get(6) + ""));
				coffeeOrderResponseList.add(coffeeOrderResponse);
				orderResponse.setOrderNumber((tuple.get(4) + ""));
				orderResponse.setOrderDate(LocalDate.parse(tuple.get(5) + ""));
				grossTotal += subtotal;
			}
			orderResponse.setGrossTotal(grossTotal);
			orderResponse.setCoffeeIdAndQty(coffeeOrderResponseList);
			return orderResponse;
		} else {
			throw new UserServiceException(CoffeeHouseAppApiError.Invalid_Order_ID.toString());
		}
	}

	/**
     * saves the order entities 
     */
	public void saveOrder(OrderResponse orderResponse, int customerId) {
		List<OrderEntity> orderEntityList = new ArrayList<OrderEntity>();
		List<CoffeeOrderResponse> coffeeList = orderResponse.getCoffeeIdAndQty();
		for (CoffeeOrderResponse coffeeOrderResponse : coffeeList) {
			OrderEntity o = new OrderEntity();
			o.setCoffeeId(coffeeOrderResponse.getCoffeeId());
			o.setCustomerId(customerId);
			o.setOm_createdBy(orderResponse.getCustomerName());
			o.setOm_createdOn(LocalDate.now());
			o.setOm_orderNumber(customerId + "ch");
			o.setOm_quantity(coffeeOrderResponse.getQuantity());
			orderEntityList.add(o);
		}
		orderRepository.saveAll(orderEntityList);
	}

	/**
     * this report has list of coffee types and it has
     * total servings available for the day, 
     * and total servings sold for the day for each coffee type.
     */
	@Override
	public List<ReportForTheDay> fetchReportForTheDay() {
		List<Tuple> tupleList = entityManager.createQuery(
				"select cf.name, cf.totoalServingsAvailableForADay, sum(om.om_quantity) as soldQty "
						+ "from OrderEntity om " + "left join CoffeeEntity cf on om.coffeeId = cf.coffeeId "
						+ "left join CustomerEntity ce on ce.customerId = om.customerId "
						+ "where om.om_createdOn = getDate() group by cf.coffeeId",
				Tuple.class).getResultList();
		List<ReportForTheDay> reportForTheDayList = new ArrayList<ReportForTheDay>();
		for (Tuple tuple : tupleList) {
			ReportForTheDay reportForTheDay = new ReportForTheDay();
			reportForTheDay.setCoffieVeriety(tuple.get(0)+"");
			reportForTheDay.setTotalServingsAvailableForTheDay(Integer.parseInt(tuple.get(1)+""));
			reportForTheDay.setTotalServingsSoldForTheDay(Integer.parseInt(tuple.get(2)+""));
			reportForTheDayList.add(reportForTheDay);
		}
		return reportForTheDayList;
	}
}
