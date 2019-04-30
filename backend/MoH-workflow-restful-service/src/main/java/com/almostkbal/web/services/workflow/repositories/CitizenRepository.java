package com.almostkbal.web.services.workflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.almostkbal.web.services.workflow.entities.Citizen;

//@PreAuthorize("isAuthenticated()")

//@RepositoryRestResource(collectionResourceRel = "citizens", path = "citizens")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
//@CrossOrigin(origins= "http://192.168.0.100:4200")
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	
//	@RestResource(path = "nationalId/${id}", rel="nationalId/${id}")
	List<Citizen> findByNationalId(@Param("id") long id);
	
	List<Citizen> findByName(String name);
	
//	@Query(value = "select * from citizen where TRUNC(created_date)=TO_DATE(:date, 'YYYY-MM-DD')",nativeQuery=true)
	@Query(value = "SELECT c FROM Citizen c WHERE TRUNC(c.createdDate) = TO_DATE(:date, 'yyyy-MM-dd')")
	List<Citizen> findAllByDate(@Param("date") String date);
	
//	@Query("SELECT c FROM Citizen c WHERE c.name LIKE CONCAT('%',:name,'%')")
	List<Citizen> findByNameContaining(String name);
}
