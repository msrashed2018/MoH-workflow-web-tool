package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="eye_measures")
public class EyeMeasures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEASURE_ID")
	private int id;
	
	@Column(name = "MEASURE_TITLE")
	private String measureTitle;
	
	@Column(name = "MEASURE_DESCRIPTION")
	private String measureDescription;
	public EyeMeasures() {
		
	}
	public EyeMeasures(String measureTitle, String measureDescription) {
		this.measureTitle = measureTitle;
		this.measureDescription = measureDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMeasureTitle() {
		return measureTitle;
	}
	public void setMeasureTitle(String measureTitle) {
		this.measureTitle = measureTitle;
	}
	public String getMeasureDescription() {
		return measureDescription;
	}
	public void setMeasureDescription(String measureDescription) {
		this.measureDescription = measureDescription;
	}
}

