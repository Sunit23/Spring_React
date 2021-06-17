package com.tortuga.security.governance.platform.payload.response;

import java.util.ArrayList;
import java.util.List;

public class SecurityRuleRevisionDao {

	private RuleHistoryStartToEndData ruleHistoryStartToEndData;
	
	private List<RuleRevision> ruleRevision = new ArrayList<>();

	public RuleHistoryStartToEndData getRuleHistoryStartToEndData() {
		return ruleHistoryStartToEndData;
	}

	public void setRuleHistoryStartToEndData(RuleHistoryStartToEndData ruleHistoryStartToEndData) {
		this.ruleHistoryStartToEndData = ruleHistoryStartToEndData;
	}

	public List<RuleRevision> getRuleRevision() {
		return ruleRevision;
	}

	public void setRuleRevision(List<RuleRevision> ruleRevision) {
		this.ruleRevision = ruleRevision;
	}
	
	

}
