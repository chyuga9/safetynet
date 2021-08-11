package com.openclassrooms.apisafetynet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnfindablePersonException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 315424253854436395L;

	public UnfindablePersonException(String message) {
		
		super(message);
	}
	/*
	public UnfindablePersonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnfindablePersonException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnfindablePersonException(Throwable cause) {
		super(cause);
	}
*/
	
}
