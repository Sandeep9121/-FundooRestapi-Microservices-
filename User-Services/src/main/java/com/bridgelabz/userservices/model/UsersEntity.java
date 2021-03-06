package com.bridgelabz.userservices.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name = "Userdata")
@Component
public class UsersEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "user_id")
	// @NotNull
	private long userId;

	@Column(name = "user_Name")
	private String name;

	@Column(name = "email", unique = true)
	// @Size(min = 8)
	// @NotNull
	private String email;

	// @Column(name = "Password" )
	// @Size(min = 8)
	private String password;

//	@Column(name = "mobile_number")
	// @Size(min = 10, max = 12)
//	@NotNull
	private Long mobileNumber;

//	@Column(name = "Registered_date")
	// @NotNull
	private LocalDateTime date;

	@Column(name = "is_verified")
	// @NotNull
	private boolean isVerified;
}