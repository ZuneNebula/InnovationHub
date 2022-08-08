package com.niit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class InnovationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovationManagementApplication.class, args);
	}

}
