package com.openclassrooms.apisafetynet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnfindablePersonException extends RuntimeException {

	public UnfindablePersonException(String message) {
		super(message);
	}

	
}
