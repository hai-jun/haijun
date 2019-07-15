package com.duy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HJServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(HJServerApplication.class, args);
	}
}
