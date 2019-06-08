package com.almostkbal.web.services.workflow.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.BonesReveal;
import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.entities.EyeReveal;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestPayment;
import com.almostkbal.web.services.workflow.repositories.BonesRevealRepository;
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;
import com.almostkbal.web.services.workflow.repositories.EyeRevealRepository;
import com.almostkbal.web.services.workflow.repositories.RequestPaymentRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;

@CrossOrigin(origins = "*")
@RestController
public class RequestController {
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private RequestPaymentRepository paymentRepository;
	
	
	@Autowired
	private BonesRevealRepository bonesRevealRepository;
	
	
	
	@Autowired
	private EyeRevealRepository eyeRevealRepository;
	

	@GetMapping("/api/requests")
	public List<Request> retrieveAllRequests() {
		return requestRepository.findAll();
	}

	@GetMapping("/api/requests/search/findAllByDate")
	public List<Request> findAllByDate(@RequestParam String date) {

		return requestRepository.findAllByDate(date);
	}
	@GetMapping("/api/requests/search/findAllByNationalId")
	public List<Request> findAllByNationalId(@RequestParam long nationalId) {
		List<Request> requests = requestRepository.findByCitizenNationalId(nationalId);
		return requests;
	}
	
	
	@GetMapping("/api/citizens/{citizenId}/requests")
	public List<Request> retrieveCitizenRequests(@PathVariable long citizenId) {
		if(!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		return requestRepository.findByCitizenId(citizenId);
	}
	
	
	@DeleteMapping("/api/citizens/{citizenId}/requests/{requestId}")
	public void deleteRequest(@PathVariable long citizenId, @PathVariable long requestId) {
		if(!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}
		try {
			requestRepository.deleteById(requestId);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
	}
	
	
	
	
	@GetMapping("/api/requests/{id}")
	public Request retrieveRequestById(@PathVariable long id) {
		Optional<Request> request = requestRepository.findById(id);
		if (!request.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		return request.get();
	}



	@PostMapping("/api/citizens/{citizenId}/requests")
	public Object createRequest(@PathVariable long citizenId, @RequestBody Request request) {
		Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);

		if (!citizenOptional.isPresent())
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		
		Request savedRequest = null;
		request.setCitizen(citizenOptional.get());
		savedRequest = requestRepository.save(request);
		
		EyeReveal eyeReveal = new EyeReveal();
		eyeReveal.setRequest(savedRequest);
		eyeRevealRepository.save(eyeReveal);
		
		
		BonesReveal bonesReveal = new BonesReveal();
		bonesReveal.setRequest(savedRequest);
		bonesRevealRepository.save(bonesReveal);
		
		RequestPayment requestPayment = new RequestPayment();
		requestPayment.setRequest(savedRequest);
		paymentRepository.save(requestPayment);
		return savedRequest;
	}

	@PutMapping("/api/citizens/{citizenId}/requests/{requestId}")
	public ResponseEntity<Request> updateRequest(@PathVariable long citizenId, @PathVariable long requestId, @RequestBody Request request) {
		
		Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);

		if (!citizenOptional.isPresent())
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		
		
		Optional<Request> existingRequest = requestRepository.findById(requestId);

		if (!existingRequest.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");

		
		request.setCitizen(citizenOptional.get());
		Request updatedRequest = requestRepository.save(request);
		return new ResponseEntity<Request>(updatedRequest, HttpStatus.OK);

	}
}
