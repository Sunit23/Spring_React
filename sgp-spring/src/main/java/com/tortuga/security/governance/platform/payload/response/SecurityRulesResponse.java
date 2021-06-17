package com.tortuga.security.governance.platform.payload.response;

public class SecurityRulesResponse {

	String id;
	String projectId;
	String checksum;
	String ruleID;
	String ruleText;
	String file;
	String modifiedDate;
	String status;
	String resultsDate;
	String test;
	String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	public String getRuleText() {
		return ruleText;
	}
	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String result) {
		this.status = result;
	}
	public String getResultsDate() {
		return resultsDate;
	}
	public void setResultsDate(String resultsDate) {
		this.resultsDate = resultsDate;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SecurityRulesResponse(String id, String projectId, String checksum, String ruleID, String ruleText,
			String file, String modifiedDate, String result, String resultsDate, String test, String description) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.checksum = checksum;
		this.ruleID = ruleID;
		this.ruleText = ruleText;
		this.file = file;
		this.modifiedDate = modifiedDate;
		this.status = result;
		this.resultsDate = resultsDate;
		this.test = test;
		this.description = description;
	}
	public SecurityRulesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
