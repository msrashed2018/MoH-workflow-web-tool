package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="traffic_management")
public class TrafficManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAFFIC_ID")
	private int id;
	
	@Column(name = "TRAFFIC_NAME")
	private String trafficName;
	
	@Column(name = "GOVERNATE_ID")
	private Governate governate;
	
	public TrafficManagement() {
		
	}

	public TrafficManagement(String trafficName) {
		super();
		this.trafficName = trafficName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrafficName() {
		return trafficName;
	}

	public void setTrafficName(String trafficName) {
		this.trafficName = trafficName;
	}

	public Governate getGovernate() {
		return governate;
	}

	public void setGovernate(Governate governate) {
		this.governate = governate;
	}
}

