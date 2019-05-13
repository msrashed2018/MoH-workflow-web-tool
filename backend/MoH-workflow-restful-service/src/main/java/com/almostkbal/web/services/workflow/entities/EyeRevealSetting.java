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
@Table(name="eye_reveal_setting")
public class EyeRevealSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_OCCUPATION")
	@Column(name = "setting_id")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "right_measure_id")
	private EyeMeasure rightMeasure;
	
	
	@OneToOne
	@JoinColumn(name = "left_measure_id")
	private EyeMeasure leftMeasure;
	
	@Column(name = "use_glasses")
	private byte useGlasses;
	
	@Column(name = "DISTINGUISH_COLOR")
	private byte distinguishColor;
	
	@Column(name = "rsetting_esult")
	private String result;
	
	@Column(name = "setting_description")
	private String description;
	
	public EyeRevealSetting() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EyeMeasure getRightMeasure() {
		return rightMeasure;
	}

	public void setRightMeasure(EyeMeasure rightMeasure) {
		this.rightMeasure = rightMeasure;
	}

	public EyeMeasure getLeftMeasure() {
		return leftMeasure;
	}

	public void setLeftMeasure(EyeMeasure leftMeasure) {
		this.leftMeasure = leftMeasure;
	}

	public byte getUseGlasses() {
		return useGlasses;
	}

	public void setUseGlasses(byte useGlasses) {
		this.useGlasses = useGlasses;
	}

	public byte getDistinguishColor() {
		return distinguishColor;
	}

	public void setDistinguishColor(byte distinguishColor) {
		this.distinguishColor = distinguishColor;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}