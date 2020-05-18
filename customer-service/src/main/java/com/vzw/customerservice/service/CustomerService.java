package com.vzw.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vzw.customerservice.domain.Address;
import com.vzw.customerservice.domain.Customer;
import com.vzw.customerservice.repository.AddressRepository;
import com.vzw.customerservice.repository.CustomerRepository;
import com.vzw.customerservice.response.VZWCustomer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private AddressRepository addressRepository;

	public Flux<Customer> fetchAllCustomers() {

		List<Customer> customers = repository.findAll();
		return Flux.fromIterable(customers);
	}

	public List<Customer> fetchAllCx() {
		return repository.findAll();
	}

	public Mono<VZWCustomer> fetchCustomer(Long customerId) {
		Optional<Customer> findById = repository.findById(customerId);
		List<Address> findByCustomerId = addressRepository.findByCustomerId(customerId);
		VZWCustomer vzwCustomer = null;
		if(findById.isPresent()) {
			vzwCustomer = new VZWCustomer();
			vzwCustomer.setId(findById.get().getId());
			vzwCustomer.setFirstName(findById.get().getFirstName());
			vzwCustomer.setLastName(findById.get().getLastName());
			vzwCustomer.setAddress(findByCustomerId);
		}
		return Mono.justOrEmpty(vzwCustomer);
	}

	public Mono<String> addCustomer(VZWCustomer customer) {
		List<Address> address = customer.getAddress();
		Customer customer2 = new Customer();
		customer2.setId(customer.getId());
		customer2.setFirstName(customer.getFirstName());
		customer2.setLastName(customer.getLastName());
		Customer save = repository.save(customer2);
		address.get(0).setCustomer(save);
		addressRepository.save(address.get(0));
		return Mono.just("Successfully added customer! Id="+save.getId());
	}
}
