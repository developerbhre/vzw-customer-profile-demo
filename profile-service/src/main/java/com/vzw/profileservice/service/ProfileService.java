package com.vzw.profileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vzw.profileservice.models.VZWCustomer;
import com.vzw.profileservice.models.VZWEquipment;
import com.vzw.profileservice.models.VZWProfile;

import reactor.core.publisher.Mono;

@Service
public class ProfileService {

	@Autowired
	private RestTemplate restTemplate;

	public Mono<VZWProfile> fetchProfile(Long customerId) {

		//VZWCustomer customer = restTemplate.getForObject("http://localhost:8080/customer/" + customerId, VZWCustomer.class);
		VZWCustomer customer = restTemplate.getForObject("http://vzw-customer-service/customer/" + customerId, VZWCustomer.class);

		//VZWEquipment equipment = restTemplate.getForObject("http://localhost:8081/equipment/customer/" + customerId, VZWEquipment.class);
		VZWEquipment equipment = restTemplate.getForObject("http://vzw-equipment-service/equipment/customer/" + customerId, VZWEquipment.class);

		VZWProfile vzwProfile = new VZWProfile();
		vzwProfile.setCustomer_id(customerId);
		vzwProfile.setFirstName(customer.getFirstName());
		vzwProfile.setLastName(customer.getLastName());
		vzwProfile.setAddress(customer.getAddress());
		vzwProfile.setEquipment(equipment.getEquipment());

		return Mono.just(vzwProfile);
	}

}
