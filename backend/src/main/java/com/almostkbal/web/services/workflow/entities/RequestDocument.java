package com.almostkbal.web.services.workflow.entities;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "request_document")
public class RequestDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_REQUEST_DOCUMENT")
	@Column(name = "REQUEST_DOCUMENT_ID")
	private long id;
	
	@Column(name = "name",nullable=false)
	@NotNull
	private String name;

	@Enumerated(EnumType.STRING)
	@NotNull
	private DocumentType type;

	@Column(name = "path", nullable = false)
	@NotNull
	private String path;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "request_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Request request;
	
	public RequestDocument() {

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	
}