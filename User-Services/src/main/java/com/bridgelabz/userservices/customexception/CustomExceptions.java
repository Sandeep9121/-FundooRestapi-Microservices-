package com.bridgelabz.userservices.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.userservices.response.ErrorResponse;
@ControllerAdvice
public class CustomExceptions   extends ResponseEntityExceptionHandler{

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorResponse> UserExceptions(UserNotFoundException e){
		ErrorResponse ex=new ErrorResponse();
		ex.setMessage(e.getMessage());
		ex.setStatusCode(e.getStatusCode());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage(),ex.getStatusCode()));
		
	}
	
	@ExceptionHandler(UserNotVerifiedException.class)
	public final ResponseEntity<ErrorResponse> userNotVerifiedExceptions(UserNotVerifiedException e){
		ErrorResponse ex=new ErrorResponse();
		ex.setMessage(e.getMessage());
		ex.setStatusCode(e.getStatusCode());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage(),ex.getStatusCode()));
		
	}
	
	@ExceptionHandler(MailNotFoundException.class)
	public final ResponseEntity<ErrorResponse> mailNotfoundExceptions(MailNotFoundException e){
		ErrorResponse ex=new ErrorResponse();
		ex.setMessage(e.getMessage());
		ex.setStatusCode(e.getStatusCode());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage(),ex.getStatusCode()));
		
	}

	@ExceptionHandler(ExitsEmailException.class)
	public final ResponseEntity<ErrorResponse> mailExceptions(ExitsEmailException e){
		ErrorResponse ex=new ErrorResponse();
		ex.setMessage(e.getMessage());
		ex.setStatusCode(e.getStatusCode());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage(),ex.getStatusCode()));
	
	}
}
