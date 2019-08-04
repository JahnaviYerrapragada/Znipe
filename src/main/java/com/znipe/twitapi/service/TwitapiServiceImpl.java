package com.znipe.twitapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.znipe.twitapi.entity.Followers;
import com.znipe.twitapi.entity.Tweets;
import com.znipe.twitapi.entity.TweetsReplies;
import com.znipe.twitapi.entity.Users;
import com.znipe.twitapi.exceptions.TwitApiException;
import com.znipe.twitapi.repository.FollowersRepository;
import com.znipe.twitapi.repository.TweetRepliesRepository;
import com.znipe.twitapi.repository.TweetsRepository;
import com.znipe.twitapi.repository.UsersRepository;

@Transactional
@Service
public class TwitapiServiceImpl implements TwitapiService {

	private static final Logger logger = LoggerFactory.getLogger(TwitapiServiceImpl.class);
	private static final String INVALID_ACCOUNTID = "UNABLE TO FETCH THE RECORDS FOR THE INVALID ACCOUNT";
	private static final String INVALID_ACCOUNTID_FOLLOWERSID = "UNABLE TO PROCESS THE REQUEST FOR THE INVALID ACCOUNT ID OR FOLLOWERS ID";
	private static final String INVALID_FOLLOW_REQUEST = "UNABLE TO PROCESS THE REQUEST FOR ALREADY FLOWWING USERS";
	private static final String INVALID_UNFOLLOW_REQUEST = "UNABLE TO PROCESS THE REQUEST FOR USERS WHO ARE NOT FOLLOWING";
	private static final String NO_FOLLOWERS = "REQUESTED USER HAS NO FOLLOWERS";
	private static final String NO_TWEETS= "REQUESTED USER HAS NO TWEETS";

	
	private static final String FOLLOW = "FOLLOW";
	private static final String UNFOLLOW = "UNFOLLOW";

	private Users follower = null;
	private Users user = null;

	@Autowired
	private FollowersRepository followersRepository;

	@Autowired
	private TweetsRepository tweetsRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private TweetRepliesRepository tweetRepliesRepository;

	// API to follow User
	@Override
	public List<Users> followUser(Followers followers) throws TwitApiException {
		List<Users> result = new ArrayList<Users>();
		logger.info(":::: In TwitAPIService followUser method ::::");
		if (existingUsers(followers)) {
			if (!currentlyFollowing(followers)) {
				followersRepository.save(followers);
				logger.info("::: Inserted followers  request details ::: ");
				updateUserDetails(FOLLOW);
				result.add(follower);
				result.add(user);
			} else
				throw new TwitApiException(INVALID_FOLLOW_REQUEST, HttpStatus.BAD_REQUEST);
			return result;
		} else
			throw new TwitApiException(INVALID_ACCOUNTID_FOLLOWERSID, HttpStatus.BAD_REQUEST);
	}

	// API to unfollow user
	@Override
	public List<Users> unfollowUser(Followers followers) throws TwitApiException {
		List<Users> result = new ArrayList<Users>();
		if (existingUsers(followers)) {
			if (currentlyFollowing(followers)) {
				logger.info("::: Updating followers count ::: ");
				followersRepository.deleteByFollowersIDAndAccountID(followers.getFollowersID(),
						followers.getAccountID());
				updateUserDetails(UNFOLLOW);
				result.add(follower);
				result.add(user);
			} else
				throw new TwitApiException(INVALID_UNFOLLOW_REQUEST, HttpStatus.BAD_REQUEST);
			return result;
		} else
			throw new TwitApiException(INVALID_ACCOUNTID_FOLLOWERSID, HttpStatus.BAD_REQUEST);

	}

	
	// API to get the followers details
	@Override
	public List<String> getFollowersDetails(String accountID) throws TwitApiException {
		if(findUser(accountID) == null) 
			throw new TwitApiException(INVALID_ACCOUNTID, HttpStatus.BAD_REQUEST);
		
		List<String> result = new ArrayList<String>();
		logger.info(" ::: In getFollowersCount Method ::: ");
		List<Followers> data = followersRepository.findByAccountID(accountID);
		if (data == null) 
			throw new TwitApiException(NO_FOLLOWERS, HttpStatus.BAD_REQUEST);
		
			for (Followers follower : data) {
				result.add(follower.getFollowersID());
			}
			return result;

	}

