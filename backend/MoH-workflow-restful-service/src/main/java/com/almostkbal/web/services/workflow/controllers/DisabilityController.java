package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import com.almostkbal.web.services.workflow.entities.Disability;
import com.almostkbal.web.services.workflow.exceptions.DisabilityNotFoundException;
import com.almostkbal.web.services.workflow.repositories.DisabilityRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class DisabilityController {
	@Autowired
	private DisabilityRepository disabilityRepository;
	
	@GetMapping("/api/disabilities")
	public List<Disability> retrieveAllDisabilities(){
		return disabilityRepository.findAll();
	}
	
	@GetMapping("/api/disabilities/{id}")
	public Disability retrieveDisabilityById(@PathVariable int id) {
		Optional<Disability> disability = disabilityRepository.findById(id);
		if(!disability.isPresent())
			throw new DisabilityNotFoundException("id-"+ id);
//		Resource<Disability> resource = new Resource<Disability>(disability.get());
		return disability.get();
	}

	@DeleteMapping("/api/disabilities/{id}")
	public void deleteDisability(@PathVariable int id) {
		try {
			disabilityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new DisabilityNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/disabilities")
	public ResponseEntity<Object> createDisability(@Valid @RequestBody Disability disability) {
		Disability savedDisability = disabilityRepository.save(disability);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedDisability.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/disabilities/{id}")
	public ResponseEntity<Disability> updateDisability(
			@PathVariable int id, @RequestBody Disability disability){
		Optional<Disability> existingDisability = disabilityRepository.findById(id);

		if(!existingDisability.isPresent())
			throw new DisabilityNotFoundException("id-"+ id);
//		disabilityRepository.deleteById(id);
		Disability updatedCitzen = disabilityRepository.save(disability);
		return new ResponseEntity<Disability>(updatedCitzen, HttpStatus.OK);
	}
}
