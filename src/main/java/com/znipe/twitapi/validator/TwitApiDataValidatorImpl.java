package com.znipe.twitapi.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.znipe.twitapi.entity.Followers;
import com.znipe.twitapi.exceptions.TwitApiException;
import com.znipe.twitapi.model.JwtUser;

@Validated
@Component
public class TwitApiDataValidatorImpl implements TwitApiDataValidator{

	// Method to validate input data
	public Followers validateInput(String dataObj) throws TwitApiException, Exception {
		Followers data = null;
		ObjectMapper objectMapper = new ObjectMapper();
		data = objectMapper.readValue(dataObj, Followers.class);
		if ((data.getAccountID() == null || data.getFollowersID() == null)
				&& (data.getAccountID() instanceof String || data.getFollowersID() instanceof String))
			throw new TwitApiException("INVALID INPUT PARAMETERS", HttpStatus.BAD_REQUEST);
		else
			return data;
	}
	// Method to validate JWT token input
	public JwtUser validateJwtInput(String dataObj) throws TwitApiException, Exception {
		JwtUser data = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		data = objectMapper.readValue(dataObj, JwtUser.class);
		} catch(Exception e)
		{throw new TwitApiException(e.getMessage());}
		if ((data.getUsername() == null && data.getRole() != "admin" &&  data.getId() >0 ))
			throw new TwitApiException("INVALID JWT USER PARAMETERS", HttpStatus.BAD_REQUEST);
		else
			return data;
	}

	// Method to validate fields
	public String validatefield(String input) throws TwitApiException {
		if (input == null)
			throw new TwitApiException("INVALID INPUT PARAMETERS", HttpStatus.BAD_REQUEST);
		else
			return input;
	}
}
