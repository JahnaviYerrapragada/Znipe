package com.znipe.twitapi.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.znipe.twitapi.entity.Users;
import com.znipe.twitapi.exceptions.TwitApiException;
import com.znipe.twitapi.service.TwitapiService;
import com.znipe.twitapi.validator.TwitApiDataValidator;

@RestController
@RequestMapping("/znipe")
public class TwitApiController {
	@Autowired
	private TwitapiService twitapiService;

	@Autowired
	private TwitApiDataValidator twitApiDataValidator;

	private static final Logger logger = LoggerFactory.getLogger(TwitApiController.class);

	// API To FOLLOW USER
	@PutMapping(value = "/follow", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Users> followUser(@RequestBody String data) throws Exception {
		logger.info("Request Initiated to follow user");
		return twitapiService.followUser(twitApiDataValidator.validateInput(data));
	}

	// API TO UNFOLLOW USER
	@PostMapping(value = "/unfollow", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Users> unfollowUser(@RequestBody String data) throws Exception {
		logger.info("Request Initiated to unfollow user");
		return twitapiService.unfollowUser(twitApiDataValidator.validateInput(data));
	}

	// API TO GET A LIST OF PEOPLE FOLLOWING A USER
	@GetMapping(value = "/followers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getFollowersCount(@PathVariable String id) throws TwitApiException {
		logger.info("Request Initiated to follow user");
		return twitapiService.getFollowersDetails(twitApiDataValidator.validatefield(id));
	}

	// API TO GET ALL THE TWEETS OF A USER
	@GetMapping(value = "/tweets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> getUserTweets(@PathVariable String id) throws TwitApiException {
		logger.info("Request Initiated to follow user");
		return twitapiService.getUserTweets(twitApiDataValidator.validatefield(id));
	}

	
}
