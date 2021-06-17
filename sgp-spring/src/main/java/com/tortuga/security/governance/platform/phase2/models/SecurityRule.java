package com.tortuga.security.governance.platform.phase2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("security_rule")
public class SecurityRule {
	
	@Id
	private String id;
	
	@Field("Project ID")
	private String projectId;
	
	@Field("Rule Checksum")
	private String ruleChecksum;	
	
	@Field("Rule ID")
	private String ruleID;	
	
	@Field("Rule Text")
	private String ruleText;	
	
	@Field("File")
	private String file;	
	
//	@Field("Create Date")
//	private String createDate;
	
	@Field("Modified Date")
	private String modifiedDate;
	
	@Field("Status")
	private String status;
	
	@Field("Status Date")
	private String statusDate;
	
	@Field("Test")
	private String test;
	
	@Field("Description")
	private String description;

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

	public String getRuleChecksum() {
		return ruleChecksum;
	}

	public void setRuleChecksum(String ruleChecksum) {
		this.ruleChecksum = ruleChecksum;
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

//	public String getCreateDate() {
//		return createDate;
//	}
//
//	public void setCreateDate(String createDate) {
//		this.createDate = createDate;
//	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
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

	public SecurityRule(String projectId, String ruleChecksum, String ruleID, String ruleText, String file,
			String modifiedDate, String status, String statusDate, String test, String description) {
		super();
		this.projectId = projectId;
		this.ruleChecksum = ruleChecksum;
		this.ruleID = ruleID;
		this.ruleText = ruleText;
		this.file = file;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.statusDate = statusDate;
		this.test = test;
		this.description = description;
	}

	public SecurityRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
