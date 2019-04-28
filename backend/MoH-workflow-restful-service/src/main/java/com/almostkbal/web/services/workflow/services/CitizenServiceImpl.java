package com.almostkbal.web.services.workflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.almostkbal.web.services.workflow.entities.Citizen;
import com.almostkbal.web.services.workflow.repositories.CitizenRepository;

public class CitizenServiceImpl {
	@Autowired
	private  CitizenRepository citizenRepository;

	public List<Citizen> findAll() {
		return citizenRepository.findAll();
	}

	public Optional<Citizen> findById(Long id) {
		return citizenRepository.findById(id);
	}

	public Citizen save(Citizen citizen) {
		return citizenRepository.save(citizen);
	}

	public void deleteById(Long id) {
		citizenRepository.deleteById(id);
	}

}