	// API to get the tweets and replies of a user
	@Override
	public List<Object> getUserTweets(String id) throws TwitApiException {
		if(findUser(id) == null) 
			throw new TwitApiException(INVALID_ACCOUNTID, HttpStatus.BAD_REQUEST);
		
		List<String> followers = this.getFollowersDetails(id);
		List<Object> result = new ArrayList<Object>();
		logger.info(" ::: In getUserTweets Method ::: ");
		List<Tweets> tweets = tweetsRepository.findByAccountID(id);
		if (tweets == null) 
			throw new TwitApiException(NO_TWEETS, HttpStatus.BAD_REQUEST);
		
			for (Tweets tweet : tweets) {
				result.add(tweet.getTweet());
				HashMap<String, String> data = findReplies(tweet.getId(), followers);
				if (data != null) {
					result.add(data);
				}
		 } 
		return result;
	}

	// method to find the replies of a tweets
	private HashMap<String, String> findReplies(Integer id, List<String> followers) {
		List<TweetsReplies> tweets = tweetRepliesRepository.findByTweetID(id);
		HashMap<String, String> temp = new HashMap<String, String>();
		HashMap<String, String> result = new HashMap<String, String>();
		if (tweets != null) {
			for (TweetsReplies tweet : tweets) {
				temp.put(tweet.getAccountID(), tweet.getTweet());
			}
			if (followers != null) {
				for (String flrsID : followers) {
					if (temp.get(flrsID) != null)
						result.put(flrsID, temp.get(flrsID));
				}
			}
			return result;
		} else
			return null;
	}

	// Method to update users followers and following details
	private void updateUserDetails(String details) throws TwitApiException {
		try {
			if (details == FOLLOW) {
				int currentFollowers = user.getFollowers();
				int currentFollowing = follower.getFollowing();
				user.setFollowers(currentFollowers + 1);
				follower.setFollowing(currentFollowing + 1);
			} else if (details == UNFOLLOW) {
				int currentFollowers = user.getFollowers();
				int currentFollowing = follower.getFollowing();
				user.setFollowers(currentFollowers - 1);
				follower.setFollowing(currentFollowing - 1);
			}
			usersRepository.save(user);
			usersRepository.save(follower);

			logger.info("::: Updated user followers count ::: ");
		} catch (Exception e) {
			e.printStackTrace();
			throw new TwitApiException(INVALID_ACCOUNTID_FOLLOWERSID, HttpStatus.BAD_REQUEST);
		}
	}
    
	// Method to find if a User exists
	public Users findUser(String id) throws TwitApiException {
		logger.info("::: In findUser Method ::: ");
		try {
			return usersRepository.findByAccountID(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TwitApiException(INVALID_ACCOUNTID, HttpStatus.BAD_REQUEST);
		}
	}

	// Method to verify if the users requested for follow/unfollow are valid users
	private boolean existingUsers(Followers followers) throws TwitApiException {
		logger.info("::: In existingUser Method ::: ");
		try {
			follower = findUser(followers.getFollowersID());
			user = findUser(followers.getAccountID());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TwitApiException(INVALID_ACCOUNTID_FOLLOWERSID, HttpStatus.BAD_REQUEST);
		}
	}
    // Method to verify if the users are already following or not
	private boolean currentlyFollowing(Followers followers) throws TwitApiException {
		logger.info(" ::: In currentlyFollowing Method ::: ");
		Followers flrs = followersRepository.findByAccountIDAndFollowersID(followers.getAccountID(),
				followers.getFollowersID());
		if (flrs != null)
			return true;
		else
			return false;
	}

}
