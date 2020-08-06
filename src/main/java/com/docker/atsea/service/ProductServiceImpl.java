package com.docker.atsea.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.docker.atsea.model.Product;
import com.docker.atsea.repositories.ProductRepository;

import datadog.trace.api.Trace;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Value("${python.url}")
	private String pythonUrl;

	@Trace
	public List<Product> findAllProducts() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		URI uri = URI.create(pythonUrl);
		ResponseEntity<String> response
		  = restTemplate.getForEntity(uri, String.class);
		return productRepository.findAll();
	}

	
	@Trace
	public Product findByName(String name) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response
		  = restTemplate.getForEntity(pythonUrl, String.class);
		return productRepository.findByName(name);
	}

	
	@Trace
	public Product findById(Long productId) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response
		  = restTemplate.getForEntity(pythonUrl, String.class);
		return productRepository.findOne(productId);
	}
}
