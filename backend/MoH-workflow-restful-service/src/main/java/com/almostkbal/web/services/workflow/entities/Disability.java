package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="disability")
public class Disability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disability_id")
	private int id;
	
	@Column(name = "disability_name")
	private String disabilityName;
	
	@Column(name = "disability_description")
	private String disabilityDescription;
	
	public Disability() {
		
	}

	public Disability(String disabilityName, String disabilityDescription) {
		super();
		this.disabilityName = disabilityName;
		this.disabilityDescription = disabilityDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisabilityName() {
		return disabilityName;
	}

	public void setDisabilityName(String disabilityName) {
		this.disabilityName = disabilityName;
	}

	public String getDisabilityDescription() {
		return disabilityDescription;
	}

	public void setDisabilityDescription(String disabilityDescription) {
		this.disabilityDescription = disabilityDescription;
	}
	
}
