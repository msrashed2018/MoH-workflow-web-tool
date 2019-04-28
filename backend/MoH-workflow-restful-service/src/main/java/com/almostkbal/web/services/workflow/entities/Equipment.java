package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="course")
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EQUIPMENT_ID")
	private int id;
	
	@Column(name = "EQUIPMENT_NAME")
	private String equipmentName;
	
	@Column(name = "DISABILITY_ID")
	private Disability disability;
	
	@Column(name = "EQUIPMENT_DESCRIPTION")
	private String equipmentDescription;
	
	public Equipment() {
		
	}

	public Equipment(String equipmentName, String equipmentDescription) {
		super();
		this.equipmentName = equipmentName;
		this.equipmentDescription = equipmentDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Disability getDisability() {
		return disability;
	}

	public void setDisability(Disability disability) {
		this.disability = disability;
	}

	public String getEquipmentDescription() {
		return equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}
	
}
