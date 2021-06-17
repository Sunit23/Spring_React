package com.tortuga.security.governance.platform.phase2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("test")
public class Test {

	@Id
	private String id;
	
	@Field("Test Checksum")
	private String testChecksum;
	
	@Field("Name")
	private String name;
	
	@Field("Start Time")
	private String startTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestChecksum() {
		return testChecksum;
	}

	public void setTestChecksum(String testChecksum) {
		this.testChecksum = testChecksum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
	
	
}
