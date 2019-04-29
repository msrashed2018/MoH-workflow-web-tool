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
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping(path = "/test")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AuthenticationBean helloWorld(Authentication authentication) {
		return new AuthenticationBean("You are authenticated");
//		User user = userRepository.findByUsername(authentication.getName());
//		System.out.println("\n username  :"+authentication.getName() );
//        for (Role role : user.getRoles()){
//        	System.out.println("\n role  "+role.getRoleName());
//        }
//
//		
//		List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();
//		
//		String authorities = "  ";
//		for(GrantedAuthority authority : grantedAuthorities) {
//			authorities = authorities + "  " +authority.getAuthority();
//		}
//		return "Hello World --  authentication = " + authentication.getName() + "  \n authorities : "+authorities;
	}

@GetMapping(path = "/hello-world-bean")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public HelloWorldBean helloWorldBean() {
	//throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
	return new HelloWorldBean("Hello World - Changed");
}

}
