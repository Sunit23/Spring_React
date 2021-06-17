package com.tortuga.security.governance.platform.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("project")
public class Project {
	@Id
	public String id;
	public String name;
	
	public String owner;
	
	public String deadline;
	
	public String projectId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	


	/*
	 * public Project(String id, String name, String owner, String deadline, String
	 * id2) { super(); this.id = id; this.Name = name; this.Owner = owner;
	 * this.Deadline = deadline; this.Id = id2; }
	 */

	
	
	
	
}
