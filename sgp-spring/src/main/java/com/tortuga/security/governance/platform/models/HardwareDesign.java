package com.tortuga.security.governance.platform.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("hardwaredesign")
public class HardwareDesign {

	@Id
	private String id;
	
	private String moduleName;
	
	private String instanceName;
	
	private String fullPath ;
	
	private String filePath;
	
	private String key;
	
	private ArrayList<String> childrenKey = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ArrayList<String> getChildrenKey() {
		return childrenKey;
	}

	public void setChildrenKey(ArrayList<String> childrenKey) {
		this.childrenKey = childrenKey;
	}
	 
	 
	
}
