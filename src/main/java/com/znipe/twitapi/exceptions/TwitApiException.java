package com.znipe.twitapi.exceptions;

import org.springframework.http.HttpStatus;

public class TwitApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public TwitApiException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public TwitApiException(String message) {
		super(message);
	}

	public TwitApiException(Exception e) {
		super(e);
	}
	
}
