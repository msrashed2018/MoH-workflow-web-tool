package com.almostkbal.web.services.workflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestState;

public interface RequestRepository extends JpaRepository<Request, Long> {
	@Query(value = "SELECT r FROM Request r WHERE TRUNC(r.requestDate) = TO_DATE(:date, 'yyyy-MM-dd')")
	List<Request> findAllByDate(@Param("date") String date);
	
	List<Request> findByCitizenNationalId(Long nationalId);
	
	List<Request> findByCitizenId(Long nationalId);
	
	List<Request> findByState(RequestState state);
}
