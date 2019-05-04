package com.almostkbal.web.services.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestDetail;

public interface RequestDetailRepository extends JpaRepository<RequestDetail, Long> {

}
