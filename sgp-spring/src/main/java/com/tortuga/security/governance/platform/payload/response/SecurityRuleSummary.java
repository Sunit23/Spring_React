package com.tortuga.security.governance.platform.payload.response;

public class SecurityRuleSummary {

	int failed;
	int outOfDate;
	int passed;
	int total;
	
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
	public int getOutOfDate() {
		return outOfDate;
	}
	public void setOutOfDate(int outOfDate) {
		this.outOfDate = outOfDate;
	}
	public int getPassed() {
		return passed;
	}
	public void setPassed(int passed) {
		this.passed = passed;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public SecurityRuleSummary(int failed, int outOfDate, int passed, int total) {
		super();
		this.failed = failed;
		this.outOfDate = outOfDate;
		this.passed = passed;
		this.total = total;
	}
	public SecurityRuleSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
