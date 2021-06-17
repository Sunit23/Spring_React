package com.tortuga.security.governance.platform.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//securityrequirements
@Document("securityrequirements")
public class SecurityRequirements {
	
	@Id
	public String id;
	
	@Field("projectId")
	private String projectId;
	
	@Field("Description")
	private String description;
	
	@Field("Owner")
	private String owner;
	
	@Field("Asset")
	private String asset;

	@Field("SecurityBoundary")
	private String securityBoundary;

	@Field("SecurityObjective")
	private String securityObjective;
	
	@Field("Status")
	private String status;

	@Field("Deadline")
	private String deadline;
	
	@Field("HWID")
	private String HWID;
	
	@Field("CWEID")
	private String CWEID;

	@Field("RuleID")
	private String ruleID;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}
	
	public String getSecurityBoundary() {
		return securityBoundary;
	}

	public void setSecurityBoundary(String securityBoundary) {
		this.securityBoundary = securityBoundary;
	}
	
	public String getSecurityObjective() {
		return securityObjective;
	}

	public void setSecurityObjective(String securityObjective) {
		this.securityObjective = securityObjective;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public String getHWID() {
		return HWID;
	}

	public void setHWID(String HWID) {
		this.HWID = HWID;
	}

	public String getCWEID() {
		return CWEID;
	}

	public void setCWEID(String CWEID) {
		this.CWEID = CWEID;
	}
	
	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
}
