package com.almostkbal.web.services.workflow.auth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.almostkbal.web.services.workflow.entities.Role;
import com.almostkbal.web.services.workflow.entities.User;
import com.almostkbal.web.services.workflow.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	 @Autowired
	 private UserRepository userRepository;

	    @Override
	    @Transactional(readOnly = true)
	    public UserDetails loadUserByUsername(String username) {
	        User user = userRepository.findByUsername(username);
	        if (user == null) throw new UsernameNotFoundException(username);
	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        System.out.println("username : "+username + "roles : ");
	        for (Role role : user.getRoles()){
	        	System.out.print(role+ ", ");
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	    }
}
