package com.tortuga.security.governance.platform;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tortuga.security.governance.platform.payload.request.LoginRequest;
import com.tortuga.security.governance.platform.payload.request.SignupRequest;
import com.tortuga.security.governance.platform.payload.request.UserRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class SpringUserServiceTest {

	private static String token ;
	private static String type;
	
	 @Autowired
	 private MockMvc mvc;
	

	

	/**
	 * Login to Tortuga Application
	 * @throws Exception 
	 */
	@Test
	@Order(1)
	void userLogin() throws Exception {
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
	
	}
	
	 @Test
	 @Order(2)
	 public void userRegistrationTest() throws Exception {
	
		 Set<String> roles = new HashSet<>();
		 roles.add("ROLE_ADMIN");
	
		 SignupRequest signupRequest = new SignupRequest();
		 signupRequest.setUsername("user");
		 signupRequest.setPassword("123456");
		 signupRequest.setEmail("user@email.com");
		 signupRequest.setRole(roles);
	        mvc.perform(MockMvcRequestBuilders
	        		.post("/api/auth/signup")
	        		.content(asJsonString(signupRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User registered successfully!"))
	        .andExpect(status().isOk());
	 }
	 
	 @Test
	 @Order(3)
	 public void getUserDetails() throws Exception {
	
		 UserRequest userRequest = new UserRequest();
		 userRequest.setUsername("user");
		
	        mvc.perform(MockMvcRequestBuilders
	        		.get("/api/user/GetUserDetails")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(userRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	 }
	 
	 @Test
	 @Order(4)
	 public void getUserDetailsWithoutToken() throws Exception {
	
		 UserRequest userRequest = new UserRequest();
		 userRequest.setUsername("user");
		
	        mvc.perform(MockMvcRequestBuilders
	        		.get("/api/user/GetUserDetails")
	        		.content(asJsonString(userRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isUnauthorized());
	 }
	 
	 @Test
	 @Order(5)
	 public void deactivateuser() throws Exception {
	
		 UserRequest userRequest = new UserRequest();
		 userRequest.setUsername("user");
		
	        mvc.perform(MockMvcRequestBuilders
	        		.put("/api/user/deactiveUser")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(userRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	 }
	 
	 @Test
	 @Order(6)
	 public void activateUser() throws Exception {
	
		 UserRequest userRequest = new UserRequest();
		 userRequest.setUsername("user");
		
	        mvc.perform(MockMvcRequestBuilders
	        		.put("/api/user/activateUser")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(userRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	 }
	 
	 @Test
	 @Order(7)
	 public void deleteUser() throws Exception {
	
		 UserRequest userRequest = new UserRequest();
		 userRequest.setUsername("user");
		
	        mvc.perform(MockMvcRequestBuilders
	        		.delete("/api/user/deleteUser")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(userRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	 }
	 
	 
	 @Test
	 @Order(8)
	 public void deleteLoginUser() throws Exception {
	
		 UserRequest userRequest = new UserRequest();
		 userRequest.setUsername("test");
		
	        mvc.perform(MockMvcRequestBuilders
	        		.delete("/api/user/deleteUser")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(userRequest))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	 }
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	
}
