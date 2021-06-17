package com.tortuga.security.governance.platform.phase2.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("sgp_project")
public class SGPProject {
	
	@Id
	private String id;
	
	@Field("Project Name")
	private String ProjectName;	
	
	private String Design;	
	
	@Field("Security Rules")
	private ArrayList<String> SecurityRules = new ArrayList<>(); ;
	
	private ArrayList<String> Tests  = new ArrayList<>();;
	
	@Field("Security Requirements")
	private ArrayList<String> SecurityRequirements = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getDesign() {
		return Design;
	}

	public void setDesign(String design) {
		Design = design;
	}

	public ArrayList<String> getSecurityRules() {
		return SecurityRules;
	}

	public void setSecurityRules(ArrayList<String> securityRules) {
		SecurityRules = securityRules;
	}

	public ArrayList<String> getTests() {
		return Tests;
	}

	public void setTests(ArrayList<String> tests) {
		Tests = tests;
	}

	public ArrayList<String> getSecurityRequirements() {
		return SecurityRequirements;
	}

	public void setSecurityRequirements(ArrayList<String> securityRequirements) {
		SecurityRequirements = securityRequirements;
	}
	
	


}
