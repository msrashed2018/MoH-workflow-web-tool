package com.almostkbal.web.services.workflow.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestPayment;
import com.almostkbal.web.services.workflow.entities.RequestState;
import com.almostkbal.web.services.workflow.repositories.RequestPaymentRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class RequestPaymentController {
	@Autowired
	private RequestPaymentRepository requestPaymentRepository;
	
	@Autowired
	private RequestRepository requestRepository;

	
	@GetMapping("/api/requests/{id}/payment")
	public RequestPayment retrieveRequestRequestPayment(@PathVariable long id) {
		if(!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		return requestPaymentRepository.findByRequestId(id);
	}

	@PostMapping("/api/requests/{id}/payment")
	public ResponseEntity<RequestPayment> addRequestPayment(@PathVariable long id,
			@Valid @RequestBody RequestPayment requestPayment) {
		
		Optional<Request> existingRequest = requestRepository.findById(id);

		if (!existingRequest.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		if(requestPayment.getPaymentDone() == 1) {
			existingRequest.get().setState(RequestState.PENDING_CONTINUE_REGISTERING);
			requestRepository.save(existingRequest.get());
		}
		
		requestPayment.setRequest(existingRequest.get());
		RequestPayment savedRequestPayment = requestPaymentRepository.save(requestPayment);
		
		return new ResponseEntity<RequestPayment>(savedRequestPayment, HttpStatus.OK);
		
	}
	
}
