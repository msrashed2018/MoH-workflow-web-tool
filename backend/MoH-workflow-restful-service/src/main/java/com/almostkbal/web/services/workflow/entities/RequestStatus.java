package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="request_status")
public class RequestStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_REQUEST_STATUS")
	@Column(name = "REQUEST_STATUS_ID")
	private long id;
	
	@Column(name = "REQUEST_STATUS_NAME")
	private String name;
	
	@Column(name = "REQUEST_STATUS_DESCRIPTION")
	private String description;
	
	public RequestStatus() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
