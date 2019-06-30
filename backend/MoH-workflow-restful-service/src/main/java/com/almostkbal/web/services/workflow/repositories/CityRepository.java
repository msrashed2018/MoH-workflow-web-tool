package com.almostkbal.web.services.workflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almostkbal.web.services.workflow.entities.City;
import com.almostkbal.web.services.workflow.entities.Governate;

//@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
public interface CityRepository extends JpaRepository<City, Integer> {
//	@Query(value = "SELECT c FROM City c WHERE c.governate = :governate")
//	List<City> findByGovernate(@Param("governate") Governate governate);
	
	List<City> findByGovernateId(int id);
}
