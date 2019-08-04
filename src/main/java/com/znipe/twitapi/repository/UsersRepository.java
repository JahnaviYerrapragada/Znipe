package com.znipe.twitapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.znipe.twitapi.entity.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

	public Users findByAccountID(String accountID);

}
