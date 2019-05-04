package com.almostkbal.web.services.workflow.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.auth.service.AuthenticationBean;
import com.almostkbal.web.services.workflow.auth.service.SecurityServiceImpl;
import com.almostkbal.web.services.workflow.entities.Role;
import com.almostkbal.web.services.workflow.entities.User;
import com.almostkbal.web.services.workflow.repositories.UserRepository;

//Controller
@CrossOrigin("http://localhost:4200")
@RestController
public class AuthenticationController {
	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@GetMapping(path = "/api/authenticate")
	public AuthenticationBean authenticate(Authentication authentication) {
		logger.info("/api/authenticate generating AuthenticationBean ");
		List<String> authorities = new ArrayList<String>();
		User user = userRepository.findByUsername(authentication.getName());
		if (user == null)
			throw new UsernameNotFoundException(authentication.getName());
		for (Role role : user.getRoles()) {
			authorities.add(role.getName());
		}
		return new AuthenticationBean("You are authenticated successfully.",authorities);
	}

}
