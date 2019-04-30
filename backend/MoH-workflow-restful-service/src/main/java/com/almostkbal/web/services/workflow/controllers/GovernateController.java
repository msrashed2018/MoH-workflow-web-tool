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

import com.almostkbal.web.services.workflow.entities.Governate;
import com.almostkbal.web.services.workflow.exceptions.GovernateNotFoundException;
import com.almostkbal.web.services.workflow.repositories.GovernateRepository;
import com.almostkbal.web.services.workflow.repositories.GovernateRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class GovernateController {
	@Autowired
	private GovernateRepository governateRepository;
	
	@GetMapping("/api/governates")
	public List<Governate> retrieveAllGovernates(){
		return governateRepository.findAll();
	}
	
	@GetMapping("/api/governates/{id}")
	public Resource<Governate> retrieveGovernateById(@PathVariable int id) {
		Optional<Governate> governate = governateRepository.findById(id);
		if(!governate.isPresent())
			throw new GovernateNotFoundException("id-"+ id);
		Resource<Governate> resource = new Resource<Governate>(governate.get());
		return resource;
	}

	@DeleteMapping("/api/governates/{id}")
	public void deleteGovernate(@PathVariable int id) {
		governateRepository.deleteById(id);
	}

	@PostMapping("/api/governates")
	public ResponseEntity<Object> createGovernate(@Valid @RequestBody Governate governate) {
		Governate savedGovernate = governateRepository.save(governate);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedGovernate.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/governates/{id}")
	public ResponseEntity<Governate> updateGovernate(
			@PathVariable int id, @RequestBody Governate governate){
		Governate existingGovernate = governateRepository.getOne(id);
		
		if(existingGovernate == null)
			throw new GovernateNotFoundException("id-"+ id);
		governateRepository.deleteById(id);
		Governate updatedCitzen = governateRepository.save(governate);
		return new ResponseEntity<Governate>(updatedCitzen, HttpStatus.OK);
	}
}
