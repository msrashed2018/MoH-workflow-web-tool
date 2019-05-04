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

import com.almostkbal.web.services.workflow.entities.Committee;
import com.almostkbal.web.services.workflow.exceptions.CommitteeNotFoundException;
import com.almostkbal.web.services.workflow.repositories.CommitteeRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class CommitteeController {
	@Autowired
	private CommitteeRepository committeeRepository;
	
	@GetMapping("/api/committees")
	public List<Committee> retrieveAllCommittees(){
		return committeeRepository.findAll();
	}
	
	@GetMapping("/api/committees/{id}")
	public Resource<Committee> retrieveCommitteeById(@PathVariable long id) {
		Optional<Committee> committee = committeeRepository.findById(id);
		if(!committee.isPresent())
			throw new CommitteeNotFoundException("id-"+ id);
		Resource<Committee> resource = new Resource<Committee>(committee.get());
		return resource;
	}

	@DeleteMapping("/api/committees/{id}")
	public void deleteCommittee(@PathVariable long id) {
		try {
			committeeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new CommitteeNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/committees")
	public ResponseEntity<Object> createCommittee(@Valid @RequestBody Committee committee) {
		Committee savedCommittee = committeeRepository.save(committee);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedCommittee.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/committees/{id}")
	public ResponseEntity<Committee> updateCommittee(
			@PathVariable long id, @RequestBody Committee committee){
		Optional<Committee> existingCommittee = committeeRepository.findById(id);
		
		if(!existingCommittee.isPresent())
			throw new CommitteeNotFoundException("id-"+ id);
//		committeeRepository.deleteById(id);
		Committee updatedCitzen = committeeRepository.save(committee);
		return new ResponseEntity<Committee>(updatedCitzen, HttpStatus.OK);
	}
}
