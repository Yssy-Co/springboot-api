package com.docker.atsea.service;

import org.slf4j.MDC;
import datadog.trace.api.CorrelationIdentifier;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docker.atsea.model.Customer;
import com.docker.atsea.repositories.CustomerRepository;

import datadog.trace.api.Trace;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Trace
	public Customer findById(Long customerId) {
		return customerRepository.findOne(customerId);
	}

	@Trace
	public Customer findByUserName(String name) {
		return customerRepository.findByUserName(name);
	}

	@Trace
	public Customer findByName(String name) {
		return customerRepository.findByName(name);
	}
	
	@Trace
	public Customer createCustomer(Customer customer) {		
		customer = customerRepository.save(customer);
		customerRepository.flush();
		return customer;
	}
	
	@Trace
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Trace
	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Trace
	public void deleteAllCustomers() {
		customerRepository.deleteAll();
	}

	@Trace
	public List<Customer> findAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Trace
	public boolean customerExist(Customer customer) {
		System.out.println(customer.getUsername());
		return customerRepository.findByUserName(customer.getUsername()) != null;
	}

	@Trace
	public void deleteCustomerById(Long customerId) {
		customerRepository.delete(customerId);		
	}
}
