package com.tortuga.security.governance.platform.phase2.models.helper;

import java.util.List;

public class RuleFileset {
	
	private String modified;
    private String checksum;
    private String path;
    private List<Rule> rules;
    private String created;
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
    
}
