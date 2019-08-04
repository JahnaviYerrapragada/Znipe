package com.znipe.twitapi.security;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.znipe.twitapi.exceptions.TwitApiRunTimeException;
import com.znipe.twitapi.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	String secret = "Znipe";

	public JwtUser validate(String token){
       
		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			jwtUser = new JwtUser();
			jwtUser.setUsername(body.getSubject());
			jwtUser.setId(Long.parseLong((String) body.get("userId")));
			jwtUser.setRole((String) body.get("role"));
		} catch(Exception e) {
			throw new TwitApiRunTimeException("INVALID JWT Token",HttpStatus.UNAUTHORIZED);
		}
		
		return jwtUser;
	}

}
