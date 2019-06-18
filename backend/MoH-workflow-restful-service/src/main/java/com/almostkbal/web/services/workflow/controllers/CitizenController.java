package com.almostkbal.web.services.workflow.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.exceptions.CitizenValidationException;
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;

//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins = "*")
@RestController
public class CitizenController {
	@Autowired
	private CitizenRepository citizenRepository;

	@GetMapping("/api/citizens")
	public List<Citizen> retrieveAllCitizens() {
		return citizenRepository.findAll();
	}

	@GetMapping("/api/citizens/search/findByNationalId")
	public List<Citizen> findByNationalId(@RequestParam long id) {
		return citizenRepository.findByNationalId(id);
	}

	@GetMapping("/api/citizens/search/findAllByName")
	public List<Citizen> findAllByName(@RequestParam String name) {
		return citizenRepository.findByName(name);
	}

	@GetMapping("/api/citizens/search/findAllByNameContaining")
	public List<Citizen> findAllByNameContaining(@RequestParam String name) {
		return citizenRepository.findByNameContaining(name);
	}

	@GetMapping("/api/citizens/search/findAllByDate")
	public List<Citizen> findAllByDate(@RequestParam String date) {

		return citizenRepository.findAllByDate(date);
	}

	@GetMapping("/api/citizens/{id}")
	public Citizen retrieveCitizenById(@PathVariable long id) {
		Optional<Citizen> citizen = citizenRepository.findById(id);
		if (!citizen.isPresent())
			throw new ResourceNotFoundException("id-" + id);
//		Resource<Citizen> resource = new Resource<Citizen>(citizen.get());
		return citizen.get();
	}

	@DeleteMapping("/api/citizens/{id}")
	public void deleteCitizen(@PathVariable long id) {
		try {
			citizenRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-" + id);
		}
	}

	@PostMapping("/api/citizens")
	public Object createCitizen(@Valid @RequestBody Citizen citizen) {
		Citizen savedCitizen = null;
		try {
			savedCitizen = citizenRepository.save(citizen);
			return savedCitizen;
		} catch (DataIntegrityViolationException ex) {
			throw new CitizenValidationException("هذا الرقم القومي يوجد بالفعل");
			
		}
	}

	@PutMapping("/api/citizens/{id}")
	public ResponseEntity<Citizen> updateCitizen(@PathVariable long id, @Valid @RequestBody Citizen citizen) {
		Optional<Citizen> existingCitizen = citizenRepository.findById(id);

		if (!existingCitizen.isPresent())
			throw new ResourceNotFoundException("id-" + id);
//		citizenRepository.deleteById(id);

		Citizen updatedCitzen = null;

		try {
			updatedCitzen = citizenRepository.save(citizen);
			return new ResponseEntity<Citizen>(updatedCitzen, HttpStatus.OK);
		} catch (DataIntegrityViolationException ex) {
			throw new CitizenValidationException("هذا الرقم القومي يوجد بالفعل");
			
		}
		
		
	}
}
