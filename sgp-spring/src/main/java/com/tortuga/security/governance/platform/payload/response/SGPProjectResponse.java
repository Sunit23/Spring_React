package com.tortuga.security.governance.platform.payload.response;

import java.util.List;

public class SGPProjectResponse {
	
	private String id;
	private List<SecurityRequirementsResponse> securityRequirements;
	private String projectName;
	private DesignRevisionResponse design;
	private List<SecurityRulesDao> securityRules;
	private List<TestsDao> tests;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<SecurityRequirementsResponse> getSecurityRequirements() {
		return securityRequirements;
	}
	public void setSecurityRequirements(List<SecurityRequirementsResponse> securityRequirements) {
		this.securityRequirements = securityRequirements;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public DesignRevisionResponse getDesign() {
		return design;
	}
	public void setDesign(DesignRevisionResponse design) {
		this.design = design;
	}
	public List<SecurityRulesDao> getSecurityRules() {
		return securityRules;
	}
	public void setSecurityRules(List<SecurityRulesDao> securityRules) {
		this.securityRules = securityRules;
	}
	public List<TestsDao> getTests() {
		return tests;
	}
	public void setTests(List<TestsDao> tests) {
		this.tests = tests;
	}
	public SGPProjectResponse(String id, List<SecurityRequirementsResponse> securityRequirements, String projectName,
			DesignRevisionResponse design, List<SecurityRulesDao> securityRules, List<TestsDao> tests) {
		super();
		this.id = id;
		this.securityRequirements = securityRequirements;
		this.projectName = projectName;
		this.design = design;
		this.securityRules = securityRules;
		this.tests = tests;
	}
	public SGPProjectResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
