package com.znipe.twitapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.znipe.twitapi.entity.TweetsReplies;

@Repository
public interface TweetRepliesRepository extends CrudRepository<TweetsReplies, Long> {

	public List<TweetsReplies> findByTweetID(Integer tweetID);

}
