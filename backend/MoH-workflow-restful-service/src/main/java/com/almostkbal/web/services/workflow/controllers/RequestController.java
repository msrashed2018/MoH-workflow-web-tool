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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.BonesRevealState;
import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.entities.EyeRevealState;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestPayment;
import com.almostkbal.web.services.workflow.entities.RequestState;
import com.almostkbal.web.services.workflow.entities.RequestStatus;
import com.almostkbal.web.services.workflow.entities.RequestType;
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

	@GetMapping("/api/citizens/{citizenId}/requests")
	public List<Request> retrieveCitizenRequests(@PathVariable long citizenId) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		return requestRepository.findByCitizenId(citizenId);
	}

	@DeleteMapping("/api/citizens/{citizenId}/requests/{requestId}")
	public void deleteRequest(@PathVariable long citizenId, @PathVariable long requestId) {
		if (!citizenRepository.existsById(citizenId)) {
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
	public Object createRequest(@PathVariable long citizenId, @Valid @RequestBody Request request) {

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
			requestPayment.setReceiptSerialNumber("");
			request.setState(RequestState.PENDING_PAYMENT);
			savedRequest = requestRepository.save(request);
			requestPayment.setRequest(savedRequest);
			paymentRepository.save(requestPayment);
		} else {
			request.setState(RequestState.PENDING_CONTINUE_REGISTERING);
			savedRequest = requestRepository.save(request);
		}
		return savedRequest;
	}

	@PutMapping("/api/citizens/{citizenId}/requests/{requestId}")
	public ResponseEntity<Request> updateRequest(@PathVariable long citizenId, @PathVariable long requestId,
			@Valid @RequestBody Request request) {

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
		return new ResponseEntity<Request>(updatedRequest, HttpStatus.OK);

	}

	@PutMapping("/api/citizens/{citizenId}/requests/{requestId}/updateStatus")
	public void updateRequestStatus(@PathVariable long citizenId, @PathVariable long requestId,
			@Valid @RequestBody RequestStatus requestStatus) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		requestRepository.setRequestStatus(requestId, requestStatus);
	}

	@PutMapping("/api/requests/{requestId}/review")
	public void reviewRequest(@PathVariable long requestId, @RequestBody Request request) {

		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}

		requestRepository.setRequestState(requestId, RequestState.REVIEWED);

	}

	@PutMapping("/api/requests/{requestId}/approve")
	public void approveRequest(@PathVariable long requestId, @RequestBody Request request) {

		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}

		requestRepository.setRequestState(requestId, RequestState.APPROVED);

	}
}
