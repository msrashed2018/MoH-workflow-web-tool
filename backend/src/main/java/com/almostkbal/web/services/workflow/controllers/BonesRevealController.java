package com.almostkbal.web.services.workflow.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.Audit;
import com.almostkbal.web.services.workflow.entities.BonesReveal;
import com.almostkbal.web.services.workflow.entities.BonesRevealState;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.repositories.AuditRepository;
import com.almostkbal.web.services.workflow.repositories.BonesRevealRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;

//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins = "*")
@RestController
public class BonesRevealController {
	@Autowired
	private BonesRevealRepository bonesRevealRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private AuditRepository auditRepository;

	@GetMapping("/api/requests/{id}/bones-reveal")
	public BonesReveal retrieveRequestBonesReveal(@PathVariable long id) {
		if (!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		return bonesRevealRepository.findByRequestId(id);
	}

	@PostMapping("/api/requests/{id}/bones-reveal")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EYE')")
	public ResponseEntity<BonesReveal> addRequestBonesReveal(@PathVariable long id,
			@Valid @RequestBody BonesReveal bonesReveal, Authentication authentication) {

		if (!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}

		Request request = new Request();
		request.setId(id);
		bonesReveal.setRequest(request);
		BonesReveal savedBonesReveal = bonesRevealRepository.save(bonesReveal);
		if (bonesReveal.getRevealDone() == 1) {
			requestRepository.setBonesRevealState(id, BonesRevealState.PENDING_REGISTERING);
		}

//		Optional<Request> existingRequest = requestRepository.findById(id);
//		if (!existingRequest.isPresent())
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//		bonesReveal.setRequest(existingRequest.get());
//		BonesReveal savedBonesReveal = bonesRevealRepository.save(bonesReveal);
//
//		if (bonesReveal.getRevealDone() == 1) {
//			existingRequest.get().setBonesRevealState(BonesRevealState.PENDING_REGISTERING);
//			requestRepository.save(existingRequest.get());
//		}

		// auditing
		String action = "تسجيل حضور مواطن لكشف العظام";
		StringBuilder details = new StringBuilder("");
		details.append("لا يوجد");
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), id, performedBy);
		auditRepository.save(audit);

		return new ResponseEntity<BonesReveal>(savedBonesReveal, HttpStatus.OK);

	}
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEDICAL_REGISTERING')")
	@PutMapping("/api/requests/{requestId}/bones-reveal/{bonesRevealId}")
	public ResponseEntity<BonesReveal> updateRequestBonesReveal(@PathVariable long requestId,
			@PathVariable long bonesRevealId, @Valid @RequestBody BonesReveal bonesReveal, Authentication authentication) {

		if (!requestRepository.existsById(requestId))
			throw new ResourceNotFoundException("هذا الطلب غير موجود");

		if (!bonesRevealRepository.existsById(bonesRevealId)) {
			throw new ResourceNotFoundException("عفوا لم يتم كشف عظام لهذا المواطن");
		}
		Request request = new Request();
		request.setId(requestId);
		bonesReveal.setRequest(request);
		BonesReveal savedBonesReveal = bonesRevealRepository.save(bonesReveal);

		if (bonesReveal.getRevealDone() == 1) {
			requestRepository.setBonesRevealState(requestId, BonesRevealState.DONE);
		}
		// auditing
		String action = "تسجيل بيانات كشف عظام";
		StringBuilder details = new StringBuilder("");
		if(savedBonesReveal.getDisability() != null) {
			details.append(" نوع الاعاقة ");
			details.append(" : "+ savedBonesReveal.getDisability().getName());
			
			details.append(" نوع التجهيزة ");
			details.append(" : "+ savedBonesReveal.getDisability().getEquipment().getName());
		}
		details.append(" نتيجة الكشف ");
		details.append(" : "+ savedBonesReveal.getResult());
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);

		return new ResponseEntity<BonesReveal>(savedBonesReveal, HttpStatus.OK);

//		Optional<Request> existingRequest = requestRepository.findById(requestId);
//
//		if (!existingRequest.isPresent())
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//
//		if (!bonesRevealRepository.existsById(bonesRevealId)) {
//			throw new ResourceNotFoundException("عفوا لم يتم كشف عظام لهذا المواطن");
//		}
//
//		bonesReveal.setRequest(existingRequest.get());
//		BonesReveal savedBonesReveal = bonesRevealRepository.save(bonesReveal);
//
//		if (bonesReveal.getRevealDone() == 1) {
//			existingRequest.get().setBonesRevealState(BonesRevealState.DONE);
//			requestRepository.save(existingRequest.get());
//		}
//
//		return new ResponseEntity<BonesReveal>(savedBonesReveal, HttpStatus.OK);

	}

}
