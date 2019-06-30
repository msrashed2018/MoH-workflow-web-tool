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

import com.almostkbal.web.services.workflow.entities.FileType;
import com.almostkbal.web.services.workflow.repositories.DocumentTypeRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DocumentTypeController {
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	
	@GetMapping("/api/document-types")
	public Page<FileType> retrieveAllDocumentTypes(@RequestParam("page") int page, @RequestParam("size") int size) {
		return documentTypeRepository.findAll(PageRequest.of(page, size));
	}
	
	@GetMapping("/api/document-types/{id}")
	public FileType retrieveDocumentTypeById(@PathVariable long id) {
		Optional<FileType> DocumentType = documentTypeRepository.findById(id);
		if(!DocumentType.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		Resource<DocumentType> resource = new Resource<DocumentType>(DocumentType.get());
		return DocumentType.get();
	}

	@DeleteMapping("/api/document-types/{id}")
	public void deleteDocumentType(@PathVariable long id) {
		try {
			documentTypeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/document-types")
	public ResponseEntity<Object> createDocumentType(@Valid @RequestBody FileType DocumentType) {
		FileType savedDocumentType = documentTypeRepository.save(DocumentType);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedDocumentType.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/document-types/{id}")
	public ResponseEntity<FileType> updateDocumentType(
			@PathVariable long id, @Valid @RequestBody FileType documentType) {
		Optional<FileType> existingDocumentType = documentTypeRepository.findById(id);
		
		if(!existingDocumentType.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
		FileType updatedDocumentType = documentTypeRepository.save(documentType);
		return new ResponseEntity<FileType>(updatedDocumentType, HttpStatus.OK);
	}
}
