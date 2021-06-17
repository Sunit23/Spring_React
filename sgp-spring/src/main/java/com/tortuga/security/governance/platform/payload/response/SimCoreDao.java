package com.tortuga.security.governance.platform.payload.response;

public class SimCoreDao {

	String ruleChecksum;
	String modifiedDate;
	String status;
	String statusDate;
	String test;
	
	public String getRuleChecksum() {
		return ruleChecksum;
	}
	public void setRuleChecksum(String ruleChecksum) {
		this.ruleChecksum = ruleChecksum;
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
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public SimCoreDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SimCoreDao(String ruleChecksum, String modifiedDate, String status, String statusDate, String test) {
		super();
		this.ruleChecksum = ruleChecksum;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.statusDate = statusDate;
		this.test = test;
	}
	
	
}
