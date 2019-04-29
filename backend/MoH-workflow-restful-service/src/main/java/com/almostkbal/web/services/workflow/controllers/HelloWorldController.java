package com.almostkbal.web.services.workflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.auth.service.AuthenticationBean;
import com.almostkbal.web.services.workflow.repositories.UserRepository;

//Controller
@CrossOrigin(origins="*")
@RestController
public class HelloWorldController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/test")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AuthenticationBean helloWorld(Authentication authentication) {
		System.out.println("\n \n authentication\n\n");
		return new AuthenticationBean("You are authenticated");
	}

	@GetMapping(path = "/hello-world-bean")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public HelloWorldBean helloWorldBean() {
		// throw new RuntimeException("Some Error has Happened! Contact Support at
		// ***-***");
		return new HelloWorldBean("Hello World - Changed");
	}

}
