package com.almostkbal.web.services.workflow.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestState;

public interface RequestRepository extends JpaRepository<Request, Long> {
	@Query(value = "SELECT r FROM Request r WHERE TRUNC(r.requestDate) = TO_DATE(:date, 'yyyy-MM-dd')")
	List<Request> findAllByDate(@Param("date") String date);
	
	List<Request> findByCitizenNationalId(Long nationalId);
	
	List<Request> findByCitizenId(Long nationalId);

	@Modifying
	@Query("update Request r set r.state = :state where r.id= :requestId")
	void updateRequestState(@Param("requestId") long requestId, @Param("state") RequestState state);

	List<Request> findByState(RequestState state);

	List<Request> findByStateIn(Collection<RequestState> states);

	List<Request> findByEyeCommitteeIsNotNullAndState(RequestState state);

	List<Request> findByBonesCommitteeIsNotNullAndState(RequestState state);

	// for payment
//	List<Request> findByRequestTypeNameAnd(RequestState state);

}
