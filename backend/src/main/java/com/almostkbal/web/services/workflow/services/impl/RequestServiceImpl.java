package com.almostkbal.web.services.workflow.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.almostkbal.web.services.workflow.auth.UserService;
import com.almostkbal.web.services.workflow.entities.Audit;
import com.almostkbal.web.services.workflow.entities.BonesRevealState;
import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.entities.EyeRevealState;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestPayment;
import com.almostkbal.web.services.workflow.entities.RequestState;
import com.almostkbal.web.services.workflow.entities.RequestStatus;
import com.almostkbal.web.services.workflow.entities.RequestType;
import com.almostkbal.web.services.workflow.entities.Zone;
import com.almostkbal.web.services.workflow.repositories.AuditRepository;
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;
import com.almostkbal.web.services.workflow.repositories.RequestPaymentRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;
import com.almostkbal.web.services.workflow.repositories.RequestTypeRepository;
import com.almostkbal.web.services.workflow.services.RequestService;

@Service(value = "requestService")
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	UserService userService;

	@Autowired
	private AuditRepository auditRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private RequestPaymentRepository paymentRepository;

	@Autowired
	private RequestTypeRepository requestTypeRepository;

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_PAYMENTS_REGISTRATION')")
	public Page<Request> getRequestsForPayment(Pageable pageable) {
		return requestRepository.findByZoneIdAndState(userService.getUserZoneId(), RequestState.PENDING_PAYMENT,
				pageable);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_PAYMENTS_REGISTRATION')")
	public Page<Request> getRequestsBySearchKeyForPayment(String searchKey, Pageable pageable) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndCitizenMobileNumber(userService.getUserZoneId(),
						RequestState.PENDING_PAYMENT, searchKey, pageable);
			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndCitizenNationalId(userService.getUserZoneId(),
						RequestState.PENDING_PAYMENT, key, pageable);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndRequestDate(userService.getUserZoneId(),
							RequestState.PENDING_PAYMENT, requestDate, pageable);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndCitizenNameContaining(userService.getUserZoneId(),
						RequestState.PENDING_PAYMENT, searchKey, pageable);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_CONTINUE_REGISTERING')")
	public Page<Request> getRequestsForContinueRegistering(Pageable pageable) {
		return requestRepository.findByZoneIdAndState(userService.getUserZoneId(),
				RequestState.PENDING_CONTINUE_REGISTERING, pageable);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_CONTINUE_REGISTERING')")
	public Page<Request> getRequestsBySearchKeyForContinueRegistering(String searchKey, Pageable pageable) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndCitizenMobileNumber(userService.getUserZoneId(),
						RequestState.PENDING_CONTINUE_REGISTERING, searchKey, pageable);
			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndCitizenNationalId(userService.getUserZoneId(),
						RequestState.PENDING_CONTINUE_REGISTERING, key, pageable);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndRequestDate(userService.getUserZoneId(),
							RequestState.PENDING_CONTINUE_REGISTERING, requestDate, pageable);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndCitizenNameContaining(userService.getUserZoneId(),
						RequestState.PENDING_CONTINUE_REGISTERING, searchKey, pageable);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_EYE_REVEAL')")
	public Page<Request> getRequestsForEyeRevealAttending(Pageable page) {
		return requestRepository.findByZoneIdAndStateAndEyeRevealState(userService.getUserZoneId(),
				RequestState.CONTINUE_REGISTERING_DONE, EyeRevealState.PENDING_REVEAL, page);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_EYE_REVEAL')")
	public Page<Request> getRequestsBySearchKeyForEyeRevealAttending(String searchKey, Pageable page) {

		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndCitizenMobileNumber(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						EyeRevealState.PENDING_REVEAL, searchKey, page);

			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndCitizenNationalId(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						EyeRevealState.PENDING_REVEAL, key, page);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndRequestDate(
							userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
							EyeRevealState.PENDING_REVEAL, requestDate, page);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndCitizenNameContaining(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						EyeRevealState.PENDING_REVEAL, searchKey, page);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_BONES_REVEAL')")
	public Page<Request> getRequestsForBonesRevealAttending(Pageable pageable) {
		return requestRepository.findByZoneIdAndStateAndBonesRevealState(userService.getUserZoneId(),
				RequestState.CONTINUE_REGISTERING_DONE, BonesRevealState.PENDING_REVEAL, pageable);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_BONES_REVEAL')")
	public Page<Request> getRequestsBySearchKeyForBonesRevealAttending(String searchKey, Pageable page) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndCitizenMobileNumber(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						BonesRevealState.PENDING_REVEAL, searchKey, page);

			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndCitizenNationalId(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						BonesRevealState.PENDING_REVEAL, key, page);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndRequestDate(
							userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
							BonesRevealState.PENDING_REVEAL, requestDate, page);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndCitizenNameContaining(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						BonesRevealState.PENDING_REVEAL, searchKey, page);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_EYE_REVEAL_RESULT_REGISTERING')")
	public Page<Request> getRequestsForEyeRevealResultRegistering(Pageable page) {
		return requestRepository.findByZoneIdAndStateAndEyeRevealState(userService.getUserZoneId(),
				RequestState.CONTINUE_REGISTERING_DONE, EyeRevealState.PENDING_REGISTERING, page);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_EYE_REVEAL_RESULT_REGISTERING')")
	public Page<Request> getRequestsBySearchKeyForEyeRevealResultRegistering(String searchKey, Pageable page) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndCitizenMobileNumber(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						EyeRevealState.PENDING_REGISTERING, searchKey, page);

			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndCitizenNationalId(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						EyeRevealState.PENDING_REGISTERING, key, page);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndRequestDate(
							userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
							EyeRevealState.PENDING_REGISTERING, requestDate, page);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndEyeRevealStateAndCitizenNameContaining(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						EyeRevealState.PENDING_REGISTERING, searchKey, page);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_BONES_REVEAL_RESULT_REGISTERING')")
	public Page<Request> getRequestsForBonesRevealResultRegistering(Pageable pageable) {
		return requestRepository.findByZoneIdAndStateAndBonesRevealState(userService.getUserZoneId(),
				RequestState.CONTINUE_REGISTERING_DONE, BonesRevealState.PENDING_REGISTERING, pageable);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_BONES_REVEAL_RESULT_REGISTERING')")
	public Page<Request> getRequestsBySearchKeyForBonesRevealResultRegistering(String searchKey, Pageable page) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndCitizenMobileNumber(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						BonesRevealState.PENDING_REGISTERING, searchKey, page);

			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndCitizenNationalId(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						BonesRevealState.PENDING_REGISTERING, key, page);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndRequestDate(
							userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
							BonesRevealState.PENDING_REGISTERING, requestDate, page);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndBonesRevealStateAndCitizenNameContaining(
						userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE,
						BonesRevealState.PENDING_REGISTERING, searchKey, page);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_REVIEWING')")
	public Page<Request> getRequestsForReviewing(Pageable pageable) {
		// for reviewing
		List<BonesRevealState> bonesRevealStates = new ArrayList<BonesRevealState>();
		bonesRevealStates.add(BonesRevealState.DONE);
		bonesRevealStates.add(BonesRevealState.NA);

		List<EyeRevealState> eyeRevealStates = new ArrayList<EyeRevealState>();
		eyeRevealStates.add(EyeRevealState.DONE);
		eyeRevealStates.add(EyeRevealState.NA);
		return requestRepository.findByZoneIdAndStateAndBonesRevealStateInAndEyeRevealStateIn(
				userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE, bonesRevealStates, eyeRevealStates,
				pageable);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_REVIEWING')")
	public Page<Request> getRequestsBySearchKeyForReviewing(String searchKey, Pageable page) {
		// for reviewing
		List<BonesRevealState> bonesRevealStates = new ArrayList<BonesRevealState>();
		bonesRevealStates.add(BonesRevealState.DONE);
		bonesRevealStates.add(BonesRevealState.NA);

		List<EyeRevealState> eyeRevealStates = new ArrayList<EyeRevealState>();
		eyeRevealStates.add(EyeRevealState.DONE);
		eyeRevealStates.add(EyeRevealState.NA);

		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01

				return requestRepository
						.findByZoneIdAndStateAndBonesRevealStateInAndEyeRevealStateInAndCitizenMobileNumber(
								userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE, bonesRevealStates,
								eyeRevealStates, searchKey, page);

			} else {
				// assuming search key is national id
				return requestRepository
						.findByZoneIdAndStateAndBonesRevealStateInAndEyeRevealStateInAndCitizenNationalId(
								userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE, bonesRevealStates,
								eyeRevealStates, key, page);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);

					return requestRepository.findByZoneIdAndStateAndBonesRevealStateInAndEyeRevealStateInAndRequestDate(
							userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE, bonesRevealStates,
							eyeRevealStates, requestDate, page);

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {

				return requestRepository
						.findByZoneIdAndStateAndBonesRevealStateInAndEyeRevealStateInAndCitizenNameContaining(
								userService.getUserZoneId(), RequestState.CONTINUE_REGISTERING_DONE, bonesRevealStates,
								eyeRevealStates, searchKey, page);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_APPROVING')")
	public Page<Request> getRequestsForApproving(Pageable pageable) {
		return requestRepository.findByZoneIdAndState(userService.getUserZoneId(), RequestState.REVIEWED, pageable);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_APPROVING')")
	public Page<Request> getRequestsBySearchKeyForApproving(String searchKey, Pageable pageable) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndCitizenMobileNumber(userService.getUserZoneId(),
						RequestState.REVIEWED, searchKey, pageable);
			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndCitizenNationalId(userService.getUserZoneId(),
						RequestState.REVIEWED, key, pageable);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndRequestDate(userService.getUserZoneId(),
							RequestState.REVIEWED, requestDate, pageable);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndCitizenNameContaining(userService.getUserZoneId(),
						RequestState.REVIEWED, searchKey, pageable);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CITIZENS_REQUESTS_VIEWING')")
	public Page<Request> getApprovedRequests(Pageable pageable) {
		return requestRepository.findByZoneIdAndState(userService.getUserZoneId(), RequestState.APPROVED, pageable);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CITIZENS_REQUESTS_VIEWING')")
	public Page<Request> getApprovedRequestsBySearchKey(String searchKey, Pageable pageable) {
		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);

			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndStateAndCitizenMobileNumber(userService.getUserZoneId(),
						RequestState.APPROVED, searchKey, pageable);
			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndStateAndCitizenNationalId(userService.getUserZoneId(),
						RequestState.APPROVED, key, pageable);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndStateAndRequestDate(userService.getUserZoneId(),
							RequestState.APPROVED, requestDate, pageable);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return requestRepository.findByZoneIdAndStateAndCitizenNameContaining(userService.getUserZoneId(),
						RequestState.APPROVED, searchKey, pageable);
			}

		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CITIZENS_REQUESTS_VIEWING')")
	public Page<Request> getAllRequests(Pageable page) {
		return requestRepository.findByZoneId(userService.getUserZoneId(), page);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CITIZENS_REQUESTS_VIEWING')")
	public Page<Request> getAllRequestsBySearchKey(String searchKey, Pageable pageable) {

		// check if searchKey is number or string
		try {
			Long key = Long.parseLong(searchKey);
			// No Thrown exception, so searchKey is number
			// check if it is national id or mobile number
			if (searchKey.startsWith("01")) {
				// search key is mobile number because it starts with 01
				return requestRepository.findByZoneIdAndCitizenMobileNumber(userService.getUserZoneId(), searchKey,
						pageable);

			} else {
				// assuming search key is national id
				return requestRepository.findByZoneIdAndCitizenNationalId(userService.getUserZoneId(), key, pageable);
			}
		} catch (NumberFormatException | NullPointerException nfe) {
			
			if (searchKey.contains("-")) {
				// search key is date
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date requestDate = (Date) formatter.parse(searchKey);
					return requestRepository.findByZoneIdAndRequestDateGreaterThan(userService.getUserZoneId(), requestDate,
							pageable);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				// search key is name
				return requestRepository.findByZoneIdAndCitizenNameContaining(userService.getUserZoneId(), searchKey,
						pageable);
			}

		}

	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CITIZEN_REQUEST_REGISTERING') OR hasRole('ROLE_CITIZENS_DATA_EDITING')")
	public List<Request> getCitizenRequests(long citizenId) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		return requestRepository.findByZoneIdAndCitizenId(userService.getUserZoneId(), citizenId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CITIZENS_DATA_EDITING')")
	public void deleteRequest(long citizenId, long requestId) {
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
		String performedBy = userService.getUsername();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy, userService.getUserZoneId());
		auditRepository.save(audit);
//		} catch (EmptyResultDataAccessException ex) {
//			throw new ResourceNotFoundException("هذا الطلب غير موجود");
//		}
	}

	@Override
	public Request getRequestById(long requestId) {
		Optional<Request> request = requestRepository.findByZoneIdAndId(userService.getUserZoneId(), requestId);
		if (!request.isPresent())
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		return request.get();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CITIZEN_REQUEST_REGISTERING')")
	public Object createRequest(long citizenId, Request request) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("هذا المواطن غير موجود");
		}

		Optional<RequestType> requestType = requestTypeRepository.findById(request.getRequestType().getId());

		if (!requestType.isPresent()) {
			throw new ResourceNotFoundException("عفوا نوع الطلب غير موجود");
		}

		Zone zone = new Zone();
		zone.setId(userService.getUserZoneId());
		request.setZone(zone);

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
		String performedBy = userService.getUsername();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy, userService.getUserZoneId());
		auditRepository.save(audit);

		return savedRequest;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_CONTINUE_REGISTERING') OR hasRole('ROLE_CITIZENS_DATA_EDITING')")
	public ResponseEntity<Request> updateRequest(long citizenId, long requestId, Request request) {
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
		String performedBy = userService.getUsername();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy, userService.getUserZoneId());
		auditRepository.save(audit);

		return new ResponseEntity<Request>(updatedRequest, HttpStatus.OK);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_REVIEWING') OR hasRole('ROLE_CITIZENS_DATA_EDITING')")
	public void updateRequestStatus(long citizenId, long requestId, RequestStatus requestStatus) {
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
		String performedBy = userService.getUsername();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy, userService.getUserZoneId());
		auditRepository.save(audit);

	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_REVIEWING')")
	public void reviewRequest(long requestId, Request request) {
		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}

		requestRepository.setRequestState(requestId, RequestState.REVIEWED);

		// auditing
		String action = "مراجعة طلب";
		StringBuilder details = new StringBuilder("");
		details.append("لا يوجد");
		String performedBy = userService.getUsername();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy, userService.getUserZoneId());
		auditRepository.save(audit);

	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_REQUEST_APPROVING') ")
	public void approveRequest(long requestId, Request request) {
		if (!requestRepository.existsById(requestId)) {
			throw new ResourceNotFoundException("هذا الطلب غير موجود");
		}
		requestRepository.setRequestState(requestId, RequestState.APPROVED);

		// auditing
		String action = "اعتماد طلب";
		StringBuilder details = new StringBuilder("");
		details.append("لا يوجد");
		String performedBy = userService.getUsername();
		Audit audit = new Audit(action, details.toString(), requestId, performedBy, userService.getUserZoneId());
		auditRepository.save(audit);

	}

}