package com.bridgelabz.userservices.services;

import com.bridgelabz.userservices.dto.LoginDto;
import com.bridgelabz.userservices.dto.UpdatePassword;
import com.bridgelabz.userservices.dto.UsersDto;
import com.bridgelabz.userservices.model.UsersEntity;

public interface IUsersServices {

	public boolean addUsers(UsersDto userdto) throws Exception;
	
	public boolean isUserAlreadyRegistered(String email);
	
	
	public UsersEntity updatePassword(String token,UpdatePassword password);

	public UsersEntity getuserById(String token);
	
	public boolean verify(String token);
	
	public UsersEntity login(LoginDto loginData);

}
