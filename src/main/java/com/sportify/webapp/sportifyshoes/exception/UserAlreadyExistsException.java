package com.sportify.webapp.sportifyshoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.ALREADY_REPORTED)
public class UserAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}