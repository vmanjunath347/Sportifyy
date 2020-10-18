package com.sportify.webapp.sportifyshoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
public class EmptyFieldException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public EmptyFieldException(String message) {
		super(message);
	}
}
