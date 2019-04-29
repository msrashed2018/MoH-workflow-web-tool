package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.almostkbal.web.services.workflow.entities.Occupation;

//@RepositoryRestResource(collectionResourceRel = "occupations", path = "occupations")
public interface OccupationRepository extends JpaRepository<Occupation, Integer> {

}
