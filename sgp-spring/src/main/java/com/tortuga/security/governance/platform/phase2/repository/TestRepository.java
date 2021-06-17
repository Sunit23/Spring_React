package com.tortuga.security.governance.platform.phase2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.Test;

@Repository
public interface TestRepository extends MongoRepository<Test, String> {
	
	List<Test> findAll();
	List<Test> findByName(String Name);
}
