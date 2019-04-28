package com.almostkbal.web.services.workflow.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="request_history")
public class RequestHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQUEST_HISTORY_ID")
	private int id;
	
	@Column(name = "REQUEST_ID")
	private Request request;
	
	@Column(name = "ACTION_TIMESTAMP")
	private Date actionTimestamp;


	@Column(name = "ACTION_TAKEN")
	private Request actionTaken;
	
	@Column(name = "ACTION_TAKEN_BY")
	private Request actionTakenBy;
	
	@Column(name = "USER_COMMENT")
	private Request userComment;
	
	public RequestHistory() {
		
	}

	public RequestHistory(Request request, Request actionTaken, Request actionTakenBy) {
		super();
		this.request = request;
		this.actionTaken = actionTaken;
		this.actionTakenBy = actionTakenBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Date getActionTimestamp() {
		return actionTimestamp;
	}

	public void setActionTimestamp(Date actionTimestamp) {
		this.actionTimestamp = actionTimestamp;
	}

	public Request getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(Request actionTaken) {
		this.actionTaken = actionTaken;
	}

	public Request getActionTakenBy() {
		return actionTakenBy;
	}

	public void setActionTakenBy(Request actionTakenBy) {
		this.actionTakenBy = actionTakenBy;
	}

	public Request getUserComment() {
		return userComment;
	}

	public void setUserComment(Request userComment) {
		this.userComment = userComment;
	}
	
}

