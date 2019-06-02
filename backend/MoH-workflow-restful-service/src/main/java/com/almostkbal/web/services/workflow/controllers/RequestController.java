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

import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;

@CrossOrigin(origins = "*")
@RestController
public class RequestController {
	@Autowired
	private RequestRepository requestRepository;

	@GetMapping("/api/requests")
	public List<Request> retrieveAllRequests() {
		return requestRepository.findAll();
	}

	@GetMapping("/api/requests/search/findAllByDate")
	public List<Request> findAllByDate(@RequestParam String date) {

		return requestRepository.findAllByDate(date);
	}

	@GetMapping("/api/requests/{id}")
	public Request retrieveRequestById(@PathVariable long id) {
		Optional<Request> request = requestRepository.findById(id);
		if (!request.isPresent())
			throw new ResourceNotFoundException("id-" + id);
		return request.get();
	}

	@DeleteMapping("/api/requests/{id}")
	public void deleteRequest(@PathVariable long id) {
		try {
			requestRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-" + id);
		}
	}

	@PostMapping("/api/requests")
	public Object createRequest(@Valid @RequestBody Request request) {
		Request savedRequest = null;
		savedRequest = requestRepository.save(request);
		return savedRequest;
	}

	@PutMapping("/api/requests/{id}")
	public ResponseEntity<Request> updateRequest(@PathVariable long id, @RequestBody Request request) {
		Optional<Request> existingRequest = requestRepository.findById(id);

		if (!existingRequest.isPresent())
			throw new ResourceNotFoundException("id-" + id);

		Request updatedRequest = null;

		updatedRequest = requestRepository.save(request);
		return new ResponseEntity<Request>(updatedRequest, HttpStatus.OK);

	}
}
