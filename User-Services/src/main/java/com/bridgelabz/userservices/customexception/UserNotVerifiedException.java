package com.bridgelabz.userservices.customexception;

import org.springframework.http.HttpStatus;
import lombok.Getter;
@Getter
public class UserNotVerifiedException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	  private String message;
	  private HttpStatus statusCode;
	public UserNotVerifiedException(String message, HttpStatus statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

}
