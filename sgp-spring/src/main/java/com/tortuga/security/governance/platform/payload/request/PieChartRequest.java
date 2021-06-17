package com.tortuga.security.governance.platform.payload.request;

import javax.validation.constraints.NotBlank;

public class PieChartRequest {
	
	@NotBlank
	public String title;
	
	
	public int value;
	
	@NotBlank
	public String color;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
