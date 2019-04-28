package com.almostkbal.web.services.workflow.auth.service;

import com.almostkbal.web.services.workflow.entities.User;

public interface UserService {
	
	void save(User user);

	User findByUsername(String username);
}
