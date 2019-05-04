package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almostkbal.web.services.workflow.entities.City;
import com.almostkbal.web.services.workflow.entities.Governate;
import com.almostkbal.web.services.workflow.entities.Role;
import com.almostkbal.web.services.workflow.entities.User;
import com.almostkbal.web.services.workflow.exceptions.GovernateNotFoundException;
import com.almostkbal.web.services.workflow.exceptions.UserNotFoundException;
import com.almostkbal.web.services.workflow.repositories.RoleRepository;
import com.almostkbal.web.services.workflow.repositories.UserRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/api/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/api/users/{id}")
	public Resource<User> retrieveUserById(@PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+ id);
		Resource<User> resource = new Resource<User>(user.get());
		return resource;
	}

	@DeleteMapping("/api/users/{id}")
	public void deleteUser(@PathVariable long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new UserNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/users/{id}")
	public ResponseEntity<User> updateUser(
			@PathVariable long id, @RequestBody User user){
		Optional<User> existingUser = userRepository.findById(id);

		if(!existingUser.isPresent())
			throw new UserNotFoundException("id-"+ id);
//		userRepository.deleteById(id);
		User updatedCitzen = userRepository.save(user);
		return new ResponseEntity<User>(updatedCitzen, HttpStatus.OK);
	}
	
	@PostMapping("/api/users/{id}/roles")
	public ResponseEntity<Object> addRole(@PathVariable long id, @RequestBody Role role) {

		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		User user = userOptional.get();
		user.addRole(role);
		
		userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	@GetMapping("/api/users/{id}/roles")
	public List<Role> retrieveUserRoles(@PathVariable long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new GovernateNotFoundException("id-" + id);
		}
		User user = userOptional.get();

		return user.getRoles();
	}
}
