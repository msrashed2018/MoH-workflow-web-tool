package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

import com.almostkbal.web.services.workflow.entities.Occupation;
import com.almostkbal.web.services.workflow.repositories.OccupationRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class OccupationController {
	@Autowired
	private OccupationRepository occupationRepository;
	
	@GetMapping("/api/occupations")
	public List<Occupation> retrieveAllOccupations(){
		return occupationRepository.findAll();
	}
	
	@GetMapping("/api/occupations/{id}")
	public Occupation retrieveOccupationById(@PathVariable int id) {
		Optional<Occupation> occupation = occupationRepository.findById(id);
		if(!occupation.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		Resource<Occupation> resource = new Resource<Occupation>(occupation.get());
		return occupation.get();
	}

	@DeleteMapping("/api/occupations/{id}")
	public void deleteOccupation(@PathVariable int id) {
		try {
			occupationRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/occupations")
	public ResponseEntity<Object> createOccupation(@Valid @RequestBody Occupation occupation) {
		Occupation savedOccupation = occupationRepository.save(occupation);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedOccupation.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/occupations/{id}")
	public ResponseEntity<Occupation> updateOccupation(
			@PathVariable int id, @RequestBody Occupation occupation){
		Optional<Occupation> existingOccupation = occupationRepository.findById(id);

		if(!existingOccupation.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		occupationRepository.deleteById(id);
		Occupation updatedCitzen = occupationRepository.save(occupation);
		return new ResponseEntity<Occupation>(updatedCitzen, HttpStatus.OK);
	}
}
