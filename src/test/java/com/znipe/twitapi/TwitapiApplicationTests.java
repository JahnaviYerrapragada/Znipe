package com.znipe.twitapi;

import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.znipe.twitapi.entity.Followers;

import org.junit.runners.MethodSorters;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TwitapiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitapiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiIxMjMiLCJyb2xlIjoiYWRtaW4ifQ.AUApyjvIZ8V2ReRZcXtu3FBRnyYL2Ocd9HpA6G6ndTNd-l8naaKli1PjzOw01IwPHZtkMlsaUJvPdV7goD448w";

	// Successful response for Follow Request
	@Test
	public void followUser() throws Exception {
		String data = "{ \"accountID\":\"varysr@got.com\", \"followersID\":\"sansa@got.com\" }";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put(("/znipe/follow")).content(data)
						.contentType(MediaType.APPLICATION_JSON).header("Authorisation", "Token " + token)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		System.out.println(" ***** Result for follow User *****:  " + result.getResponse().getContentAsString());
	}

	// Successful response for UnFollow Request
	@Test
	public void unFollowUser() throws Exception {
		String data = "{ \"accountID\":\"sansa@got.com\", \"followersID\":\"varysr@got.com\" }";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(("/znipe/unfollow")).content(data)
						.contentType(MediaType.APPLICATION_JSON).header("Authorisation", "Token " + token)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		System.out.println(" ***** Result for unFollowUser *****:  " + result.getResponse().getContentAsString());
	}
	
	//API TO GET A LIST OF PEOPLE FOLLOWING A USER
	@Test
	public void getFollowersDetails() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(("/znipe/followers/sansa@got.com"))
						.header("Authorisation", "Token " + token)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		System.out.println(" ***** Result for getFollowersDetails *****:  " + result.getResponse().getContentAsString());
	}
	

	//API TO GET ALL THE TWEETS OF THE USER
	@Test
	public void getUserTweets() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(("/znipe/tweets/sansa@got.com"))
						.header("Authorisation", "Token " + token)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		System.out.println(" ***** Result for getUserTweets *****:  " + result.getResponse().getContentAsString());
	}
	
	//Invalid User
	@Test
	public void invalidUser() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(("/znipe/tweets/san@got.com"))
						.header("Authorisation", "Token " + token)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
		System.out.println(" ***** Result for invalidUser *****:  " + result.getResponse().getContentAsString());
	}

}
