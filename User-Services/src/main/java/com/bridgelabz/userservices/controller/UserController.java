package com.bridgelabz.userservices.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userservices.dto.Forgotpassword;
import com.bridgelabz.userservices.dto.LoginDto;
import com.bridgelabz.userservices.dto.UpdatePassword;
import com.bridgelabz.userservices.dto.UsersDto;
import com.bridgelabz.userservices.model.UsersEntity;
import com.bridgelabz.userservices.response.Response;
import com.bridgelabz.userservices.response.UserVerification;
import com.bridgelabz.userservices.services.IUsersServices;
import com.bridgelabz.userservices.utility.JWTGenerator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class UserController {
	@Autowired
	private UsersEntity user;

    
	
	@Autowired
	private JWTGenerator generateToken;

	@Autowired
	private IUsersServices usersService;
	
//	@Autowired
//	private ProfileServiceImp profileService;

	@PostMapping("/users/register")
	// @RequestMapping(method = RequestMethod.POST,value = "users/register")
	public ResponseEntity<Response> userRegistration(@RequestBody UsersDto userdto) {
		try {
			if (usersService.addUsers(userdto)) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new Response("U have been Registered Successfully",userdto));
			} else {
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
						.body(new Response("U are already Registered"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

//	@PostMapping(value = "/users")
//	public List<UsersEntity> getUsers() {
//		return usersService.getUserDetails();
//	}

	@PutMapping("/users/verify/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String token) {
		
		

		if (usersService.verify(token)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("U have verified Successfully", 200));
		}

		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not verified", 400));

		}
	}

	@GetMapping(value = "/users/{token}")
	public ResponseEntity<Response> getUserById(@PathVariable("token") String token) {
		UsersEntity user=usersService.getuserById(token);
		if(user!=null) {
		return ResponseEntity.status(HttpStatus.OK).body(new Response("User is fetched",user));
	}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Unable to  fetch",user));
		}

	@PostMapping("/users/login")
	public ResponseEntity<UserVerification> login(@RequestBody LoginDto loginData) {
		UsersEntity userLogin = usersService.login(loginData);
		String token = generateToken.generateWebToken(userLogin.getUserId());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserVerification(token,"u have been Successfully Login",userLogin));
	}

	@PutMapping("/users/updatePassword/{token}")
	public ResponseEntity<Response> updatePassword(@Valid @PathVariable("token") String token,
			@RequestBody UpdatePassword password) {
		
		log.info("token"+token);
		log.info("pass"+password.getNewPassword());
		log.info("pass"+password.getChangepassword());
		user = usersService.updatePassword(token, password);
		if (user != null)
			return ResponseEntity.status(HttpStatus.OK).body(new Response(token,"password is updated Succesfully"));
		else
		{
			log.info("helooooo");
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
					.body(new Response("password and confirm password is not matched"));
		}
	}
	
	
	@PostMapping("/users/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody Forgotpassword forgotPassword){
		boolean result =usersService.isUserAlreadyRegistered(forgotPassword.getEmail());
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("user exits",200,forgotPassword.getEmail()));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("user does not exist with given email id", 400, forgotPassword.getEmail()));
		
	}

}
