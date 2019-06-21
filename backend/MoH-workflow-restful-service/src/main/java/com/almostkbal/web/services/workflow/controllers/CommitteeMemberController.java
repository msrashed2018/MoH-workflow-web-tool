package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almostkbal.web.services.workflow.entities.CommitteeMember;
import com.almostkbal.web.services.workflow.repositories.CommitteeMemberRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class CommitteeMemberController {
	@Autowired
	private CommitteeMemberRepository committeeMemberRepository;
	
	@GetMapping("/api/committee-members")
	public Page<CommitteeMember> retrieveAllCommitteeMembers(@RequestParam("page") int page,
			@RequestParam("size") int size) {
		return committeeMemberRepository.findAll(PageRequest.of(page, size));
	}
	
	@GetMapping("/api/committee-members/{id}")
	public CommitteeMember retrieveCommitteeMemberById(@PathVariable long id) {
		Optional<CommitteeMember> committeeMember = committeeMemberRepository.findById(id);
		if(!committeeMember.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		Resource<CommitteeMember> resource = new Resource<CommitteeMember>(committeeMember.get());
		return committeeMember.get();
	}

	@DeleteMapping("/api/committee-members/{id}")
	public void deleteCommitteeMember(@PathVariable long id) {
		try {
			committeeMemberRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/committee-members")
	public ResponseEntity<Object> createCommitteeMember(@Valid @RequestBody CommitteeMember committeeMember) {
		
		System.out.println("\n\n Committte Member "+ committeeMember.getName());
		System.out.println(committeeMember.getDescription());
		System.out.println(committeeMember.getMobileNumber());
		System.out.println(committeeMember.getName());
		System.out.println(committeeMember.getTitle());
		System.out.println(committeeMember.getZone().getId());
		System.out.println(committeeMember.getZone().getName());
		System.out.println(committeeMember.getZone().getDescription());
		CommitteeMember savedCommitteeMember = committeeMemberRepository.save(committeeMember);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedCommitteeMember.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/committee-members/{id}")
	public ResponseEntity<CommitteeMember> updateCommitteeMember(
			@PathVariable long id, @Valid @RequestBody CommitteeMember committeeMember) {
		Optional<CommitteeMember> existingCommitteeMember = committeeMemberRepository.findById(id);
		
		if(!existingCommitteeMember.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		committeeMemberRepository.deleteById(id);
		CommitteeMember updatedCitzen = committeeMemberRepository.save(committeeMember);
		return new ResponseEntity<CommitteeMember>(updatedCitzen, HttpStatus.OK);
	}
}
