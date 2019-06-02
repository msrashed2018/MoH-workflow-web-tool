package com.almostkbal.web.services.workflow.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="request")
public class  Request{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_REQUEST")
	@Column(name = "request_id")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "citizen_id")
	private Citizen citizen;
	
	@Column(name = "request_date")
	private Date requestDate;
	
	@OneToOne
	@JoinColumn(name = "request_type_id")
	private RequestType requestType;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "request_payment_id")
	private RequestPayment payment;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "eye_reveal_id")
	private EyeReveal eyeReveal;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "bones_reveal_id")
	private BonesReveal bonesReveal;
	
	@OneToOne
	@JoinColumn(name = "custom_id")
	private Custom custom;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@OneToOne
	@JoinColumn(name = "request_status_id")
	private RequestStatus requestStatus;
	
	@OneToOne
	@JoinColumn(name = "traffic_management_id")
	private TrafficManagement trafficManagement;
	
	@OneToMany(mappedBy = "request",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<RequestDocument> documents;
	
	@Column(name = "description")
	private String description;
	
	
	public Request() {
		
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


	public EyeReveal getEyeReveal() {
		return eyeReveal;
	}


	public void setEyeReveal(EyeReveal eyeReveal) {
		this.eyeReveal = eyeReveal;
	}


	public BonesReveal getBonesReveal() {
		return bonesReveal;
	}


	public void setBonesReveal(BonesReveal bonesReveal) {
		this.bonesReveal = bonesReveal;
	}


	public Custom getCustom() {
		return custom;
	}


	public void setCustom(Custom custom) {
		this.custom = custom;
	}


	public RequestPayment getPayment() {
		return payment;
	}


	public void setPayment(RequestPayment payment) {
		this.payment = payment;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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


	public TrafficManagement getTrafficManagement() {
		return trafficManagement;
	}


	public void setTrafficManagement(TrafficManagement trafficManagement) {
		this.trafficManagement = trafficManagement;
	}


	public List<RequestDocument> getDocuments() {
		return documents;
	}


	public void setDocuments(List<RequestDocument> documents) {
		this.documents = documents;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
