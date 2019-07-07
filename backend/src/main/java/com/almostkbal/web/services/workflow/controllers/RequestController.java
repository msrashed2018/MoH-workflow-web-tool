package com.almostkbal.web.services.workflow.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.Audit;
import com.almostkbal.web.services.workflow.entities.BonesRevealState;
import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.entities.EyeRevealState;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestPayment;
import com.almostkbal.web.services.workflow.entities.RequestState;
import com.almostkbal.web.services.workflow.entities.RequestStatus;
import com.almostkbal.web.services.workflow.entities.RequestType;
import com.almostkbal.web.services.workflow.repositories.AuditRepository;
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;
import com.almostkbal.web.services.workflow.repositories.RequestPaymentRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;
import com.almostkbal.web.services.workflow.repositories.RequestTypeRepository;

@CrossOrigin(origins = "*")
@RestController
public class RequestController {
	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private RequestTypeRepository requestTypeRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private RequestPaymentRepository paymentRepository;

	@Autowired
	private AuditRepository auditRepository;

	@GetMapping("/api/requests")
	public Page<Request> retrieveAllRequests(@RequestParam("page") int page, @RequestParam("size") int size) {
		return requestRepository.findAll(PageRequest.of(page, size, Sort.by("requestDate").descending()));
	}

	@GetMapping("/api/requests/{requestId}/retreiveRequestState")
	public RequestState retreiveRequestState(@PathVariable long requestId) {
		return requestRepository.findRequestState(requestId);
	}

	@GetMapping("/api/requests/retreiveRequestsForEyeReveal")
	public Page<Request> retreiveRequestsForEyeReveal(@RequestParam("page") int page, @RequestParam("size") int size) {
		return requestRepository.findByStateAndEyeRevealState(RequestState.CONTINUE_REGISTERING_DONE,
				EyeRevealState.PENDING_REVEAL, PageRequest.of(page, size));
	}

	@GetMapping("/api/requests/retreiveRequestsForBonesReveal")
	public Page<Request> retreiveRequestsForBonesReveal(@RequestParam("page") int page,
			@RequestParam("size") int size) {
		return requestRepository.findByStateAndBonesRevealState(RequestState.CONTINUE_REGISTERING_DONE,
				BonesRevealState.PENDING_REVEAL, PageRequest.of(page, size));
	}

	@GetMapping("/api/requests/retreiveRequestsForRevealsRegistering")
	public Page<Request> retreiveRequestsForRevealsRegistering(@RequestParam("page") int page,
			@RequestParam("size") int size) {

		List<BonesRevealState> bonesRevealStates = new ArrayList<BonesRevealState>();
		bonesRevealStates.add(BonesRevealState.PENDING_REGISTERING);
		bonesRevealStates.add(BonesRevealState.NA);

		List<EyeRevealState> eyeRevealStates = new ArrayList<EyeRevealState>();
		eyeRevealStates.add(EyeRevealState.PENDING_REGISTERING);
		eyeRevealStates.add(EyeRevealState.NA);
		return requestRepository.findByStateAndBonesRevealStateInAndEyeRevealStateIn(
				RequestState.CONTINUE_REGISTERING_DONE, bonesRevealStates, eyeRevealStates, PageRequest.of(page, size));
	}

	@GetMapping("/api/requests/retreiveRequestsForReviewing")
	public Page<Request> retreiveRequestsForReviewing(@RequestParam("page") int page, @RequestParam("size") int size) {
		List<BonesRevealState> bonesRevealStates = new ArrayList<BonesRevealState>();
		bonesRevealStates.add(BonesRevealState.DONE);
		bonesRevealStates.add(BonesRevealState.NA);

		List<EyeRevealState> eyeRevealStates = new ArrayList<EyeRevealState>();
		eyeRevealStates.add(EyeRevealState.DONE);
		eyeRevealStates.add(EyeRevealState.NA);
		return requestRepository.findByStateAndBonesRevealStateInAndEyeRevealStateIn(
				RequestState.CONTINUE_REGISTERING_DONE, bonesRevealStates, eyeRevealStates, PageRequest.of(page, size));
	}

	@GetMapping("/api/requests/retreiveRequestsForApproving")
	public Page<Request> retreiveRequestsForApproving(@RequestParam("page") int page, @RequestParam("size") int size) {
		return requestRepository.findByState(RequestState.REVIEWED, PageRequest.of(page, size));
	}

	@GetMapping("/api/requests/retreiveByRequestState")
	public Page<Request> retrieveAllRequestsByState(@RequestParam RequestState state, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		return requestRepository.findByState(state, PageRequest.of(page, size));
//		return requestRepository.findAll();
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

	@GetMapping("/api/requests/search/findBySearchKey")
	public List<Request> findBySearchKey(@RequestParam String searchKey) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByCitizenMobileNumber(searchKey);
			} else {
				// assuming search key is national id
				return requestRepository.findByCitizenNationalId(key);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			return requestRepository.findByCitizenName(searchKey);
		}
	}

	@GetMapping("/api/citizens/{citizenId}/requests")
	public List<Request> retrieveCitizenRequests(@PathVariable long citizenId) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		return requestRepository.findByCitizenId(citizenId);
	}

	@DeleteMapping("/api/citizens/{citizenId}/requests/{requestId}")
	public void deleteRequest(@PathVariable long citizenId, @PathVariable long requestId,
			Authentication authentication) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}
