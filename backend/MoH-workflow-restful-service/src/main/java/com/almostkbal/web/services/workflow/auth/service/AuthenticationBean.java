package com.almostkbal.web.services.workflow.auth.service;

import java.util.List;

public class AuthenticationBean {
	private List<String> authorities;
	private String message;

	public AuthenticationBean(String message,List<String> authorities) {
		this.message = message;
		this.authorities = authorities;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return String.format("AuthenticationBean [message=%s]", message);
	}

}
