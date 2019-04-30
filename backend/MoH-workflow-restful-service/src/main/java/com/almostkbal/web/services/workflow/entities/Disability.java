package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="disability")
public class Disability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disability_id")
	private int id;
	
	@Column(name = "disability_name")
	private String name;
	
	@Column(name = "disability_description")
	private String description;
	
	public Disability() {
		
	}

	public Disability(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
