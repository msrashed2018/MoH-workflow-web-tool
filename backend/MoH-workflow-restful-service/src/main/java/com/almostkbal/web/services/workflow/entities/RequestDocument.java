package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "request_document")
public class RequestDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_REQUEST_DOCUMENT")
	@Column(name = "REQUEST_DOCUMENT_ID")
	private long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "request_id")
	@JsonIgnore
	private Request request;

	@OneToOne
	@JoinColumn(name = "DCOUMENT_TYPE_ID")
	private DocumentType type;

	@Column(name = "DOCUMENT_PATH")
	private String path;

	public RequestDocument() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
