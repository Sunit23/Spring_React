package com.tortuga.security.governance.platform.payload.response;

public class DesignRevisionResponse {
	String id;
	String date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public DesignRevisionResponse(String id, String date) {
		super();
		this.id = id;
		this.date = date;
	}
	public DesignRevisionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
