package com.vzw.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vzw.customerservice.domain.Customer;
import com.vzw.customerservice.response.VZWCustomer;
import com.vzw.customerservice.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/all")
	public Flux<Customer> getCustomers() {
		
		return customerService.fetchAllCustomers();
	}
	
	@GetMapping(path="/{id}",produces = "application/json")
	public Mono<VZWCustomer> getCustomers(@PathVariable(name = "id") Long customerId) {
		
		return customerService.fetchCustomer(customerId);
	}

	@GetMapping(path = "/allcx",produces = "application/json")
	public List<Customer> getAllCustomers() {
		
		List<Customer> fetchAllCx = customerService.fetchAllCx();
		return fetchAllCx;
	}
	
	@PostMapping("/add")
	public Mono<String> addCustomer(@RequestBody VZWCustomer customer) {
		return customerService.addCustomer(customer);
	}
}
