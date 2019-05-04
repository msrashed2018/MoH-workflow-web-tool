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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.entities.User;
import com.almostkbal.web.services.workflow.exceptions.CitizenNotFoundException;
import com.almostkbal.web.services.workflow.exceptions.EquipmentNotFoundException;
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class CitizenController {
	@Autowired
	private CitizenRepository citizenRepository;
	
	@GetMapping("/api/citizens")
	public List<Citizen> retrieveAllCitizens(){
		return citizenRepository.findAll();
	}
	
	@GetMapping("/api/citizens/search/findByNationalId")
	public List<Citizen> findByNationalId(@RequestParam long id){
		return citizenRepository.findByNationalId(id);
	}
	
	@GetMapping("/api/citizens/search/findAllByName")
	public List<Citizen> findAllByName(@RequestParam String name){
		return citizenRepository.findByName(name);
	}
	
	@GetMapping("/api/citizens/search/findAllByNameContaining")
	public List<Citizen> findAllByNameContaining(@RequestParam String name){
		return citizenRepository.findByNameContaining(name);
	}
	@GetMapping("/api/citizens/search/findAllByDate")
	public List<Citizen> findAllByDate(@RequestParam String date){
		
		return citizenRepository.findAllByDate(date);
	}
	
	@GetMapping("/api/citizens/{id}")
	public Resource<Citizen> retrieveCitizenById(@PathVariable long id) {
		Optional<Citizen> citizen = citizenRepository.findById(id);
		if(!citizen.isPresent())
			throw new CitizenNotFoundException("id-"+ id);
		Resource<Citizen> resource = new Resource<Citizen>(citizen.get());
		return resource;
	}

	@DeleteMapping("/api/citizens/{id}")
	public void deleteCitizen(@PathVariable long id) {
		try {
			citizenRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new CitizenNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/citizens")
	public ResponseEntity<Object> createCitizen(@Valid @RequestBody Citizen citizen) {
		Citizen savedCitizen = citizenRepository.save(citizen);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedCitizen.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	@PutMapping("/api/citizens/{id}")
	public ResponseEntity<Citizen> updateCitizen(
			@PathVariable long id, @RequestBody Citizen citizen){
		Optional<Citizen> existingCitizen = citizenRepository.findById(id);

		if(!existingCitizen.isPresent())
			throw new CitizenNotFoundException("id-"+ id);
//		citizenRepository.deleteById(id);
		
		Citizen updatedCitzen = citizenRepository.save(citizen);
		
		return new ResponseEntity<Citizen>(updatedCitzen, HttpStatus.OK);
	}
}
