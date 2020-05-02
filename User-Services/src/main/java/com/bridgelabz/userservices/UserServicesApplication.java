package com.bridgelabz.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//nableEurekaClient
public class UserServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServicesApplication.class, args);
	}

}
