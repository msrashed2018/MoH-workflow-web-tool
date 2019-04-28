package com.almostkbal.web.services.workflow.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almostkbal.web.services.workflow.entities.City;
import com.almostkbal.web.services.workflow.entities.Governate;
import com.almostkbal.web.services.workflow.repositories.CityRepository;
import com.almostkbal.web.services.workflow.repositories.GovernateRepository;

//import oracle.jdbc.proxy.annotation.Post;

//@RestController
//@RequestMapping("api")
public class CityJPAResource {

//	@Autowired
//	private CityRepository cityRepository;
//	
//	@Autowired
//	private GovernateRepository governateRepository;
//
//	@GetMapping("/cities")
//	public List<City> retrieveAllCitys() {
//		return cityRepository.findAll();
//	}
//
//	@GetMapping("/cities/{id}")
//	public Resource<City> retrieveCity(@PathVariable int id) {
//		Optional<City> city = cityRepository.findById(id);
//		city.get().getGovernate()
//		if (!city.isPresent())
//			throw new CityNotFoundException("id-" + id);
//
//		// "all-users", SERVER_PATH + "/users"
//		// retrieveAllCitys
//		Resource<City> resource = new Resource<City>(city.get());
//
//		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllCitys());
//
//		resource.add(linkTo.withRel("all-cities"));
//
//		// HATEOAS
//
//		return resource;
//	}
//
//	@DeleteMapping("/cities/{id}")
//	public void deleteCity(@PathVariable int id) {
//		cityRepository.deleteById(id);
//	}
//
//	//
//	// input - details of user
//	// output - CREATED & Return the created URI
//
//	// HATEOAS
//
//	@PostMapping("/cities")
//	public ResponseEntity<Object> createCity(@Valid @RequestBody City city) {
//		Governate governate = city.getGovernate();
//		System.out.println("\n\n governateId = "+governate.getId() + "governate Name = "+governate.getGovernateName()+"\n\n");
//		City savedCity = cityRepository.save(city);
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCity.getId())
//				.toUri();
//
//		return ResponseEntity.created(location).build();
//
//	}
//	
//	@GetMapping("/cities/{id}/governate")
//	public List<Post> retrieveAllCitys(@PathVariable int id) {
//		Optional<City> userOptional = cityRepository.findById(id);
//		
//		if(!userOptional.isPresent()) {
//			throw new CityNotFoundException("id-" + id);
//		}
//		
//		return userOptional.get().getPosts();
//	}


//	@PostMapping("/cities/{id}/governate")
//	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
//		
//		Optional<City> userOptional = cityRepository.findById(id);
//		
//		if(!userOptional.isPresent()) {
//			throw new CityNotFoundException("id-" + id);
//		}
//
//		City user = userOptional.get();
//		
//		post.setCity(user);
//		
//		governateRepository.save(post);
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
//				.toUri();
//
//		return ResponseEntity.created(location).build();
//
//	}

}
