package com.tortuga.security.governance.platform.phase2.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tortuga.security.governance.platform.phase2.models.helper.RuleResult;

@Document(collection = "simCore")
public class SimCore {
	
	@Id
	private String _id;
	private String checksum;
	private String projectName;
	private String simStart;
    private String testSuite;
    private String test;
    private List<RuleResult> ruleResults;
    
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSimStart() {
		return simStart;
	}
	public void setSimStart(String simStart) {
		this.simStart = simStart;
	}
	public String getTestSuite() {
		return testSuite;
	}
	public void setTestSuite(String testSuite) {
		this.testSuite = testSuite;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public List<RuleResult> getRuleResults() {
		return ruleResults;
	}
	public void setRuleResults(List<RuleResult> ruleResults) {
		this.ruleResults = ruleResults;
	}
    
	
    
}
