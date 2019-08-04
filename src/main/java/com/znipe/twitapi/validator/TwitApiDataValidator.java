package com.znipe.twitapi.validator;

import com.znipe.twitapi.entity.Followers;
import com.znipe.twitapi.exceptions.TwitApiException;
import com.znipe.twitapi.model.JwtUser;

public interface TwitApiDataValidator {

	public Followers validateInput(String dataObj) throws TwitApiException, Exception ;

	public String validatefield(String input) throws TwitApiException;
	
	public JwtUser validateJwtInput(String dataObj) throws TwitApiException, Exception ;
}
