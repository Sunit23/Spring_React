package com.tortuga.security.governance.platform.payload.response;

import java.util.List;

import com.tortuga.security.governance.platform.models.HardwareDesign;
import com.tortuga.security.governance.platform.models.ProjectRevision;
import com.tortuga.security.governance.platform.models.SecurityRequirements;


public class Nodes {

	List<ProjectRevision> projectRevision;
	
	List<HardwareDesign> hardwareDesign;
	
	
	List<SecurityRequirements> securityRequirements;
	

	public List<ProjectRevision> getProjectRevision() {
		return projectRevision;
	}

	public void setProjectRevision(List<ProjectRevision> projectRevision) {
		this.projectRevision = projectRevision;
	}

	public List<HardwareDesign> getHardwareDesign() {
		return hardwareDesign;
	}

	public void setHardwareDesign(List<HardwareDesign> hardwareDesign) {
		this.hardwareDesign = hardwareDesign;
	}

	public List<SecurityRequirements> getSecurityRequirements() {
		return securityRequirements;
	}
	
	public void setSecurityRequirements(List<SecurityRequirements> securityrequirements) {
		this.securityRequirements = securityrequirements;
	}
	
	
}
