package com.almostkbal.web.services.workflow.repositories;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almostkbal.web.services.workflow.entities.BonesRevealState;
import com.almostkbal.web.services.workflow.entities.EyeRevealState;
import com.almostkbal.web.services.workflow.entities.Request;
import com.almostkbal.web.services.workflow.entities.RequestState;

public interface RequestRepository extends JpaRepository<Request, Long> {
	@Query(value = "SELECT r FROM Request r WHERE TRUNC(r.requestDate) = TO_DATE(:date, 'yyyy-MM-dd')")
	List<Request> findAllByDate(@Param("date") String date);

	List<Request> findByCitizenNationalId(Long nationalId);

	List<Request> findByCitizenId(Long nationalId);

	@Transactional
	@Modifying
	@Query("update Request r set r.state = :state where r.id= :requestId")
	void setRequestState(@Param("requestId") long requestId, @Param("state") RequestState state);

	@Transactional()
	@Modifying
	@Query("update Request r set r.eyeRevealState = :state where r.id= :requestId")
	void setEyeRevealState(@Param("requestId") long requestId, @Param("state") EyeRevealState eyeRevealState);

	@Transactional
	@Modifying
	@Query("update Request r set r.bonesRevealState = :state where r.id= :requestId")
	void setBonesRevealState(@Param("requestId") long requestId, @Param("state") BonesRevealState bonesRevealState);

	List<Request> findByState(RequestState state);

	List<Request> findByStateIn(Collection<RequestState> states);

	List<Request> findByStateAndEyeRevealState(RequestState state,
			EyeRevealState eyeRevealState);

	List<Request> findByStateAndBonesRevealState(RequestState state,
			BonesRevealState bonesRevealState);

	List<Request> findByStateAndBonesRevealStateInAndEyeRevealStateIn(RequestState state,
			Collection<BonesRevealState> bonesRevealStates, Collection<EyeRevealState> eyeRevealStates);

	List<Request> findByBonesCommitteeIsNotNullAndState(RequestState state);

	// for payment'
//	@Query("select r from Request r , BonesReveal e where e.request = r")
//	List<Request> findForEyeReveal();

//	@Query("SELECT new com.jeejava.dto.DeptEmpDto(d.name, e.name, e.email, e.address) "
//			+ "FROM Department d INNER JOIN d.employees e")
//	List<DeptEmpDto> fetchEmpDeptDataInnerJoin();

}
