package com.almostkbal.web.services.workflow.services;

import java.util.List;

import com.almostkbal.web.services.workflow.entities.Citizen;

public interface CitizenService {
	 
	List<Citizen> findAll();
	
	Citizen save(Citizen citizen);
	
	Citizen findById(long id);
	
	Citizen findByNationalId(long id);
	
	Citizen deleteById(long id);
	
	Citizen deleteByNationalId(long id);
}
