package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.Resource;
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

import com.almostkbal.web.services.workflow.entities.TrafficManagement;
import com.almostkbal.web.services.workflow.exceptions.TrafficManagementNotFoundException;
import com.almostkbal.web.services.workflow.repositories.TrafficManagementRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class TrafficManagementController {
	@Autowired
	private TrafficManagementRepository cityRepository;
	
	@GetMapping("/api/traffic-management")
	public List<TrafficManagement> retrieveAllTrafficManagements(){
		return cityRepository.findAll();
	}
	
	@GetMapping("/api/traffic-management/{id}")
	public Resource<TrafficManagement> retrieveTrafficManagementById(@PathVariable int id) {
		Optional<TrafficManagement> city = cityRepository.findById(id);
		if(!city.isPresent())
			throw new TrafficManagementNotFoundException("id-"+ id);
		Resource<TrafficManagement> resource = new Resource<TrafficManagement>(city.get());
		return resource;
	}

	@DeleteMapping("/api/traffic-management/{id}")
	public void deleteTrafficManagement(@PathVariable int id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new TrafficManagementNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/traffic-management")
	public ResponseEntity<Object> createTrafficManagement(@Valid @RequestBody TrafficManagement city) {
		TrafficManagement savedTrafficManagement = cityRepository.save(city);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedTrafficManagement.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/traffic-management/{id}")
	public ResponseEntity<TrafficManagement> updateTrafficManagement(
			@PathVariable int id, @RequestBody TrafficManagement city){
		Optional<TrafficManagement> existingTrafficManagement = cityRepository.findById(id);

		if(!existingTrafficManagement.isPresent())
			throw new TrafficManagementNotFoundException("id-"+ id);
//		cityRepository.deleteById(id);
		TrafficManagement updatedCitzen = cityRepository.save(city);
		return new ResponseEntity<TrafficManagement>(updatedCitzen, HttpStatus.OK);
	}
}