//		try {
		Optional<Request> request = requestRepository.findById(requestId);
		if (!request.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		requestRepository.deleteById(requestId);

		// auditing
		String action = "مسح طلب";
		StringBuilder details = new StringBuilder("");
		details.append(" نوع الطلب ");
		details.append(" : " + request.get().getRequestType().getName());

		if (request.get().getRequestStatus() != null) {
			details.append(" نتيجة الطلب");
			details.append(" : " + request.get().getRequestStatus().getName());
		}
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);
//		} catch (EmptyResultDataAccessException ex) {
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//		}
	}

	@GetMapping("/api/requests/{id}")
	public Request retrieveRequestById(@PathVariable long id) {
		Optional<Request> request = requestRepository.findById(id);
		if (!request.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		return request.get();
	}

	@PostMapping("/api/citizens/{citizenId}/requests")
	public Object createRequest(@PathVariable long citizenId, @Valid @RequestBody Request request,
			Authentication authentication) {

		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		Optional<RequestType> requestType = requestTypeRepository.findById(request.getRequestType().getId());

		if (!requestType.isPresent()) {
			throw new ResourceNotFoundException("عفوا نوع الطلب غير موجود");
		}

		Citizen citizen = new Citizen();
		citizen.setId(citizenId);
		request.setCitizen(citizen);

		Request savedRequest = null;
		if (requestType.get().getPrice() > 0) {
			RequestPayment requestPayment = new RequestPayment();
			requestPayment.setPrice(requestType.get().getPrice());
			requestPayment.setReceiptSerialNumber("0");
			request.setState(RequestState.PENDING_PAYMENT);
			savedRequest = requestRepository.save(request);
			requestPayment.setRequest(savedRequest);
			paymentRepository.save(requestPayment);
		} else {
			request.setState(RequestState.PENDING_CONTINUE_REGISTERING);
			savedRequest = requestRepository.save(request);
		}

		// auditing
		String action = "اضافة طلب جديد";
		StringBuilder details = new StringBuilder("");
		details.append("نوع الطلب");
		details.append(" : " + requestType.get().getName());
		long requestId = savedRequest.getId();
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);

		return savedRequest;
	}

	@PutMapping("/api/citizens/{citizenId}/requests/{requestId}")
	public ResponseEntity<Request> updateRequest(@PathVariable long citizenId, @PathVariable long requestId,
			@Valid @RequestBody Request request, Authentication authentication) {

		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}

//		if (requestRepository.findRequestState(requestId) != RequestState.PENDING_CONTINUE_REGISTERING) {
//			throw new IllegalRequestStateException(new Date(), "هذا الطلب تم تنفيذه من قبل",
//					"هذا الطلب تم تنفيذه من قبل");
//		}

		if (request.getBonesCommittee() != null) {
			request.setBonesRevealState(BonesRevealState.PENDING_REVEAL);
		}

		if (request.getEyeCommittee() != null) {
			request.setEyeRevealState(EyeRevealState.PENDING_REVEAL);
		}
		request.setState(RequestState.CONTINUE_REGISTERING_DONE);

		Citizen citizen = new Citizen();
		citizen.setId(citizenId);
		request.setCitizen(citizen);

		Request updatedRequest = requestRepository.save(request);

		// auditing
		String action = "استكمال بيانات طلب";
		StringBuilder details = new StringBuilder("");

		details.append(" نوع الطلب ");
		details.append((" : " + updatedRequest.getRequestType().getName()));

		if (updatedRequest.getBonesCommittee() != null) {
			details.append(" ميعاد لجنة العظام  ");
			details.append(" : " + updatedRequest.getBonesCommittee().getDate().toString());
		}

		if (request.getEyeCommittee() != null) {
			details.append("  ميعاد لجنة الرمد  ");
			details.append(" : " + updatedRequest.getEyeCommittee().getDate().toString());
		}
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);

		return new ResponseEntity<Request>(updatedRequest, HttpStatus.OK);

	}

	@PutMapping("/api/citizens/{citizenId}/requests/{requestId}/updateStatus")
	public void updateRequestStatus(@PathVariable long citizenId, @PathVariable long requestId,
			@Valid @RequestBody RequestStatus requestStatus, Authentication authentication) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		requestRepository.setRequestStatus(requestId, requestStatus);

		// auditing
		String action = "تعديل نتيجة طلب";
		StringBuilder details = new StringBuilder("");
		details.append("نتيجة الطلب");
		details.append(" : " + requestStatus.getName());
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);
	}

	@PutMapping("/api/requests/{requestId}/review")
	public void reviewRequest(@PathVariable long requestId, @RequestBody Request request,
			Authentication authentication) {

		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}

		requestRepository.setRequestState(requestId, RequestState.REVIEWED);

		// auditing
		String action = "مراجعة طلب";
		StringBuilder details = new StringBuilder("");
		details.append("لا يوجد");
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);

	}

	@PutMapping("/api/requests/{requestId}/approve")
	public void approveRequest(@PathVariable long requestId, @RequestBody Request request,
			Authentication authentication) {

		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}

		requestRepository.setRequestState(requestId, RequestState.APPROVED);

		// auditing
		String action = "اعتماد طلب";
		StringBuilder details = new StringBuilder("");
		details.append("لا يوجد");
		String performedBy = authentication.getName();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy);
		auditRepository.save(audit);

	}
}
