package com.znipe.twitapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.znipe.twitapi.entity.Tweets;

@Repository
public interface TweetsRepository extends CrudRepository<Tweets, Long> {

	public List<Tweets> findByAccountID(String id);

}
