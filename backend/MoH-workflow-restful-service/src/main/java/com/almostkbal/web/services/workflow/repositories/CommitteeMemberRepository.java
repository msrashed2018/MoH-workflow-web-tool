package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almostkbal.web.services.workflow.entities.CommitteeMember;

public interface CommitteeMemberRepository extends JpaRepository<CommitteeMember, Long> {

}
