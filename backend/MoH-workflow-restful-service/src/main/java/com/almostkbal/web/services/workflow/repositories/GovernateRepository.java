package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.almostkbal.web.services.workflow.entities.Governate;

//@CrossOrigin(origins="http://localhost:4201")
@RepositoryRestResource(collectionResourceRel = "governates", path = "governates")
public interface GovernateRepository extends JpaRepository<Governate, Integer> {

}
