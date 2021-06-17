package com.tortuga.security.governance.platform.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pie-chart")
public class PieChart {

	 @Id
	  private String id;

	  @NotBlank
	  @Size(max = 100)
	  private String title;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String color;
	  
	  private int value;
	  
	  

	public PieChart(@NotBlank @Size(max = 100) String title, @NotBlank @Size(max = 50) @Email String color,
			 int value) {
		super();
		this.title = title;
		this.color = color;
		this.value = value;
	}

	@Override
	public String toString() {
		return "PieChart [id=" + id + ", title=" + title + ", color=" + color + ", value=" + value + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
}
