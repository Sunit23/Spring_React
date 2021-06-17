package com.tortuga.security.governance.platform.payload.response;

public class TestsDao {

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
	public TestsDao(String id, String date) {
		super();
		this.id = id;
		this.date = date;
	}
	public TestsDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
