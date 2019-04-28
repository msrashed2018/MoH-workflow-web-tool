package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="committee_member")
public class CommitteeMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "committee_member_id")
	private int id;
	
	@Column(name = "member_name")
	private String memberName;
	
	@Column(name = "member_title")
	private String memberTitle;
	
	@Column(name = "member_type")
	private String memberType;
	
	public CommitteeMember() {
		
	}

	public CommitteeMember(String memberName, String memberTitle, String memberType) {
		super();
		this.memberName = memberName;
		this.memberTitle = memberTitle;
		this.memberType = memberType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberTitle() {
		return memberTitle;
	}

	public void setMemberTitle(String memberTitle) {
		this.memberTitle = memberTitle;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	
}
