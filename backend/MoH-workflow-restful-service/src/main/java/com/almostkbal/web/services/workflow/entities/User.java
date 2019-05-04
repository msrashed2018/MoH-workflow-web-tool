package com.almostkbal.web.services.workflow.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="system_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_USER")
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne
	@JoinColumn(name = "zone_id")
	private Zone zone;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="system_user_roles",
			joinColumns=@JoinColumn(name="users_user_id"),
			inverseJoinColumns=@JoinColumn(name="roles_role_id")
			)
    private List<Role> roles;
	
	public User() {
		
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
