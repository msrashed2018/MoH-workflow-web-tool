package com.almostkbal.web.services.workflow.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="citizen")
public class Citizen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_CITIZEN")
	@Column(name = "citizen_id")
	private long id;
	
	@Column(name = "national_id")
	private long nationalId;
	
	@Column(name = "citizen_name")
	private String name;
	
	@Column(name = "birth_date")
//	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(name = "address")
	private String address;
	
	@OneToOne ()
	@JoinColumn(name = "gender_id")
	private Gender gender;
	
	@OneToOne 
	@JoinColumn(name = "city_id")
	private City city;

	@OneToOne
	@JoinColumn(name = "governate_id")
	private Governate governate;
	
	@OneToOne
	@JoinColumn(name = "occupation_id")
	private Occupation occupation;
	
	@Column(name = "mobile_no")
	private String mobileNumber;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modifiedDate;
	
	public Citizen() {
		
	}

	public Citizen(long nationalId, String name, Date birthDate, String address, String mobileNumber, String createdBy,
			Date createdDate) {
		super();
		this.nationalId = nationalId;
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNationalId() {
		return nationalId;
	}

	public void setNationalId(long nationalId) {
		this.nationalId = nationalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Governate getGovernate() {
		return governate;
	}

	public void setGovernate(Governate governate) {
		this.governate = governate;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
