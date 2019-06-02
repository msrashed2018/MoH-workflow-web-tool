package com.almostkbal.web.services.workflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almostkbal.web.services.workflow.entities.RequestDocument;

public interface RequestDocumentRepository extends JpaRepository<RequestDocument, Long> {
	
	
	@Query(value="select * from request_document where request_id = :requestId",nativeQuery=true)
	List<RequestDocument> findByRequestId(@Param(value = "requestId") long requestId);
	
	@Query(value="select * from request_document where request_id = :requestId and name= :documentName",nativeQuery=true)
	RequestDocument findByRequestIdAndName(@Param(value = "requestId") long requestId, @Param(value = "documentName") String documentName );

}
