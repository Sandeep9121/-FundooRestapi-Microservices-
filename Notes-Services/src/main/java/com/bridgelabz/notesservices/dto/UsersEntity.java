package com.bridgelabz.notesservices.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class UsersEntity {

	private long userId;

	private String name;


	private String email;

	private String password;


	private Long mobileNumber;
	private LocalDateTime date;

	private boolean isVerified;

}
