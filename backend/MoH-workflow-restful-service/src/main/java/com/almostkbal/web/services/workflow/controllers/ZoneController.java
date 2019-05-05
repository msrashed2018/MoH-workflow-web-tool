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

import com.almostkbal.web.services.workflow.entities.Governate;
import com.almostkbal.web.services.workflow.entities.Zone;
import com.almostkbal.web.services.workflow.exceptions.ZoneNotFoundException;
import com.almostkbal.web.services.workflow.repositories.GovernateRepository;
import com.almostkbal.web.services.workflow.repositories.ZoneRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class ZoneController {
	@Autowired
	private ZoneRepository zoneRepository;
	
	@Autowired
	private GovernateRepository governateRepository;
	
	@GetMapping("/api/zones")
	public List<Zone> retrieveAllZones(){
		return zoneRepository.findAll();
	}
	
	@GetMapping("/api/zones/{id}")
	public Resource<Zone> retrieveZoneById(@PathVariable int id) {
		Optional<Zone> zone = zoneRepository.findById(id);
		if(!zone.isPresent())
			throw new ZoneNotFoundException("id-"+ id);
		Resource<Zone> resource = new Resource<Zone>(zone.get());
		return resource;
	}

	@DeleteMapping("/api/zones/{id}")
	public void deleteZone(@PathVariable int id) {
		try {
			zoneRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ZoneNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/zones")
	public ResponseEntity<Object> createZone(@Valid @RequestBody Zone zone) {
		Zone savedZone = zoneRepository.save(zone);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedZone.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/zones/{id}")
	public ResponseEntity<Zone> updateZone(
			@PathVariable int id, @RequestBody Zone zone){
		Optional<Zone> existingZone = zoneRepository.findById(id);

		if(!existingZone.isPresent())
			throw new ZoneNotFoundException("id-"+ id);
		zoneRepository.deleteById(id);
		Zone updatedCitzen = zoneRepository.save(zone);
		return new ResponseEntity<Zone>(updatedCitzen, HttpStatus.OK);
	}
	@PostMapping("/api/zones/{id}/governates")
	public ResponseEntity<Object> addCity(@PathVariable int id, @RequestBody Governate governate) {

		Optional<Zone> governateOptional = zoneRepository.findById(id);

		if (!governateOptional.isPresent()) {
			throw new ZoneNotFoundException("id-" + id);
		}

		Zone zone = governateOptional.get();

		governate.setZone(zone);

		governateRepository.save(governate);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(governate.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/api/zones/{id}/governates")
	public List<Governate> retrieveZoneGovernates(@PathVariable int id) {
		Optional<Zone> zoneOptional = zoneRepository.findById(id);
		if (!zoneOptional.isPresent()) {
			throw new ZoneNotFoundException("id-" + id);
		}
		Zone zone = zoneOptional.get();

		return governateRepository.findByZone(zone);
	}
}