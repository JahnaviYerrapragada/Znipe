package com.znipe.twitapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.znipe.twitapi.entity.Followers;

@Repository
public interface FollowersRepository extends CrudRepository<Followers, Long> {

	public Followers findByAccountIDAndFollowersID(String accountID,String followersID);

	public void deleteByFollowersIDAndAccountID(String followersID, String accountID);

	public List<Followers> findByAccountID(String accountID);

}
