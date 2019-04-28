package com.almostkbal.web.services.workflow.auth.service;

public interface SecurityService {
	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
