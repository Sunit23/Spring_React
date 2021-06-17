package com.tortuga.security.governance.platform.payload.response;

import java.util.List;

import com.tortuga.security.governance.platform.phase2.models.ProjectCore;
import com.tortuga.security.governance.platform.phase2.models.SimCore;

public class SecurityRuleHistoryResponse {
	
	List<ProjectCoreDao> projectCore;
	
	List<SimCoreDao> simCore;

	public List<ProjectCoreDao> getProjectCore() {
		return projectCore;
	}

	public void setProjectCore(List<ProjectCoreDao> projectCore2) {
		this.projectCore = projectCore2;
	}

	public List<SimCoreDao> getSimCore() {
		return simCore;
	}

	public void setSimCore(List<SimCoreDao> simCore) {
		this.simCore = simCore;
	}

}
