package com.almostkbal.web.services.workflow.repositories;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almostkbal.web.services.workflow.entities.BonesRevealState;
import com.almostkbal.web.services.workflow.entities.EyeRevealState;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestState;
import com.almostkbal.web.services.workflow.entities.RequestStatus;

public interface RequestRepository extends JpaRepository<Request, Long> {
	@Query("select r.state from Request r where r.id = :requestId")
	RequestState findRequestState(@Param("requestId") long requestId);

	@Query(value = "SELECT r FROM Request r WHERE TRUNC(r.requestDate) = TO_DATE(:date, 'yyyy-MM-dd')")
	List<Request> findAllByDate(@Param("date") String date);

	List<Request> findByCitizenNationalId(Long nationalId);

	List<Request> findByCitizenId(Long nationalId);

	@Transactional
	@Modifying
	@Query("update Request r set r.state = :state where r.id= :requestId")
	void setRequestState(@Param("requestId") long requestId, @Param("state") RequestState state);

	@Transactional
	@Modifying
	@Query("update Request r set r.requestStatus = :requestStatus where r.id= :requestId")
	void setRequestStatus(@Param("requestId") long requestId, @Param("requestStatus") RequestStatus requestStatus);

	@Transactional()
	@Modifying
	@Query("update Request r set r.eyeRevealState = :state where r.id= :requestId")
	void setEyeRevealState(@Param("requestId") long requestId, @Param("state") EyeRevealState eyeRevealState);

	@Transactional
	@Modifying
	@Query("update Request r set r.bonesRevealState = :state where r.id= :requestId")
	void setBonesRevealState(@Param("requestId") long requestId, @Param("state") BonesRevealState bonesRevealState);

	Page<Request> findByState(RequestState state, Pageable pageable);

	Page<Request> findByStateIn(Collection<RequestState> states, Pageable pageable);

	Page<Request> findByStateAndEyeRevealState(RequestState state,
			EyeRevealState eyeRevealState, Pageable pageable);

	Page<Request> findByStateAndBonesRevealState(RequestState state,
			BonesRevealState bonesRevealState, Pageable pageable);

	Page<Request> findByStateAndBonesRevealStateInAndEyeRevealStateIn(RequestState state,
			Collection<BonesRevealState> bonesRevealStates, Collection<EyeRevealState> eyeRevealStates,
			Pageable pageable);
//	Page<Request> findByStateAndBonesRevealStateInOrEyeRevealStateIn(RequestState state,
//			Collection<BonesRevealState> bonesRevealStates, Collection<EyeRevealState> eyeRevealStates,
//			Pageable pageable);

	Page<Request> findByBonesCommitteeIsNotNullAndState(RequestState state, Pageable pageable);

	// for payment'
//	@Query("select r from Request r , BonesReveal e where e.request = r")
//	List<Request> findForEyeReveal();

//	@Query("SELECT new com.jeejava.dto.DeptEmpDto(d.name, e.name, e.email, e.address) "
//			+ "FROM Department d INNER JOIN d.employees e")
//	List<DeptEmpDto> fetchEmpDeptDataInnerJoin();

}
