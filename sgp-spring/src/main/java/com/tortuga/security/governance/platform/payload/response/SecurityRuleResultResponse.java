package com.tortuga.security.governance.platform.payload.response;

public class SecurityRuleResultResponse {

	String checksum;
	String result;
	String resultDate;
	String test;
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public SecurityRuleResultResponse(String checksum, String result, String resultDate, String test) {
		super();
		this.checksum = checksum;
		this.result = result;
		this.resultDate = resultDate;
		this.test = test;
	}
	public SecurityRuleResultResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
