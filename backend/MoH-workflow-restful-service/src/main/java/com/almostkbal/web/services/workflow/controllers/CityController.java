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

import com.almostkbal.web.services.workflow.entities.City;
import com.almostkbal.web.services.workflow.repositories.CityRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class CityController {
	@Autowired
	private CityRepository cityRepository;
	
	@GetMapping("/api/cities")
	public List<City> retrieveAllCities(){
		return cityRepository.findAll();
	}
	
	@GetMapping("/api/cities/{id}")
	public City retrieveCityById(@PathVariable int id) {
		Optional<City> city = cityRepository.findById(id);
		if(!city.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		Resource<City> resource = new Resource<City>(city.get());
		return city.get();
	}

	@DeleteMapping("/api/cities/{id}")
	public void deleteCity(@PathVariable int id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("id-"+ id);
	    }
	}

	@PostMapping("/api/cities")
	public ResponseEntity<Object> createCity(@Valid @RequestBody City city) {
		City savedCity = cityRepository.save(city);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedCity.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/cities/{id}")
	public ResponseEntity<City> updateCity(
			@PathVariable int id, @RequestBody City city){
		Optional<City> existingCity = cityRepository.findById(id);

		if(!existingCity.isPresent())
			throw new ResourceNotFoundException("id-"+ id);
//		cityRepository.deleteById(id);
		City updatedCitzen = cityRepository.save(city);
		return new ResponseEntity<City>(updatedCitzen, HttpStatus.OK);
	}
}
