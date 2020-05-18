package com.vzw.equipmentservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vzw.equipmentservice.domain.CustomerEquipment;

public interface CustomerEquipmentRepository extends JpaRepository<CustomerEquipment, Long>{

	List<CustomerEquipment> findByCustomerId(Long customerId);
	Optional<CustomerEquipment> findByCustomerIdAndEquipmentId(Long customerId, Long equipmentId);
}
