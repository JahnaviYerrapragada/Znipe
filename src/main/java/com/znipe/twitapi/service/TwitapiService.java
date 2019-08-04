package com.znipe.twitapi.service;

import java.util.List;
import com.znipe.twitapi.entity.Followers;
import com.znipe.twitapi.entity.Users;
import com.znipe.twitapi.exceptions.TwitApiException;



public interface TwitapiService {


	public List<Users> followUser(Followers followers) throws TwitApiException;

	public List<Users> unfollowUser(Followers followers) throws TwitApiException;

	public List<String> getFollowersDetails(String accountID) throws TwitApiException;

	public List<Object> getUserTweets(String id) throws TwitApiException; 
	
	public Users findUser(String id) throws TwitApiException;
}
