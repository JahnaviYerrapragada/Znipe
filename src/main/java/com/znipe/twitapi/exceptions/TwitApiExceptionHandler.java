package com.znipe.twitapi.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class TwitApiExceptionHandler{

	@ExceptionHandler(value = { TwitApiException.class })
	public final ResponseEntity<Object> handleAPIException(TwitApiException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		TwitException twitException = new TwitException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));

		return new ResponseEntity<Object>(twitException, badRequest);
	}
}
