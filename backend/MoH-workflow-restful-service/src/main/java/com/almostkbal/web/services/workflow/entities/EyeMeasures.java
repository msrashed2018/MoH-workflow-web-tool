package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eye_measures")
public class EyeMeasures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEASURE_ID")
	private int id;
	
	@Column(name = "MEASURE_TITLE")
	private String title;
	
	@Column(name = "MEASURE_DESCRIPTION")
	private String description;
	public EyeMeasures() {
		
	}
	public EyeMeasures(String measureTitle, String measureDescription) {
		this.title = measureTitle;
		this.description = measureDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

