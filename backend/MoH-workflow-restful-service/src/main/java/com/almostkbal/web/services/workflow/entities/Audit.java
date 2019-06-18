package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "audt")
public class Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_AUDIT")
	@Column(name = "audit_id")
	private int id;

	@Column(name = "action", nullable = false)
	@NotNull
	private String action;

	@Column(name = "details")
	private String details;

	@Column(name = "performed_by")
	private String performedBy;

	@Column(name = "timestamp")
	private long timestamp;

}
