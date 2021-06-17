package com.tortuga.security.governance.platform.payload.response;

import java.util.List;

public class ProjectResponse {
	
	private String key;
	private String label;
	private Nodes nodes;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Nodes getNodes() {
		return nodes;
	}
	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}
	

}
