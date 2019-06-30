package com.almostkbal.web.services.workflow.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.Audit;
import com.almostkbal.web.services.workflow.entities.BonesRevealState;
import com.almostkbal.web.services.workflow.entities.EyeReveal;
import com.almostkbal.web.services.workflow.entities.EyeRevealSetting;
import com.almostkbal.web.services.workflow.entities.EyeRevealState;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.repositories.AuditRepository;
import com.almostkbal.web.services.workflow.repositories.EyeRevealRepository;
import com.almostkbal.web.services.workflow.repositories.EyeRevealSettingRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;

//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins = "*")
@RestController
public class EyeRevealController {
	@Autowired
	private EyeRevealRepository eyeRevealRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private EyeRevealSettingRepository eyeRevealSettingRepository;

	@Autowired
	private AuditRepository auditRepository;

	@GetMapping("/api/requests/{id}/eye-reveal")
	public EyeReveal retrieveRequestEyeReveal(@PathVariable long id) {
		if (!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		return eyeRevealRepository.findByRequestId(id);
	}

	@PostMapping("/api/requests/{id}/eye-reveal")
	public ResponseEntity<EyeReveal> addRequestEyeReveal(@PathVariable long id, @RequestBody EyeReveal eyeReveal,
			Authentication authentication) {
		if (!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		Request request = new Request();
		request.setId(id);
		eyeReveal.setRequest(request);
		EyeReveal savedEyeReveal = eyeRevealRepository.save(eyeReveal);
		if (eyeReveal.getRevealDone() == 1) {
			requestRepository.setEyeRevealState(id, EyeRevealState.PENDING_REGISTERING);
		}
//		Optional<Request> existingRequest = requestRepository.findById(id);
//		if (!existingRequest.isPresent())
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//		eyeReveal.setRequest(existingRequest.get());
//		EyeReveal savedEyeReveal = eyeRevealRepository.save(eyeReveal);
//		if (eyeReveal.getRevealDone() == 1) {
//			existingRequest.get().setEyeRevealState(EyeRevealState.PENDING_REGISTERING);
//			requestRepository.save(existingRequest.get());
//		}
		// auditing

		String action = "تسجيل حضور مواطن لكشف الرمد";
		StringBuilder details = new StringBuilder("");
		details.append("لا يوجد");
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), id, performedBy);
		auditRepository.save(audit);
		return new ResponseEntity<EyeReveal>(savedEyeReveal, HttpStatus.OK);

	}

	@PutMapping("/api/requests/{requestId}/eye-reveal/{bonesRevealId}")
	public ResponseEntity<EyeReveal> updateRequestEyeReveal(@PathVariable long requestId,
			@PathVariable long bonesRevealId, @Valid @RequestBody EyeReveal eyeReveal, Authentication authentication) {

		if (!requestRepository.existsById(requestId))
			throw new ResourceNotFoundException("هذا الطلب غير موجود");

		if (!eyeRevealRepository.existsById(bonesRevealId)) {
			throw new ResourceNotFoundException("عفوا لم يتم كشف رمد لهذا المواطن");
		}

		EyeRevealSetting setting = eyeRevealSettingRepository
				.findByRightMeasureAndLeftMeasureAndUseGlassesAndDistinguishColorAndSquint(eyeReveal.getRightEye(),
						eyeReveal.getLeftEye(), eyeReveal.getUseGlasses(), eyeReveal.getDistinguishColor(),
						eyeReveal.getSquint());

		if (setting == null) {
			throw new ResourceNotFoundException("عفوا لا توجد اعدادات كشف رمد لهذه البيانات");
		} else {
			eyeReveal.setResult(setting.getResult());
		}

		Request request = new Request();
		request.setId(requestId);
		eyeReveal.setRequest(request);

		EyeReveal savedEyeReveal = eyeRevealRepository.save(eyeReveal);

		if (eyeReveal.getRevealDone() == 1) {
			requestRepository.setEyeRevealState(requestId, EyeRevealState.DONE);
		}

		// auditing
		String action = "تسجيل بيانات كشف رمد";
		StringBuilder details = new StringBuilder("");
		details.append("نتيجة الكشف : ");
		details.append(savedEyeReveal.getResult());
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);
		return new ResponseEntity<EyeReveal>(savedEyeReveal, HttpStatus.OK);

	}

}
