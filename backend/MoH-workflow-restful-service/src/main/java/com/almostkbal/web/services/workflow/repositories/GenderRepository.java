package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.almostkbal.web.services.workflow.entities.Gender;

@RepositoryRestResource(collectionResourceRel = "genders", path = "genders")
public interface GenderRepository extends PagingAndSortingRepository<Gender, Integer> {

}
