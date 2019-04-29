package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;

@Entity
@Table(name="city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_CITY")
	@Column(name = "city_id")
	private int id;
	
	@Column(name = "city_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "governate_id")
	private Governate governate;
	
	public City() {
		
	}

	public City(String name) {
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
	public Governate getGovernate() {
		return governate;
	}

	public void setGovernate(Governate governate) {
		this.governate = governate;
	}
}
