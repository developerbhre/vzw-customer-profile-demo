package com.vzw.equipmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vzw.equipmentservice.domain.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

}
