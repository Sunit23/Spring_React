package com.tortuga.security.governance.platform.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.models.SecurityRequirements;
import com.tortuga.security.governance.platform.payload.response.SecurityRequirementsResponse;

@Repository
public interface SecurityRequirementsRepository extends MongoRepository<SecurityRequirements, String> {
	
	List<SecurityRequirementsResponse> findByProjectId(String projectId);
	
}
