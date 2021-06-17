package com.tortuga.security.governance.platform.payload.request;

import javax.validation.constraints.NotBlank;

public class RoleCreateRequest {
	
	@NotBlank
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
