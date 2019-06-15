package com.almostkbal.web.services.workflow.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.BonesReveal;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestState;
import com.almostkbal.web.services.workflow.repositories.BonesRevealRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class BonesRevealController {
	@Autowired
	private BonesRevealRepository bonesRevealRepository;
	
	@Autowired
	private RequestRepository requestRepository;

	
	@GetMapping("/api/requests/{id}/bones-reveal")
	public BonesReveal retrieveRequestBonesReveal(@PathVariable long id) {
		if(!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		return bonesRevealRepository.findByRequestId(id);
	}
	@PostMapping("/api/requests/{id}/bones-reveal")
	public ResponseEntity<BonesReveal> addRequestBonesReveal(@PathVariable long id,
			@RequestBody BonesReveal bonesReveal) {
		
		Optional<Request> existingRequest = requestRepository.findById(id);

		if (!existingRequest.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		bonesReveal.setRequest(existingRequest.get());
		BonesReveal savedBonesReveal = bonesRevealRepository.save(bonesReveal);
		
		
		if(bonesReveal.getRevealDone() == 1) {
			existingRequest.get().setState(RequestState.BONES_REVEAL);
			requestRepository.save(existingRequest.get());
		}
		
		return new ResponseEntity<BonesReveal>(savedBonesReveal, HttpStatus.OK);
		
	}

	@PutMapping("/api/requests/{requestId}/bones-reveal/{bonesRevealId}")
	public ResponseEntity<BonesReveal> updateRequestBonesReveal(@PathVariable long requestId,
			@PathVariable long bonesRevealId, @RequestBody BonesReveal bonesReveal) {

		Optional<Request> existingRequest = requestRepository.findById(requestId);

		if (!existingRequest.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");

		if (!bonesRevealRepository.existsById(bonesRevealId)) {
			throw new ResourceNotFoundException("عفوا لم يتم كشف عظام لهذا المواطن");
		}

		bonesReveal.setRequest(existingRequest.get());
		BonesReveal savedBonesReveal = bonesRevealRepository.save(bonesReveal);

		if (bonesReveal.getRevealDone() == 1) {
			existingRequest.get().setState(RequestState.BONES_REVEAL_REGISTERED);
			requestRepository.save(existingRequest.get());
		}

		return new ResponseEntity<BonesReveal>(savedBonesReveal, HttpStatus.OK);

	}
	
}
