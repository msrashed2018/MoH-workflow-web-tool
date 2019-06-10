package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

import com.almostkbal.web.services.workflow.entities.EyeReveal;
import com.almostkbal.web.services.workflow.entities.Gender;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.repositories.EyeRevealRepository;
import com.almostkbal.web.services.workflow.repositories.GenderRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class EyeRevealController {
	@Autowired
	private EyeRevealRepository eyeRevealRepository;
	
	@Autowired
	private RequestRepository requestRepository;

	
	@GetMapping("/api/requests/{id}/eye-reveal")
	public EyeReveal retrieveRequestEyeReveal(@PathVariable long id) {
		if(!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		return eyeRevealRepository.findByRequestId(id);
	}

	@PostMapping("/api/requests/{id}/eye-reveal")
	public ResponseEntity<EyeReveal> addRequestEyeReveal(@PathVariable long id, @RequestBody EyeReveal eyeReveal) {
		
		Optional<Request> existingRequest = requestRepository.findById(id);

		if (!existingRequest.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		eyeReveal.setRequest(existingRequest.get());
		EyeReveal savedEyeReveal = eyeRevealRepository.save(eyeReveal);
		
		return new ResponseEntity<EyeReveal>(savedEyeReveal, HttpStatus.OK);
		
	}
	
}
