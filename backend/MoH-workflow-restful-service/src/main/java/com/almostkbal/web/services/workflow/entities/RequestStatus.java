package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="request_status")
public class RequestStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQUEST_STATUS_ID")
	private int id;
	
	@Column(name = "REQUEST_STATUS_NAME")
	private String requestStatusName;
	
	@Column(name = "REQUEST_STATUS_DESCRIPTION")
	private String requestStatusDescription;
	
	public RequestStatus() {
		
	}

	public RequestStatus(String requestStatusName) {
		super();
		this.requestStatusName = requestStatusName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequestStatusName() {
		return requestStatusName;
	}

	public void setRequestStatusName(String requestStatusName) {
		this.requestStatusName = requestStatusName;
	}

	public String getRequestStatusDescription() {
		return requestStatusDescription;
	}

	public void setRequestStatusDescription(String requestStatusDescription) {
		this.requestStatusDescription = requestStatusDescription;
	}
	
}
