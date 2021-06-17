package com.tortuga.security.governance.platform.payload.response;

import java.util.ArrayList;
import java.util.List;

public class SecurityVerifyRequirementDao {

//	String projectId;
	String requirementName;
	String description;
	String asset;
	String objective;
	String boundary;
	String owner;
	List<String> workspaces = new ArrayList<String>();
	List<String> securityRules = new ArrayList<String>();
	List<String> cwes = new ArrayList<>();
	List<SecurityRuleToCWE> linkedSecurityRuleToCWE = new ArrayList<>();
	
	
//	public String getProjectId() {
//		return projectId;
//	}
//	public void setProjectId(String projectId) {
//		this.projectId = projectId;
//	}
	public List<String> getcwes() {
		return cwes;
	}
	public void setcwes(List<String> cwes) {
		this.cwes = cwes;
	}
	public String getRequirementName() {
		return requirementName;
	}
	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getBoundary() {
		return boundary;
	}
	public void setBoundary(String boundary) {
		this.boundary = boundary;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<String> getWorkspaces() {
		return workspaces;
	}
	public void setWorkspaces(List<String> workspaces) {
		this.workspaces = workspaces;
	}
	public List<String> getSecurityRules() {
		return securityRules;
	}
	public void setSecurityRules(List<String> securityRules) {
		this.securityRules = securityRules;
	}

	public List<SecurityRuleToCWE> getLinkedSecurityRuleToCWE() {
		return linkedSecurityRuleToCWE;
	}
	public void setLinkedSecurityRuleToCWE(List<SecurityRuleToCWE> linkedSecurityRuleToCWE) {
		this.linkedSecurityRuleToCWE = linkedSecurityRuleToCWE;
	}
	public SecurityVerifyRequirementDao(String requirementName, String description, String asset, String objective,
			String boundary, String owner, List<String> workspaces, List<String> securityRules, List<String> cwes,
			List<SecurityRuleToCWE> linkedSecurityRuleToCWE) {
		super();
		this.requirementName = requirementName;
		this.description = description;
		this.asset = asset;
		this.objective = objective;
		this.boundary = boundary;
		this.owner = owner;
		this.workspaces = workspaces;
		this.securityRules = securityRules;
		this.cwes = cwes;
		this.linkedSecurityRuleToCWE = linkedSecurityRuleToCWE;
	}
	public SecurityVerifyRequirementDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
