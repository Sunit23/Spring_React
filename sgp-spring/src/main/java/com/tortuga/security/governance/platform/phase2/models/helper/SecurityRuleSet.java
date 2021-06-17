package com.tortuga.security.governance.platform.phase2.models.helper;

import org.springframework.data.mongodb.core.mapping.Field;

public class SecurityRuleSet {

	private String checksum;
	private String file;
	private String modified;
	private String ruleID;
	private String rule_text;
	private String description;
	
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	public String getRule_text() {
		return rule_text;
	}
	public void setRule_text(String rule_text) {
		this.rule_text = rule_text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
