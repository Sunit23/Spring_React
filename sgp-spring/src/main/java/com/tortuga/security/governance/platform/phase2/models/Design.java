package com.tortuga.security.governance.platform.phase2.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("design")
public class Design {
	
	@Id
	private String id;
	
	@Field("Design Checksum")
	private String designChecksum;	
	
	@Field("RTL Files")
	private ArrayList<String> rTLFiles = new ArrayList<>(); ;
	
	@Field("Created")
	private String created;
	
	@Field("Modified Date")
	private String modifiedDate;
	
	@Field("Last Run Date")
	private String lastRunDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesignChecksum() {
		return designChecksum;
	}

	public void setDesignChecksum(String designChecksum) {
		this.designChecksum = designChecksum;
	}

	public ArrayList<String> getrTLFiles() {
		return rTLFiles;
	}

	public void setrTLFiles(ArrayList<String> rTLFiles) {
		this.rTLFiles = rTLFiles;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getLastRunDate() {
		return lastRunDate;
	}

	public void setLastRunDate(String lastRunDate) {
		this.lastRunDate = lastRunDate;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Field("Test")
	private String test;
	
	@Field("Description")
	private String description;
	
	

}
