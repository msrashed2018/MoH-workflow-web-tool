package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.almostkbal.web.services.workflow.entities.Gender;
import com.almostkbal.web.services.workflow.exceptions.GenderNotFoundException;
import com.almostkbal.web.services.workflow.repositories.GenderRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class GenderController {
	@Autowired
	private GenderRepository genderRepository;
	
	@GetMapping("/api/genders")
	public List<Gender> retrieveAllGenders(){
		return genderRepository.findAll();
	}
	
	@GetMapping("/api/genders/{id}")
	public Resource<Gender> retrieveGenderById(@PathVariable int id) {
		Optional<Gender> gender = genderRepository.findById(id);
		if(!gender.isPresent())
			throw new GenderNotFoundException("id-"+ id);
		Resource<Gender> resource = new Resource<Gender>(gender.get());
		return resource;
	}

	@DeleteMapping("/api/genders/{id}")
	public void deleteGender(@PathVariable int id) {
		genderRepository.deleteById(id);
	}

	@PostMapping("/api/genders")
	public ResponseEntity<Object> createGender(@Valid @RequestBody Gender gender) {
		Gender savedGender = genderRepository.save(gender);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedGender.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/genders/{id}")
	public ResponseEntity<Gender> updateGender(
			@PathVariable int id, @RequestBody Gender gender){
		Gender existingGender = genderRepository.getOne(id);
		
		if(existingGender == null)
			throw new GenderNotFoundException("id-"+ id);
		genderRepository.deleteById(id);
		Gender updatedCitzen = genderRepository.save(gender);
		return new ResponseEntity<Gender>(updatedCitzen, HttpStatus.OK);
	}
}
