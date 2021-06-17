package com.tortuga.security.governance.platform.payload.response;

import java.util.ArrayList;
import java.util.List;

public class RuleRevision {

	String initialModifiedDate;
	String initialStatus;
	
	List<StatusChanges> statusChanges = new ArrayList<>();
	
	String modificationEndDate;

	public String getInitialModifiedDate() {
		return initialModifiedDate;
	}

	public void setInitialModifiedDate(String initialModifiedDate) {
		this.initialModifiedDate = initialModifiedDate;
	}

	public String getInitialStatus() {
		return initialStatus;
	}

	public void setInitialStatus(String initialStatus) {
		this.initialStatus = initialStatus;
	}

	public List<StatusChanges> getStatusChanges() {
		return statusChanges;
	}

	public void setStatusChanges(List<StatusChanges> statusChanges) {
		this.statusChanges = statusChanges;
	}

	public String getModificationEndDate() {
		return modificationEndDate;
	}

	public void setModificationEndDate(String modificationEndDate) {
		this.modificationEndDate = modificationEndDate;
	}
	
	
}
