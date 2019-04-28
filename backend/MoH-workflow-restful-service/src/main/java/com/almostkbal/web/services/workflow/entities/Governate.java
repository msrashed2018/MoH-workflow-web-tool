package com.almostkbal.web.services.workflow.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="governate")
public class Governate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_GOVERNATE")
	@Column(name = "governate_id")
	private int id;
	
	@Column(name = "governate_name")
	private String name;
	
	@OneToMany(mappedBy = "governate")
	private List<City> cities;
	
	public Governate() {
		
	}

	public Governate(String name) {
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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
