package com.almostkbal.web.services.workflow.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_REQUEST")
	@Column(name = "request_id")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "citizen_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Citizen citizen;

	@Column(name = "request_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date requestDate;

	@OneToOne(optional = false)
	@JoinColumn(name = "request_type_id", nullable = false)
	@NotNull
	private RequestType requestType;
	
	
	@OneToOne
	@JoinColumn(name = "custom_id")
	private Custom custom;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date modifiedDate;

	@OneToOne
	@JoinColumn(name = "request_status_id")
	private RequestStatus requestStatus;

	@OneToOne
	@JoinColumn(name = "traffic_management_id")
	private TrafficManagement trafficManagement;

	@Enumerated(EnumType.STRING)
    private RequestState state;
	
//	@OneToMany(mappedBy = "request",fetch=FetchType.LAZY)
//	@JsonIgnore
//	private List<RequestDocument> documents;

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

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RequestState getState() {
		return state;
	}

	public void setState(RequestState state) {
		this.state = state;
	}

}
