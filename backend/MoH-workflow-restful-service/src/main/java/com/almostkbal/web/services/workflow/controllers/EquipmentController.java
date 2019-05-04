package com.almostkbal.web.services.workflow.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import com.almostkbal.web.services.workflow.entities.Equipment;
import com.almostkbal.web.services.workflow.exceptions.EquipmentNotFoundException;
import com.almostkbal.web.services.workflow.repositories.EquipmentRepository;


//@CrossOrigin(origins="http://192.168.0.100:4200")
@CrossOrigin(origins="*")
@RestController
public class EquipmentController {
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@GetMapping("/api/equipments")
	public List<Equipment> retrieveAllEquipments(){
		return equipmentRepository.findAll();
	}
	
	@GetMapping("/api/equipments/{id}")
	public Equipment retrieveEquipmentById(@PathVariable int id) {
		Optional<Equipment> equipment = equipmentRepository.findById(id);
		if(!equipment.isPresent())
			throw new EquipmentNotFoundException("id-"+ id);
//		Resource<Equipment> resource = new Resource<Equipment>(equipment.get());
		return equipment.get();
	}

	@DeleteMapping("/api/equipments/{id}")
	public void deleteEquipment(@PathVariable int id) {
		try {
			equipmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new EquipmentNotFoundException("id-"+ id);
	    }
		
	}

	@PostMapping("/api/equipments")
	public ResponseEntity<Object> createEquipment(@Valid @RequestBody Equipment equipment) {
		Equipment savedEquipment = equipmentRepository.save(equipment);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEquipment.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/api/equipments/{id}")
	public ResponseEntity<Equipment> updateEquipment(
			@PathVariable int id, @RequestBody Equipment equipment){
		Optional<Equipment> existingEquipment = equipmentRepository.findById(id);

		if(!existingEquipment.isPresent())
			throw new EquipmentNotFoundException("id-"+ id);
//		equipmentRepository.deleteById(id);
		Equipment updatedCitzen = equipmentRepository.save(equipment);
		return new ResponseEntity<Equipment>(updatedCitzen, HttpStatus.OK);
	}
}
