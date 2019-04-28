package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "request_document")
public class RequestDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQUEST_DOCUMENT_ID")
	private int id;

	@Column(name = "REQUEST_ID")
	private Request request;

	@Column(name = "DCOUMENT_TYPE_ID")
	private DocumentType documentType;

	@Column(name = "DOCUMENT_PATH")
	private String documentPath;

	public RequestDocument() {

	}

	public RequestDocument(Request request, DocumentType documentType, String documentPath) {
		super();
		this.request = request;
		this.documentType = documentType;
		this.documentPath = documentPath;
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

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

}
