package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="document_type")
public class DocumentType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_DOCUMENT_TYPE")
	@Column(name = "document_type_id")
	private int id;
	
	@Column(name = "document_type_name",nullable=false)
	private String type;
	
	@Column(name = "document_type_description")
	private String description;
	
	public DocumentType() {
		
	}

	public DocumentType(String name) {
		this.type = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
