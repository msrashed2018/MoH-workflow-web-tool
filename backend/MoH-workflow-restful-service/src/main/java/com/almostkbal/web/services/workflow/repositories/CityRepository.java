package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.almostkbal.web.services.workflow.entities.City;

@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
public interface CityRepository extends JpaRepository<City, Integer> {

//	@Override
//	default <S extends City> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	default void deleteAll(Iterable<? extends City> entities) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
