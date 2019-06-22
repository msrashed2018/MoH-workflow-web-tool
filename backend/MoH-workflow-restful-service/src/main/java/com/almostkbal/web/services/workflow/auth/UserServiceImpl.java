package com.almostkbal.web.services.workflow.auth;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.almostkbal.web.services.workflow.entities.User;
import com.almostkbal.web.services.workflow.repositories.UserRepository;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

//	public List<User> findAll() {
//		List<User> list = new ArrayList<>();
//		userRepository.findAll().iterator().forEachRemaining(list::add);
//		return list;
//	}
//
//	@Override
//	public void delete(long id) {
//		userRepository.deleteById(id);
//	}
//
//	@Override
//	public User findOne(String username) {
//		return userRepository.findByUsername(username);
//	}
//
//	@Override
//	public User findById(Long id) {
//		logger.info("findById");
//		return userRepository.findById(id).get();
//	}
//
//	@Override
//    public User save(UserDto user) {
//		logger.info("save");
//	    User newUser = new User();
//	    newUser.setUsername(user.getUsername());
//	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//        return userRepository.save(newUser);
//    }
}
