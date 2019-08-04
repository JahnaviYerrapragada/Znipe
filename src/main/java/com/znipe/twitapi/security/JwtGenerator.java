package com.znipe.twitapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.znipe.twitapi.exceptions.TwitApiException;
import com.znipe.twitapi.model.JwtUser;
import com.znipe.twitapi.service.TwitapiService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	
	@Autowired
	private TwitapiService twitapiService;
	

	public String generate(JwtUser jwtUser) throws TwitApiException {
		
       if(jwtUser.getUsername() != null && twitapiService.findUser(jwtUser.getUsername()) != null) {
		Claims claims = Jwts.claims().setSubject(jwtUser.getUsername());
		// TODO Auto-generated method stub
		claims.put("userId", String.valueOf(jwtUser.getId()));
		claims.put("role", jwtUser.getRole());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "Znipe").compact();
       } else 
    	   return "User Does Not Exists";
    			   
       }

}
