package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="document_type")
public class FileType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_DOCUMENT_TYPE")
	@Column(name = "document_type_id")
	private long id;
	
	@Column(name = "document_type_name", nullable = false, unique = true)
//	@NotBlank(message = "لابد من ادخال نوع الملف")
	private String type;
	
	@Column(name = "document_type_description")
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
