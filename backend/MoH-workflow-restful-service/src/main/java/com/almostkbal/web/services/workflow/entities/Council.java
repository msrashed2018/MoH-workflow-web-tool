package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="council")
public class Council {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "council_id")
	private int councilId;
	
	@Column(name = "council_name")
	private String councilName;
	
	@Column(name = "governate_id")
	private Governate governate;
	
	public Council(String councilName) {
		super();
		this.councilName = councilName;
	}

	public Council() {
		
	}

	public int getCouncilId() {
		return councilId;
	}

	public void setCouncilId(int councilId) {
		this.councilId = councilId;
	}

	public String getCouncilName() {
		return councilName;
	}

	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}

	public Governate getGovernate() {
		return governate;
	}

	public void setGovernate(Governate governate) {
		this.governate = governate;
	}
	
}
