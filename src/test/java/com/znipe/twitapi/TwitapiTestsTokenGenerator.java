package com.znipe.twitapi;


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
import org.junit.runners.MethodSorters;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TwitapiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitapiTestsTokenGenerator {

	@Autowired
	private MockMvc mockMvc;

	String token = null;

	@Test
	public void contextLoads() {
	}

	
	@Test
	public void fetchToken1() throws Exception {
				
	MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(("/token"))
				.content("{ \"username\":\"john@got.com\",  \"id\": 123,  \"role\":\"admin\" }").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	token = result.getResponse().getContentAsString().trim();
	System.out.println("*************************************"+token);

	}
	
	@Test
	public void invalidInput() throws Exception {
				
	MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(("/token"))
				.content("{ \"id\": 123,  \"role\":\"admin\" }").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
	token = result.getResponse().getContentAsString().trim();
	System.out.println("*************************************"+token);

	}
}
