package com.tortuga.security.governance.platform;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tortuga.security.governance.platform.payload.request.LoginRequest;
import com.tortuga.security.governance.platform.payload.request.SignupRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@org.springframework.core.annotation.Order(1)
class SpringBootSecurityJwtMongodbApplicationTests {
	public static String token ;
	public static String type;
	
	

	public SpringBootSecurityJwtMongodbApplicationTests() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	void contextLoads() {
	}
	
	
	 @Autowired
	 private MockMvc mvc;
	 
	 @Test
	 @Order(1)
	 public void userRegistrationTest() throws Exception {
	
		 Set<String> roles = new HashSet<>();
		 roles.add("ROLE_ADMIN");
	
		 SignupRequest signupRequest = new SignupRequest();
		 signupRequest.setUsername("test");
		 signupRequest.setPassword("123456");
		 signupRequest.setEmail("test@email.com");
		 signupRequest.setRole(roles);
	        mvc.perform(MockMvcRequestBuilders
	        		.post("/api/auth/signup")
	        		.content(asJsonString(signupRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User registered successfully!"))
	        .andExpect(status().isOk());
	 }
	 
	 @Test
	 @Order(2)
	 public void userSignIn() throws Exception{
		 
		 LoginRequest loginRequest = new LoginRequest();
		 loginRequest.setUsername("test");
		 loginRequest.setPassword("123456");
		 MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
	        		.post("/api/auth/signin")
	        		.content(asJsonString(loginRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk()).andReturn();
		 
		 String responseBody = mvcResult.getResponse().getContentAsString();
		 ObjectMapper mapper = new ObjectMapper();
		 JsonNode node = mapper.readTree(responseBody);
		 type=node.get("tokenType").asText();
		 token=node.get("accessToken").asText();
		 assertEquals("Bearer", type);
		 assertEquals(3, node.get("accessToken").asText().split("\\.").length);
	 }
	 
	
	 
	 public static String asJsonString(final Object obj) {
		    try {
		        final ObjectMapper mapper = new ObjectMapper();
		        final String jsonContent = mapper.writeValueAsString(obj);
		        System.out.println("Json : "+jsonContent);
		        return jsonContent;
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}  

}
