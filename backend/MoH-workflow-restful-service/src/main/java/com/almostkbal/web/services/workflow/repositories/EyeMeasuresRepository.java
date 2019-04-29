package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.almostkbal.web.services.workflow.entities.Disability;
import com.almostkbal.web.services.workflow.entities.EyeMeasures;

@RepositoryRestResource(collectionResourceRel = "eye-measures", path = "eye-measures")
public interface EyeMeasuresRepository extends JpaRepository<EyeMeasures, Integer> {
	
}
