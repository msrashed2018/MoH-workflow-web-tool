package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;


//@CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins="*")

@RestController
public class CitizenController {
	@Autowired
	private CitizenRepository citizenRepository;
	
	@GetMapping("/api/citizens")
	public List<Citizen> retrieveCitizenById(){
		return citizenRepository.findAll();
	}
	
	@GetMapping("/api/citizens/search/findByNationalId")
	public Citizen findByNationalId(@RequestParam long id){
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
		Citizen citizen = citizenRepository.findByNationalId(id);
		
		if(citizen==null)
			throw new CitizenNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		Resource<Citizen> resource = new Resource<Citizen>(citizen);
		
//		ControllerLinkBuilder linkTo = 
//				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
//		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}

	@DeleteMapping("/api/citizens/{id}")
	public void deleteUser(@PathVariable long id) {
		citizenRepository.deleteById(id);
//		User user = service.deleteById(id);
//		
//		if(user==null)
//			throw new CitizenNotFoundException("id-"+ id);		
	}

	//
	// input - details of user
	// output - CREATED & Return the created URI
	
	//HATEOAS
	
	@PostMapping("/api/citizens")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Citizen citizen) {
		Citizen savedUser = citizenRepository.save(citizen);
		// CREATED
		// /user/{id}     savedUser.getId()
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/citizens/{id}")
	public ResponseEntity<Citizen> updateCitizen(
			@PathVariable long id, @RequestBody Citizen citizen){
		Citizen existingCitizen = citizenRepository.getOne(id);
		
		if(existingCitizen == null)
			throw new CitizenNotFoundException("id-"+ id);
		citizenRepository.deleteById(id);
		
		Citizen updatedCitzen = citizenRepository.save(citizen);
		
		return new ResponseEntity<Citizen>(updatedCitzen, HttpStatus.OK);
	}
}
