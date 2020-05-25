package com.vzw.profileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vzw.profileservice.models.VZWProfile;
import com.vzw.profileservice.service.ProfileService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	
	@Autowired
	private ProfileService profileService;

	@GetMapping(path="/{customer_id}",produces = "application/json")
	public Mono<VZWProfile> getCustomers(@PathVariable(name = "customer_id") Long customerId) {
		
		return profileService.fetchProfile(customerId);
	}
	
	@GetMapping(path="/user/{customer_id}",produces = "application/json")
	public Mono<String> getUserRole(@PathVariable(name = "customer_id") Long customerId) {
		
		return profileService.getUserRole(customerId);
	}
}
