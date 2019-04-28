package com.almostkbal.web.services.workflow.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.almostkbal.web.services.workflow.entities.Citizen;

//@PreAuthorize("isAuthenticated()")

@RepositoryRestResource(collectionResourceRel = "citizens", path = "citizens")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	
//	@RestResource(path = "nationalId/${id}", rel="nationalId/${id}")
	Citizen findByNationalId(@Param("id") long id);
	
	List<Citizen> findByName(String name);
	
	List<Citizen> findAllByCreatedDate(Date createdDate);
	
//	@Query("SELECT c FROM Citizen c WHERE c.name LIKE CONCAT('%',:name,'%')")
	List<Citizen> findByNameContaining(String name);
}
