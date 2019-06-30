package com.almostkbal.web.services.workflow.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.almostkbal.web.services.workflow.entities.DocumentType;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestDocument;
import com.almostkbal.web.services.workflow.exceptions.ExceptionResponse;
import com.almostkbal.web.services.workflow.repositories.RequestDocumentRepository;
import com.almostkbal.web.services.workflow.repositories.RequestRepository;
import com.almostkbal.web.services.workflow.services.StorageService;
import com.google.common.net.HttpHeaders;

//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins = "*")
@RestController
public class RequestDocumentController {

	@Autowired
	StorageService storageService;

	@Autowired
	RequestDocumentRepository documentRepository;
	
	@Autowired
	RequestRepository requestRepository;
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@PostMapping(value = "/api/requests/{id}/documents/{documentType}" /* consumes = "application/pdf" */)
	public ResponseEntity<String> addRequestDocuments(@PathVariable long id,
			@PathVariable DocumentType documentType, @RequestParam("file") MultipartFile[] uploadedFiles) {
		String message = "";
		String currentUploadFileName = "";
		try {

			for (MultipartFile file : uploadedFiles) {
				currentUploadFileName = file.getOriginalFilename();
				if(!file.getContentType().equals("application/pdf")) {
					 ExceptionResponse exceptionResponse = 
							 new ExceptionResponse(new Date(), "file[ "+currentUploadFileName+" ] type is not supported ", "file[ "+currentUploadFileName+" ] type is not supported ");
				        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
				}
				
				log.info("store file :" + currentUploadFileName);
				String storedPath = storageService.store(id, documentType, currentUploadFileName, file);
				
				RequestDocument document = new RequestDocument();
				document.setName(currentUploadFileName);
				document.setPath(storedPath);
				document.setType(documentType);
				Request request = requestRepository.getOne(id);
				
				document.setRequest(request);
//				files.add(file.getOriginalFilename());
				
				documentRepository.save(document);
				
				message = currentUploadFileName +" is successfully stored...";
			}
			return ResponseEntity.status(HttpStatus.OK).body(message);

		} catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Request with ID "+id + " is not found");
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			message = "FAIL to store " + currentUploadFileName + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}


	@GetMapping("/api/requests/{id}/documents/{documentType}")
	public ResponseEntity<List<String>> getListFiles(@PathVariable long id, @PathVariable DocumentType documentType,
			Model model) {
		List<String> files = new ArrayList<String>();
		
		log.info("reteive document of request(id="+id+")");
		
		if(!requestRepository.existsById(id)) {
			throw new ResourceNotFoundException("Request with ID "+id + " is not found");
		}
		List<RequestDocument> documents = documentRepository.findByRequestIdAndType(id, documentType);
		for(RequestDocument document : documents) {
			log.info("document[ name = "+document.getName()+ ", path = "+document.getPath()); 
			files.add(document.getName());
		}

		return ResponseEntity.ok().body(files);
	}

	@GetMapping("/api/requests/{id}/document/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable long id, @PathVariable String filename) {
		
		RequestDocument document = documentRepository.findByRequestIdAndName(id, filename);
		if(document == null) {
			throw new ResourceNotFoundException("document["+filename+"] is not found");
		}
		log.info("getFile , request-ID="+id+ " docuemnt-path="+ document.getPath() +"  filename="+document.getName());
		Resource file = storageService.loadFile(document.getPath(),document.getName());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
