package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almostkbal.web.services.workflow.entities.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
