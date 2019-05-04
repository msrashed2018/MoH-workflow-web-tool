package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="request_detail")
public class RequestDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_REQUEST_DETAIL")
	@Column(name = "request_detail_id")
	private long id;
	
	@OneToOne(mappedBy = "requestDetail")
	private Request request;
	
	@Column(name = "right_eye")
	private String rightEye;
	
	@Column(name = "left_eye")
	private String leftEye;
	
	@Column(name = "use_glasses")
	private byte useGlasses;
	
	@Column(name = "DISTINGUISH_COLOR")
	private String distinguishColor;
	
	@Column(name = "FIELD_OF_SIGHT")
	private String fieldOfSight;
	
	@Column(name = "SQUINT")
	private String squint;
	
	@OneToOne
	@JoinColumn(name = "disability_id")
	private Disability disability;
	
	@Column(name = "REJECTION_REASON")
	private String rejectionReason;
	
	@Column(name = "ACCEPTANCE_REASON")
	private String acceptanceReason;
	
	public RequestDetail() {
		
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

	public String getRightEye() {
		return rightEye;
	}

	public void setRightEye(String rightEye) {
		this.rightEye = rightEye;
	}

	public String getLeftEye() {
		return leftEye;
	}

	public void setLeftEye(String leftEye) {
		this.leftEye = leftEye;
	}

	public byte getUseGlasses() {
		return useGlasses;
	}

	public void setUseGlasses(byte useGlasses) {
		this.useGlasses = useGlasses;
	}

	public String getDistinguishColor() {
		return distinguishColor;
	}

	public void setDistinguishColor(String distinguishColor) {
		this.distinguishColor = distinguishColor;
	}

	public String getFieldOfSight() {
		return fieldOfSight;
	}

	public void setFieldOfSight(String fieldOfSight) {
		this.fieldOfSight = fieldOfSight;
	}

	public String getSquint() {
		return squint;
	}

	public void setSquint(String squint) {
		this.squint = squint;
	}

	public Disability getDisability() {
		return disability;
	}

	public void setDisability(Disability disability) {
		this.disability = disability;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public String getAcceptanceReason() {
		return acceptanceReason;
	}

	public void setAcceptanceReason(String acceptanceReason) {
		this.acceptanceReason = acceptanceReason;
	}

	
}
