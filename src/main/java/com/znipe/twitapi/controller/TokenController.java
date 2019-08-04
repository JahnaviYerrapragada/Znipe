package com.znipe.twitapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.znipe.twitapi.exceptions.TwitApiException;
import com.znipe.twitapi.model.JwtUser;
import com.znipe.twitapi.security.JwtGenerator;
import com.znipe.twitapi.service.TwitapiService;
import com.znipe.twitapi.validator.TwitApiDataValidator;

@RestController
@RequestMapping("/token")
public class TokenController {

	private JwtGenerator jwtGenerator;
	
	@Autowired
	private TwitApiDataValidator twitApiDataValidator;

	public TokenController(JwtGenerator jwtGenerator) {
		super();
		this.jwtGenerator = jwtGenerator;
	}

	//API to fetch the token
	@PostMapping
	public String generate(@RequestBody final String data) throws TwitApiException, Exception {
		
		JwtUser user = twitApiDataValidator.validateJwtInput(data);
		
		return jwtGenerator.generate(user);
		
	}

}
