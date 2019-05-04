package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almostkbal.web.services.workflow.entities.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

}
