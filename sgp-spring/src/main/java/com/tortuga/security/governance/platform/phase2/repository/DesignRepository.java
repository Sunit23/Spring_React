package com.tortuga.security.governance.platform.phase2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.phase2.models.Design;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;

@Repository
public interface DesignRepository extends MongoRepository<Design, String> {
	
	List<Design> findAll();

}
