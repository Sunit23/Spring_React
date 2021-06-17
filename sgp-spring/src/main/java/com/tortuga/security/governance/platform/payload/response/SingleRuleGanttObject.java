package com.tortuga.security.governance.platform.payload.response;

import java.util.ArrayList;
import java.util.List;

public class SingleRuleGanttObject {

	private RuleHistoryStartToEndData ruleHistoryStartAndEndDate;
	private List<SecurityRuleRevisionHistoryResponseDao> securityRuleHistories = new ArrayList<>();
	public RuleHistoryStartToEndData getRuleHistoryStartAndEndDate() {
		return ruleHistoryStartAndEndDate;
	}
	public void setRuleHistoryStartAndEndDate(RuleHistoryStartToEndData ruleHistoryStartAndEndDate) {
		this.ruleHistoryStartAndEndDate = ruleHistoryStartAndEndDate;
	}
	public List<SecurityRuleRevisionHistoryResponseDao> getSecurityRuleHistories() {
		return securityRuleHistories;
	}
	public void setSecurityRuleHistories(List<SecurityRuleRevisionHistoryResponseDao> securityRuleHistories) {
		this.securityRuleHistories = securityRuleHistories;
	}
	
	
}
