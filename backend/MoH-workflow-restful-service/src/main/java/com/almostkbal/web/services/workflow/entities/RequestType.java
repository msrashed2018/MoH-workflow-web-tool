package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="request_type")
public class RequestType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQUEST_TYPE_ID")
	private int id;
	
	@Column(name = "REQUEST_TYPE_NAME")
	private String requestTypeName;
	
	@Column(name = "REQUEST_PRICE")
	private int requestPrice;
	
	public RequestType() {
		
	}

	public RequestType(String requestTypeName, int requestPrice) {
		super();
		this.requestTypeName = requestTypeName;
		this.requestPrice = requestPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequestTypeName() {
		return requestTypeName;
	}

	public void setRequestTypeName(String requestTypeName) {
		this.requestTypeName = requestTypeName;
	}

	public int getRequestPrice() {
		return requestPrice;
	}

	public void setRequestPrice(int requestPrice) {
		this.requestPrice = requestPrice;
	}
}
