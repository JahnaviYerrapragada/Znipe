package com.znipe.twitapi.exceptions;

import org.springframework.http.HttpStatus;

public class TwitApiRunTimeException extends RuntimeException {

	private static final long serialVersionUID = 2484333118437022809L;
	private HttpStatus httpStatus;
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public TwitApiRunTimeException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

}
