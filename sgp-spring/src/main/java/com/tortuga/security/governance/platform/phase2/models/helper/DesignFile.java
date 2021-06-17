package com.tortuga.security.governance.platform.phase2.models.helper;

import java.util.ArrayList;
import java.util.List;

public class DesignFile {
	
	private String checksum;
	private List<String> childFiles = new ArrayList<>();
    private String modified;
    private String path;
	
    
	public List<String> getChildFiles() {
		return childFiles;
	}
	public void setChildFiles(List<String> childFiles) {
		this.childFiles = childFiles;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
    
    

}
