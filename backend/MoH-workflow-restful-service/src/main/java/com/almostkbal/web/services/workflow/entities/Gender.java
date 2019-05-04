package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gender")
public class Gender {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_GENDER")
	@Column(name = "gender_id")
	private int id;
	
	@Column(name = "gender_name",nullable=false)
	private String name;
	
	public Gender() {
		
	}

	public Gender(String name) {
		super();
		this.name = name;
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

	
}
