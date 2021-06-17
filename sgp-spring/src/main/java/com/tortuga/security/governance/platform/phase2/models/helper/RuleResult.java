package com.tortuga.security.governance.platform.phase2.models.helper;

import org.springframework.data.mongodb.core.mapping.Field;

public class RuleResult {
	
	@Field("id")
	public String rule_id;
    public String result;
    
	public String getRule_id() {
		return rule_id;
	}
	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
    
    

}
