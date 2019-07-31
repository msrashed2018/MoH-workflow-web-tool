package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almostkbal.web.services.workflow.auth.UserService;
import com.almostkbal.web.services.workflow.entities.Committee;
import com.almostkbal.web.services.workflow.entities.Zone;
import com.almostkbal.web.services.workflow.exceptions.CommitteeValidationException;
import com.almostkbal.web.services.workflow.exceptions.ExceptionResponse;
import com.almostkbal.web.services.workflow.repositories.CommitteeRepository;

//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins = "*")
@RestController
public class CommitteeController {
	@Autowired
	private CommitteeRepository committeeRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/api/committees")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SYSTEM_TABLES_MAINTENANCE') OR hasRole('ROLE_COMMITTEES_REGISTERING')")
	public Page<Committee> retrieveAllCommittees(@RequestParam("page") int page, @RequestParam("size") int size) {
		return committeeRepository.findByZoneId(userService.getUserZoneId(), PageRequest.of(page, size));
	}

	@GetMapping("/api/committees/findUpcommingCommitteesByTypeAndFunction")
	public List<Committee> retrieveUpcommingCommitteesByTypeAndFunction(@RequestParam String type,
			@RequestParam String function) {
		return committeeRepository.findByZoneIdAndTypeAndDateGreaterThanAndFunction(userService.getUserZoneId(), type,
				yesterday(), function);
	}

	@GetMapping("/api/committees/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SYSTEM_TABLES_MAINTENANCE') OR hasRole('ROLE_COMMITTEES_REGISTERING')")
	public Committee retrieveCommitteeById(@PathVariable long id) {
		Optional<Committee> committee = committeeRepository.findById(id);
		if (!committee.isPresent())
			throw new ResourceNotFoundException("id-" + id);
		return committee.get();
	}

	@DeleteMapping("/api/committees/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SYSTEM_TABLES_MAINTENANCE') OR hasRole('ROLE_COMMITTEES_REGISTERING')")
	public void deleteCommittee(@PathVariable long id) {
		try {
			committeeRepository.deleteByIdAndZoneId(id, userService.getUserZoneId());
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-" + id);
		}
	}

	@PostMapping("/api/committees")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SYSTEM_TABLES_MAINTENANCE') OR hasRole('ROLE_COMMITTEES_REGISTERING')")
	public Object createCommittee(@Valid @RequestBody Committee committee) {
		CommitteeValidationUtils.validateCommitteeRepeatedMembers(committee);
		boolean isMemberAssignedToAnotherCommittee = false;
		String memberName = "";
		System.out.println("\n\n\n \n\n committee date = "+committee.getDate()+" \n\n\n\n\n");
		if (committee.getMemberOne() != null && committeeRepository.existsByZoneIdAndMemberOneIdAndDate(
				userService.getUserZoneId(), committee.getMemberOne().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberOne().getName();
		}
		if (committee.getMemberTwo() != null && committeeRepository.existsByZoneIdAndMemberTwoIdAndDate(
				userService.getUserZoneId(), committee.getMemberTwo().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberTwo().getName();
		}
		if (committee.getMemberThree() != null && committeeRepository.existsByZoneIdAndMemberThreeIdAndDate(
				userService.getUserZoneId(), committee.getMemberThree().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberThree().getName();
		}
		if (committee.getMemberFour() != null && committeeRepository.existsByZoneIdAndMemberFourIdAndDate(
				userService.getUserZoneId(), committee.getMemberFour().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberFour().getName();
		}
		if (committee.getMemberFive() != null && committeeRepository.existsByZoneIdAndMemberFiveIdAndDate(
				userService.getUserZoneId(), committee.getMemberFive().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberFive().getName();
		}
		if (committee.getMemberSix() != null && committeeRepository.existsByZoneIdAndMemberSixIdAndDate(
				userService.getUserZoneId(), committee.getMemberSix().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberSix().getName();
		}

		if (isMemberAssignedToAnotherCommittee) {

			StringBuilder errorMessage = new StringBuilder("");
			errorMessage.append(" تم تسجيل العضو ");
			errorMessage.append(memberName);
			errorMessage.append(" في لجنة اخري في نفس التاريخ  ");
			errorMessage.append(committee.getDate().toString());
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(new Date(), errorMessage.toString(),errorMessage.toString()),
					HttpStatus.BAD_REQUEST);
		}

		Zone zone = new Zone();
		zone.setId(userService.getUserZoneId());
		committee.setZone(zone);
		Committee savedCommittee = committeeRepository.save(committee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCommittee.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/api/committees/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SYSTEM_TABLES_MAINTENANCE') OR hasRole('ROLE_COMMITTEES_REGISTERING')")
	public Object updateCommittee(@PathVariable long id, @Valid @RequestBody Committee committee) {

		if (!committeeRepository.existsById(id))
			throw new ResourceNotFoundException("هذه اللجنة غير موجوده");

		CommitteeValidationUtils.validateCommitteeRepeatedMembers(committee);

		boolean isMemberAssignedToAnotherCommittee = false;
		String memberName = "";

		if (committee.getMemberOne() != null && committeeRepository.existsByZoneIdAndMemberOneIdAndDate(
				userService.getUserZoneId(), committee.getMemberOne().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberOne().getName();
		}
		if (committee.getMemberTwo() != null && committeeRepository.existsByZoneIdAndMemberTwoIdAndDate(
				userService.getUserZoneId(), committee.getMemberTwo().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberTwo().getName();
		}
		if (committee.getMemberThree() != null && committeeRepository.existsByZoneIdAndMemberThreeIdAndDate(
				userService.getUserZoneId(), committee.getMemberThree().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberThree().getName();
		}
		if (committee.getMemberFour() != null && committeeRepository.existsByZoneIdAndMemberFourIdAndDate(
				userService.getUserZoneId(), committee.getMemberFour().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberFour().getName();
		}
		if (committee.getMemberFive() != null && committeeRepository.existsByZoneIdAndMemberFiveIdAndDate(
				userService.getUserZoneId(), committee.getMemberFive().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberFive().getName();
		}
		if (committee.getMemberSix() != null && committeeRepository.existsByZoneIdAndMemberSixIdAndDate(
				userService.getUserZoneId(), committee.getMemberSix().getId(), committee.getDate())) {
			isMemberAssignedToAnotherCommittee = true;
			memberName = committee.getMemberSix().getName();
		}

		if (isMemberAssignedToAnotherCommittee) {

			StringBuilder errorMessage = new StringBuilder("");
			errorMessage.append(" تم تسجيل العضو ");
			errorMessage.append(memberName);
			errorMessage.append(" في لجنة اخري في نفس التاريخ  ");
			errorMessage.append(committee.getDate().toString());
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(new Date(), errorMessage.toString(), errorMessage.toString()),
					HttpStatus.BAD_REQUEST);
		}
		Zone zone = new Zone();
		zone.setId(userService.getUserZoneId());
		committee.setZone(zone);
		Committee updatedCitzen = committeeRepository.save(committee);
		return new ResponseEntity<Committee>(updatedCitzen, HttpStatus.OK);
	}

	private Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	static class CommitteeValidationUtils {
		@Autowired
		private CommitteeRepository committeeRepository;

		public static void validateCommitteeRepeatedMembers(Committee committee) {
			Set<Long> committeeMemberIds = new HashSet<Long>();
			String memberName = "";

			boolean repeated = false;

			boolean isMemberAssignedToAnotherCommittee = false; // to check if the member is assigned
			if (committee.getMemberOne() != null) {
				if (!committeeMemberIds.add(committee.getMemberOne().getId())) {
					repeated = true;
					memberName = committee.getMemberOne().getName();
				}

			}
			if (committee.getMemberTwo() != null) {
				if (!committeeMemberIds.add(committee.getMemberTwo().getId())) {
					repeated = true;
					memberName = committee.getMemberTwo().getName();
				}
			}
			if (committee.getMemberThree() != null) {
				if (!committeeMemberIds.add(committee.getMemberThree().getId())) {
					repeated = true;
					memberName = committee.getMemberThree().getName();
				}
			}
			if (committee.getMemberFour() != null) {
				if (!committeeMemberIds.add(committee.getMemberFour().getId())) {
					repeated = true;
					memberName = committee.getMemberFour().getName();
				}
			}
			if (committee.getMemberFive() != null) {
				if (!committeeMemberIds.add(committee.getMemberFive().getId())) {
					repeated = true;
					memberName = committee.getMemberFive().getName();
				}
			}
			if (committee.getMemberSix() != null) {
				if (!committeeMemberIds.add(committee.getMemberSix().getId())) {
					repeated = true;
					memberName = committee.getMemberSix().getName();
				}
			}

			if (repeated) {
				StringBuilder errorMessage = new StringBuilder("");
				errorMessage.append(" لا يمكن إضافة العضو ");
				errorMessage.append(memberName);
				errorMessage.append(" في اللجنة اكثر من مره  ");
				throw new CommitteeValidationException(errorMessage.toString());
			}
		}

	}

}
