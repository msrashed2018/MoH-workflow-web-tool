package com.almostkbal.web.services.workflow.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.almostkbal.web.services.workflow.entities.Committee;

public interface CommitteeRepository extends JpaRepository<Committee, Long> {
	
	Page<Committee> findByZoneId(long zoneId, Pageable pageable);

	void deleteByIdAndZoneId(long id, long zoneId);

	List<Committee> findByZoneIdAndTypeAndDateGreaterThan(long zoneId, String type, Date date);
}
