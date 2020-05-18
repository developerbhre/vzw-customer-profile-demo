package com.vzw.equipmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.vzw.equipmentservice")
@EnableEurekaClient
public class EquipmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentServiceApplication.class, args);
	}

}
