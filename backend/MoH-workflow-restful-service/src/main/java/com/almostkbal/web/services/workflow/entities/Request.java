package com.almostkbal.web.services.workflow.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private long id;
	
	@Column(name = "citizen_id")
	private Citizen citizen;
	
	@Column(name = "request_date")
	private Date requestDate;
	
	@Column(name = "request_type_id")
	private RequestType requestType;
	
	@Column(name = "request_price")
	private Price price;
	
	@Column(name = "receipt_serial_no")
	private String receiptSerialNumber;
	
	@Column(name = "custom_id")
	private Custom custom;
	
	@Column(name = "old_request_id")
	private long old_request_id;
	
	@Column(name = "payment_done")
	private byte paymentDone;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "committee_id")
	private Committee committee;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "request_status_id")
	private RequestStatus requestStatus;
	
	
	public Request() {
		
	}


	public Request(Citizen citizen, Date requestDate, Custom custom, Date createdDate) {
		this.citizen = citizen;
		this.requestDate = requestDate;
		this.custom = custom;
		this.createdDate = createdDate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Citizen getCitizen() {
		return citizen;
	}


	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}


	public Date getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}


	public RequestType getRequestType() {
		return requestType;
	}


	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}


	public Price getPrice() {
		return price;
	}


	public void setPrice(Price price) {
		this.price = price;
	}


	public String getReceiptSerialNumber() {
		return receiptSerialNumber;
	}


	public void setReceiptSerialNumber(String receiptSerialNumber) {
		this.receiptSerialNumber = receiptSerialNumber;
	}


	public Custom getCustom() {
		return custom;
	}


	public void setCustom(Custom custom) {
		this.custom = custom;
	}


	public long getOld_request_id() {
		return old_request_id;
	}


	public void setOld_request_id(long old_request_id) {
		this.old_request_id = old_request_id;
	}


	public byte getPaymentDone() {
		return paymentDone;
	}


	public void setPaymentDone(byte paymentDone) {
		this.paymentDone = paymentDone;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public Committee getCommittee() {
		return committee;
	}


	public void setCommittee(Committee committee) {
		this.committee = committee;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public RequestStatus getRequestStatus() {
		return requestStatus;
	}


	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
	
}
