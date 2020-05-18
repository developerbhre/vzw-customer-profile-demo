package com.vzw.vzwdiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VzwDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VzwDiscoveryServerApplication.class, args);
	}

}
