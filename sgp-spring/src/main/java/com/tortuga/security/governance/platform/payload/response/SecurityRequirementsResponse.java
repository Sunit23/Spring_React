package com.tortuga.security.governance.platform.payload.response;

public class SecurityRequirementsResponse {
	String id;
	String status;
	String date;
	
	
	public SecurityRequirementsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SecurityRequirementsResponse(String id, String status, String date) {
		super();
		this.id = id;
		this.status = status;
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
