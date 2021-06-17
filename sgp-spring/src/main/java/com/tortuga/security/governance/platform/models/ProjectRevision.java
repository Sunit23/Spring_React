package com.tortuga.security.governance.platform.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("projectrevisions")
public class ProjectRevision {

	@Id
	public String id;
	
	private String Description;
	
	private String Owner;
	
	private String Date;
	
	private String Checksum;
	
	private int version;
	
	private String ProjectId;
	
	@Field("Design Checksum")
	private String DesignChecksum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getChecksum() {
		return Checksum;
	}

	public void setChecksum(String checksum) {
		Checksum = checksum;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getDesignChecksum() {
		return DesignChecksum;
	}

	public void setDesignChecksum(String designChecksum) {
		DesignChecksum = designChecksum;
	}
	
	
	
}
