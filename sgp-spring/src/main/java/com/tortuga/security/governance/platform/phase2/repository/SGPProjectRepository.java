package com.tortuga.security.governance.platform.phase2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.phase2.models.SGPProject;

@Repository
public interface SGPProjectRepository extends MongoRepository<SGPProject, String> {
	
	List<SGPProject> findAll();

}
