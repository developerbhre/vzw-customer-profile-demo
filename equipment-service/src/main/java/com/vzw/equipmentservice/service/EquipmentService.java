package com.vzw.equipmentservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vzw.equipmentservice.domain.CustomerEquipment;
import com.vzw.equipmentservice.domain.Equipment;
import com.vzw.equipmentservice.repository.CustomerEquipmentRepository;
import com.vzw.equipmentservice.repository.EquipmentRepository;
import com.vzw.equipmentservice.response.VZWEquipment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EquipmentService {

	@Autowired
	private CustomerEquipmentRepository equipmentRepository;

	@Autowired
	private EquipmentRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentService.class);

	public Flux<Equipment> fetchAllEquipments() {
		List<Equipment> findAll = repository.findAll();
		return Flux.fromIterable(findAll);
	}

	public Mono<Equipment> fetchEquipment(Long equipmentId) {
		Optional<Equipment> findById = repository.findById(equipmentId);
		return Mono.justOrEmpty(findById);
	}

	public Mono<String> addEquipment(Equipment equipment) {
		equipment = repository.save(equipment);
		return Mono.just("Equipment saved successfully with id="+equipment.getId());
	}

	public Mono<String> mapEquipment(CustomerEquipment equipment) {
		Optional<CustomerEquipment> custEquipment = equipmentRepository.findByCustomerIdAndEquipmentId(equipment.getCustomerId(), equipment.getEquipmentId());
		if(!custEquipment.isPresent()) {
			equipmentRepository.save(equipment);
		}

		return Mono.just("Successfully mapped!");
	}

	public Mono<VZWEquipment> fetchEquipmentByCustomerId(Long customerId) {
		LOGGER.info("Fetching Equipment details for customerId: {}", customerId);
		List<CustomerEquipment> findByCustomerId = equipmentRepository.findByCustomerId(customerId);
		List<Long> equipmentIds = findByCustomerId.stream().map(m->m.getEquipmentId()).collect(Collectors.toList());
		List<Equipment> equipments = repository.findAllById(equipmentIds);
		VZWEquipment vzwEquipment = new VZWEquipment();
		vzwEquipment.setCustomer_id(customerId);
		vzwEquipment.setEquipment(equipments);
		return Mono.just(vzwEquipment);
	}

}
