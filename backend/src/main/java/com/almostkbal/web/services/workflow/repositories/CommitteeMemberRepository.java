package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.almostkbal.web.services.workflow.entities.CommitteeMember;

public interface CommitteeMemberRepository extends JpaRepository<CommitteeMember, Long> {
	
	Page<CommitteeMember> findByZoneId(long zoneId, Pageable pageable);
	
	void deleteByIdAndZoneId(long id, long zoneId);
}
