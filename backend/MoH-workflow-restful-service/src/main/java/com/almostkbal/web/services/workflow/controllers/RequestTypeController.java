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

import com.almostkbal.web.services.workflow.entities.RequestType;
import com.almostkbal.web.services.workflow.exceptions.RequestTypeNotFoundException;
import com.almostkbal.web.services.workflow.repositories.RequestTypeRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class RequestTypeController {
	@Autowired
	private RequestTypeRepository requestTypeRepository;
	
	@GetMapping("/api/request-type")
	public List<RequestType> retrieveAllRequestTypes(){
		return requestTypeRepository.findAll();
	}

	
	@GetMapping("/api/request-type/{id}")
	public RequestType retrieveRequestTypeById(@PathVariable int id) {
		Optional<RequestType> requestType = requestTypeRepository.findById(id);
		if(!requestType.isPresent())
			throw new RequestTypeNotFoundException("id-"+ id);
//		Resource<RequestType> resource = new Resource<RequestType>(requestType.get());
		return requestType.get();
	}

	@DeleteMapping("/api/request-type/{id}")
	public void deleteRequestType(@PathVariable int id) {
		try {
			requestTypeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new RequestTypeNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/request-type")
	public ResponseEntity<Object> createRequestType(@Valid @RequestBody RequestType requestType) {
		RequestType savedRequestType = requestTypeRepository.save(requestType);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedRequestType.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/request-type/{id}")
	public ResponseEntity<RequestType> updateRequestType(
			@PathVariable int id, @RequestBody RequestType requestType){
		Optional<RequestType> existingRequestType = requestTypeRepository.findById(id);

		if(!existingRequestType.isPresent())
			throw new RequestTypeNotFoundException("id-"+ id);
//		requestTypeRepository.deleteById(id);
		RequestType updatedCitzen = requestTypeRepository.save(requestType);
		return new ResponseEntity<RequestType>(updatedCitzen, HttpStatus.OK);
	}
}
