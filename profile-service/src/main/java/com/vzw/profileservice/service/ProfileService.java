package com.vzw.profileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.vzw.profileservice.models.VZWCustomer;
import com.vzw.profileservice.models.VZWEquipment;
import com.vzw.profileservice.models.VZWProfile;

import reactor.core.publisher.Mono;

@Service
public class ProfileService {
	
	@Value("${user.role}")
	private String role;
	
	@Value("${customer.service.name}")
	private String customerServiceName;
	
	@Value("${equipment.service.name}")
	private String equipmentServiceName;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

	public Mono<VZWProfile> fetchProfile(Long customerId) {
		
		Application application = eurekaClient.getApplication(customerServiceName);
		InstanceInfo instanceInfo = application.getInstances().get(0);

		//VZWCustomer customer = restTemplate.getForObject("http://localhost:8080/customer/" + customerId, VZWCustomer.class);
		VZWCustomer customer = restTemplate.getForObject("http://"+application.getName()+"/customer/" + customerId, VZWCustomer.class);

		//VZWEquipment equipment = restTemplate.getForObject("http://localhost:8081/equipment/customer/" + customerId, VZWEquipment.class);
		VZWEquipment equipment = restTemplate.getForObject("http://"+eurekaClient.getApplication(equipmentServiceName).getName()+"/equipment/customer/" + customerId, VZWEquipment.class);

		VZWProfile vzwProfile = new VZWProfile();
		vzwProfile.setCustomer_id(customerId);
		vzwProfile.setFirstName(customer.getFirstName());
		vzwProfile.setLastName(customer.getLastName());
		vzwProfile.setAddress(customer.getAddress());
		vzwProfile.setEquipment(equipment.getEquipment());

		return Mono.just(vzwProfile);
	}

	public Mono<String> getUserRole(Long customerId) {
		String message = "Customer Role for:"+customerId+", is-"+role;
		return Mono.justOrEmpty(message);
	}

}
