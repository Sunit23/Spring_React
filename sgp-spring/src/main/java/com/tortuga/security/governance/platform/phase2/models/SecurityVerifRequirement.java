package com.tortuga.security.governance.platform.phase2.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.tortuga.security.governance.platform.payload.response.SecurityRuleToCWE;

@Document("security_verif_requirement")
public class SecurityVerifRequirement {
	
	@Id
	private String id;
	
	@Field("Requirement Name")
	private String requirementName;
	
	@Field("Description")
	private String description;
	
	@Field("Asset")
	private String asset;
	
	@Field("Objective")
	private String objective;
	
	@Field("Boundary")
	private String boundary;
	
	@Field("Owner")
	private String owner;

	@Field("Workspaces")
	private List<String> workspaces = new ArrayList<>();
	
	@Field("Security Rules")
	private List<String> ruleId = new ArrayList<>();
	
	@Field("CWEs")
	private List<String> cweId = new ArrayList<>();
	
	@Field("SecurityRuleToCWE")
	private List<SecurityRuleToCWE> ruleToCwe = new ArrayList<>();
	
	@Field("Created Date")
	private String createDate;
	
	@Field("Modified Date")
	private String modifiedDate;
	
	@Field("Status")
	private String status;
	
	@Field("Status Date")
	private String statusDate;
	
	

	
//	@Field("Project/Rule ID")
//	private String projectRuleID;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
//	public String getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(String projectId) {
//		this.projectId = projectId;
//	}

	public String getSecurityBoundary() {
		return boundary;
	}

	public void setSecurityBoundary(String securityBoundary) {
		this.boundary = securityBoundary;
	}

	public String getSecurityObjective() {
		return objective;
	}

	public void setSecurityObjective(String securityObjective) {
		this.objective = securityObjective;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public List<String> getCweId() {
		return cweId;
	}

	public void setCweId(ArrayList<String> cweId) {
		this.cweId = cweId;
	}

//	public String getProjectRuleID() {
//		return projectRuleID;
//	}
//
//	public void setProjectRuleID(String projectRuleID) {
//		this.projectRuleID = projectRuleID;
//	}

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

	public List<String> getRuleId() {
		return ruleId;
	}

	public void setRuleId(List<String> ruleId) {
		this.ruleId = ruleId;
	}

	public List<SecurityRuleToCWE> getRuleToCwe() {
		return ruleToCwe;
	}

	public void setRuleToCwe(List<SecurityRuleToCWE> ruleToCwe) {
		this.ruleToCwe = ruleToCwe;
	}

	public void setCweId(List<String> cweId) {
		this.cweId = cweId;
	}

	public SecurityVerifRequirement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SecurityVerifRequirement( String requirementName, String description, String asset,
			String objective, String boundary, String owner, List<String> workspaces, List<String> ruleId,
			List<String> cweId, List<SecurityRuleToCWE> ruleToCwe, String createDate, String modifiedDate) {
		super();
//		this.projectId = projectId;
		this.requirementName = requirementName;
		this.description = description;
		this.asset = asset;
		this.objective = objective;
		this.boundary = boundary;
		this.owner = owner;
		this.workspaces = workspaces;
		this.ruleId = ruleId;
		this.cweId = cweId;
		this.ruleToCwe = ruleToCwe;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
	
	

}
