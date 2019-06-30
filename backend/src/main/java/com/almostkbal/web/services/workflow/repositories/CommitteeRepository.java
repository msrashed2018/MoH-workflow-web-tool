package com.almostkbal.web.services.workflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almostkbal.web.services.workflow.entities.Committee;

public interface CommitteeRepository extends JpaRepository<Committee, Long> {
//	Query(value= "select ")
//    @Query("SELECT c.id, c.type, c.function, c.date FROM Committee c where c.type = :type") 
//	List<Committee> _findByType(@Param("type") String type);
    
    List<Committee> findByType(String type);
}