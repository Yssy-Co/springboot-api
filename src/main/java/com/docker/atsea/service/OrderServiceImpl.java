package com.docker.atsea.service;

import org.slf4j.MDC;
import datadog.trace.api.CorrelationIdentifier;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.docker.atsea.model.Order;
import com.docker.atsea.repositories.OrderRepository;

import datadog.trace.api.Trace;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Value("${python.url}")
	private String pythonUrl;
		
	@Trace
	public Order findById(Long orderId) {
		
		RestTemplate restTemplate = new RestTemplate();
		;
		ResponseEntity<String> response
		  = restTemplate.getForEntity(pythonUrl, String.class);
		return orderRepository.findOne(orderId) ;
	}

	@Trace
	public Order createOrder(Order order) {	
		RestTemplate restTemplate = new RestTemplate();
		;
		ResponseEntity<String> response
		  = restTemplate.getForEntity(pythonUrl, String.class);
		order = orderRepository.save(order);
		orderRepository.flush();
		return order;
	}

	@Trace
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}
	
	@Trace
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	@Trace
	public void deleteOrderById(Long orderId) {
		orderRepository.delete(orderId);
	}

	@Trace
	public void deleteAllItems() {
		orderRepository.deleteAll();
	}

	@Trace
	public boolean orderExists(Order order) {
		return findById(order.getOrderId()) != null;
	}

	@Trace
	public List<Order> findAllOrders() {
		return (List<Order>) orderRepository.findAll();
	}	
}
