package com.tortuga.security.governance.platform.payload.request;

import javax.validation.constraints.NotBlank;

public class SecurityRuleRevisionRequest {
	
	@NotBlank
	String projectId ;
	
	@NotBlank
	String ruleId ;
	
	int unit ;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}
	

}
