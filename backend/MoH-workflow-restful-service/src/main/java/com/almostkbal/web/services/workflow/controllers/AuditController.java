package com.almostkbal.web.services.workflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.Audit;
import com.almostkbal.web.services.workflow.repositories.AuditRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AuditController {
	@Autowired
	private AuditRepository auditRepository;
	
	@GetMapping("/api/audits")
	public Page<Audit> retrieveAllAudits(@RequestParam("page") int page, @RequestParam("size") int size) {
		return auditRepository.findAll(PageRequest.of(page, size));
	}
	
	@DeleteMapping("/api/audits/{id}")
	public void deleteAudit(@PathVariable long id) {
		try {
			auditRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-"+ id);
	    }
	}
	
	@DeleteMapping("/api/audits")
	public void deleteAllAudits() {
		try {
			auditRepository.deleteAllInBatch();
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("audits");
	    }
	}
	
//	@GetMapping("/api/audits/{id}")
//	public Audit retrieveAuditById(@PathVariable int id) {
//		Optional<Audit> Audit = AuditRepository.findById(id);
//		if(!Audit.isPresent())
//			throw new ResourceNotFoundException("id-"+ id);
////		Resource<Audit> resource = new Resource<Audit>(Audit.get());
//		return Audit.get();
//	}



//	@PostMapping("/api/audits")
//	public ResponseEntity<Object> createAudit(@Valid @RequestBody Audit Audit) {
//		Audit savedAudit = AuditRepository.save(Audit);
//		URI location = ServletUriComponentsBuilder
//			.fromCurrentRequest()
//			.path("/{id}")
//			.buildAndExpand(savedAudit.getId()).toUri();
//		return ResponseEntity.created(location).build();
//		
//	}
//	@PutMapping("/api/audits/{id}")
//	public ResponseEntity<Audit> updateAudit(
//			@PathVariable int id, @Valid @RequestBody Audit Audit) {
//		Optional<Audit> existingAudit = AuditRepository.findById(id);
//		
//		if(!existingAudit.isPresent())
//			throw new ResourceNotFoundException("id-"+ id);
////		AuditRepository.deleteById(id);
//		Audit updatedCitzen = AuditRepository.save(Audit);
//		return new ResponseEntity<Audit>(updatedCitzen, HttpStatus.OK);
//	}
}