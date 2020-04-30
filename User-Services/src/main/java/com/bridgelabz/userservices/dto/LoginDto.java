package com.bridgelabz.userservices.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class LoginDto {
	private String email;
	private  String password;

	}
