package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeappApplication.class, args);
	}

}
