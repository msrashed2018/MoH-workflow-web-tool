package com.almostkbal.web.services.workflow.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.almostkbal.web.services.workflow.entities.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {
	
	Page<Audit> findByZoneId(long zoneId, Pageable pageable);
	
	Page<Audit> findByZoneIdAndTimestamp(long zoneId, Date timestamp, Pageable pageable);
	
	Page<Audit> findByZoneIdAndRequestId(long zoneId, long requestId, Pageable pageable);
	
	Page<Audit> findByZoneIdAndPerformedBy(long zoneId, String performedBy, Pageable pageable);
	
	
	void deleteByIdAndZoneId(long id, long zoneId);
}
