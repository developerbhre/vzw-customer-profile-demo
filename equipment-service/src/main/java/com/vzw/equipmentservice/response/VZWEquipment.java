package com.vzw.equipmentservice.response;

import java.util.List;

import com.vzw.equipmentservice.domain.Equipment;

public class VZWEquipment {
	
	private Long customer_id;
	
	private List<Equipment> equipment;

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

}
