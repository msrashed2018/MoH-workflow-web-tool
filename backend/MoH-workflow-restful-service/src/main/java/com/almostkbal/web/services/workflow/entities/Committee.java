package com.almostkbal.web.services.workflow.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="committee")
public class Committee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "committee_id")
	private long id;
	
	@Column(name = "committee_name")
	private String committeeName;
	
	@Column(name = "committee_date")
	private Date committeeDate;
	
	@Column(name = "member_one_id")
	private int memberOneId;
	
	@Column(name = "member_two_id")
	private int memberTwoId;
	
	@Column(name = "member_three_id")
	private int memberThreeId;
	
	@Column(name = "member_four_id")
	private int memberFourId;
	
	@Column(name = "member_five_id")
	private int memberFiveId;
	
	
	public Committee() {
		
	}


	public Committee(String committeeName, Date committeeDate) {
		super();
		this.committeeName = committeeName;
		this.committeeDate = committeeDate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCommitteeName() {
		return committeeName;
	}


	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}


	public Date getCommitteeDate() {
		return committeeDate;
	}


	public void setCommitteeDate(Date committeeDate) {
		this.committeeDate = committeeDate;
	}


	public int getMemberOneId() {
		return memberOneId;
	}


	public void setMemberOneId(int memberOneId) {
		this.memberOneId = memberOneId;
	}


	public int getMemberTwoId() {
		return memberTwoId;
	}


	public void setMemberTwoId(int memberTwoId) {
		this.memberTwoId = memberTwoId;
	}


	public int getMemberThreeId() {
		return memberThreeId;
	}


	public void setMemberThreeId(int memberThreeId) {
		this.memberThreeId = memberThreeId;
	}


	public int getMemberFourId() {
		return memberFourId;
	}


	public void setMemberFourId(int memberFourId) {
		this.memberFourId = memberFourId;
	}


	public int getMemberFiveId() {
		return memberFiveId;
	}


	public void setMemberFiveId(int memberFiveId) {
		this.memberFiveId = memberFiveId;
	}
	
	
}
