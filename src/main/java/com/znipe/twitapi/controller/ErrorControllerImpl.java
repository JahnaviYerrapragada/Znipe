package com.znipe.twitapi.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.znipe.twitapi.exceptions.TwitException;

@Controller
public class ErrorControllerImpl implements ErrorController {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleValidationException(final RuntimeException runtimeException) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		TwitException twitException = new TwitException(runtimeException.getMessage(), badRequest,
				ZonedDateTime.now(ZoneId.of("Z")));

		return new ResponseEntity<Object>(twitException, badRequest);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnknownException(final Exception exception) {
		HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
		TwitException twitException = new TwitException(exception.getMessage(), badRequest,
				ZonedDateTime.now(ZoneId.of("Z")));

		return new ResponseEntity<Object>(twitException, badRequest);
	}

	@RequestMapping("/error")
	public ResponseEntity<Object> handleError(HttpServletRequest request) {

		HttpStatus badRequest = HttpStatus.UNAUTHORIZED;
		TwitException twitException = new TwitException("INVALID_JWT_TOKEN", badRequest,
				ZonedDateTime.now(ZoneId.of("Z")));

		return new ResponseEntity<Object>(twitException, badRequest);
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}
