package com.tortuga.security.governance.platform;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tortuga.security.governance.platform.payload.request.LoginRequest;
import com.tortuga.security.governance.platform.payload.request.RoleCreateRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class SpringRoleServicesTest {
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
	void roleBasedLogin() throws Exception {
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
		 System.out.println("Type :: "+type +" token :: "+token);
	
	}
	
	@Test
	@Order(2)
	void createRole() throws Exception {
		RoleCreateRequest roleCreate = new RoleCreateRequest();
		roleCreate.setName("ROLE_TEST");
		
		 mvc.perform(MockMvcRequestBuilders
	        		.post("/api/role/createRole")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(roleCreate))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
		
	}

	@Test
	@Order(3)
	void createRoleDuplicate() throws Exception {
		RoleCreateRequest roleCreate = new RoleCreateRequest();
		roleCreate.setName("ROLE_TEST");
		
		 mvc.perform(MockMvcRequestBuilders
	        		.post("/api/role/createRole")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(roleCreate))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isBadRequest());
		
	}
	
	@Test
	@Order(4)
	void deleteRole() throws Exception {
		RoleCreateRequest roleCreate = new RoleCreateRequest();
		roleCreate.setName("ROLE_TEST");
		
		 MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
	        		.delete("/api/role/deleteRole")
	        		.header("Authorization", type+" "+token)
	        		.content(asJsonString(roleCreate))
	        		.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk()).andReturn();
		
		
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
