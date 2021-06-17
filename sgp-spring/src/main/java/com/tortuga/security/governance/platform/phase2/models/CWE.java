package com.tortuga.security.governance.platform.phase2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("CWE")
public class CWE {

	
	@Id
	private String id;
	
	@Field("ID")
	private String idCwe;
	
	@Field("Type")
	private String type;
	
	@Field("Description")
	private String description;
	
	@Field("Link")
	private String link;
	
	@Field("Version Spec")
	private double versionSpec;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCwe() {
		return idCwe;
	}

	public void setIdCwe(String idCwe) {
		this.idCwe = idCwe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public double getVersionSpec() {
		return versionSpec;
	}

	public void setVersionSpec(double versionSpec) {
		this.versionSpec = versionSpec;
	}
	
	
	
}
