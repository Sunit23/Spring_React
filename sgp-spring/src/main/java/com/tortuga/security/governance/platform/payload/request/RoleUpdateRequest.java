package com.tortuga.security.governance.platform.payload.request;

import javax.validation.constraints.NotBlank;

public class RoleUpdateRequest {

	@NotBlank
	String oldRoleName;

	@NotBlank
	String newRoleName;

	public String getOldRoleName() {
		return oldRoleName;
	}

	public void setOldRoleName(String oldRoleName) {
		this.oldRoleName = oldRoleName;
	}

	public String getNewRoleName() {
		return newRoleName;
	}

	public void setNewRoleName(String newRoleName) {
		this.newRoleName = newRoleName;
	}

	@Override
	public String toString() {
		return "RoleUpdateRequest [oldRoleName=" + oldRoleName + ", newRoleName=" + newRoleName + "]";
	}
	
	
	
}
