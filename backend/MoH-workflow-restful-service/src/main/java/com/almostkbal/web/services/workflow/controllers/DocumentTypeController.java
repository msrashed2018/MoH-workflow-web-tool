package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almostkbal.web.services.workflow.entities.DocumentType;
import com.almostkbal.web.services.workflow.repositories.DocumentTypeRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class DocumentTypeController {
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	
	@GetMapping("/api/document-type")
	public List<DocumentType> retrieveAllDocumentTypes(){
		return documentTypeRepository.findAll();
	}
	
	@GetMapping("/api/document-type/{id}")
	public DocumentType retrieveDocumentTypeById(@PathVariable long id) {
		Optional<DocumentType> documentType = documentTypeRepository.findById(id);
		if(!documentType.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		Resource<DocumentType> resource = new Resource<DocumentType>(documentType.get());
		return documentType.get();
	}

	@DeleteMapping("/api/document-type/{id}")
	public void deleteDocumentType(@PathVariable long id) {
		try {
			documentTypeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/document-type")
	public ResponseEntity<Object> createDocumentType(@Valid @RequestBody DocumentType documentType) {
		DocumentType savedDocumentType = documentTypeRepository.save(documentType);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedDocumentType.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/document-type/{id}")
	public ResponseEntity<DocumentType> updateDocumentType(
			@PathVariable long id, @RequestBody DocumentType documentType){
		Optional<DocumentType> existingDocumentType = documentTypeRepository.findById(id);

		if(!existingDocumentType.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		documentTypeRepository.deleteById(id);
		DocumentType updatedCitzen = documentTypeRepository.save(documentType);
		return new ResponseEntity<DocumentType>(updatedCitzen, HttpStatus.OK);
	}
}
