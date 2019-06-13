package com.almostkbal.web.services.workflow.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.BonesReveal;
import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.entities.Committee;
import com.almostkbal.web.services.workflow.entities.EyeReveal;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestPayment;
import com.almostkbal.web.services.workflow.entities.RequestState;
import com.almostkbal.web.services.workflow.repositories.BonesRevealRepository;
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;
import com.almostkbal.web.services.workflow.repositories.EyeRevealRepository;
import com.almostkbal.web.services.workflow.repositories.RequestPaymentRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;
import com.almostkbal.web.services.workflow.repositories.RequestTypeRepository;

@CrossOrigin(origins = "*")
@RestController
public class RequestController {
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private RequestTypeRepository requestTypeRepository ;
	
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
	@GetMapping("/api/requests/retreiveByRequestState")
	public List<Request> retrieveAllRequestsByState(@RequestParam RequestState state) {
		return requestRepository.findByState(state);
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
	
	
//	@GetMapping("/api/requests/{id}/eye-committee")
//	public Committee retrieveRequestEyeCommittee(@PathVariable long id) {
//		System.out.println("\n\n Optional<Request> request = requestRepository.findById(id) \n\n");
//		Optional<Request> request = requestRepository.findById(id);
//		if (!request.isPresent())
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//		
//		System.out.println("\n\n Committee eyeCommitttee = request.get().getEyeCommittee(); \n\n");
//		Committee eyeCommitttee = request.get().getEyeCommittee();
//		return eyeCommitttee;
//	}
//
//	@PostMapping("/api/requests/{id}/eye-committee")
//	public void  addRequestEyeCommittee(@PathVariable long id, @RequestBody Committee eyeCommittee) {
//		
//		Optional<Request> existingRequest = requestRepository.findById(id);
//
//		if (!existingRequest.isPresent())
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//		
//		existingRequest.get().setEyeCommittee(eyeCommittee);
//		
//		requestRepository.save(existingRequest.get());
////		return new ResponseEntity<Committee>(savedEyeReveal, HttpStatus.OK);
//		
//	}
//	
//	@PostMapping("/api/requests/{id}/bones-committee")
//	public void  addRequestBonesCommittee(@PathVariable long id, @RequestBody Committee bonesCommittee) {
//		
//		Optional<Request> existingRequest = requestRepository.findById(id);
//
//		if (!existingRequest.isPresent())
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//		
//		existingRequest.get().setBonesCommittee(bonesCommittee);
//		
//		requestRepository.save(existingRequest.get());
////		return new ResponseEntity<Committee>(savedEyeReveal, HttpStatus.OK);
//		
//	}
	
	
	@PostMapping("/api/citizens/{citizenId}/requests")
	public Object createRequest(@PathVariable long citizenId,@Valid @RequestBody Request request) {
		Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);

		if (!citizenOptional.isPresent())
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		
		Request savedRequest = null;
		request.setCitizen(citizenOptional.get());
		
		
//		request.setState(RequestState.NEW);
		savedRequest = requestRepository.save(request);
		
		
//		EyeReveal eyeReveal = new EyeReveal();
//		eyeReveal.setRequest(savedRequest);
//		eyeRevealRepository.save(eyeReveal);
//		
//		
//		BonesReveal bonesReveal = new BonesReveal();
//		bonesReveal.setRequest(savedRequest);
//		bonesRevealRepository.save(bonesReveal);
		
		RequestPayment requestPayment = new RequestPayment();
		
		requestPayment.setPrice(requestTypeRepository.findById(request.getRequestType().getId()).get().getPrice());
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
