package com.tortuga.security.governance.platform.payload.request;

import javax.validation.constraints.NotBlank;

public class SimCoreRequest {

	@NotBlank
	String ruleid;

	public String getRuleid() {
		return ruleid;
	}

	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}

	
	

	
	
	
	
}
