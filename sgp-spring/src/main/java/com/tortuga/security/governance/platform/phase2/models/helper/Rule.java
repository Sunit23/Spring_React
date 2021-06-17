package com.tortuga.security.governance.platform.phase2.models.helper;

import org.springframework.data.mongodb.core.mapping.Field;

public class Rule {

	@Field("id")
	private String rule_id;
	private String rule;
	
	public String getRule_id() {
		return rule_id;
	}
	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	 
	 
	
}
