package com.tortuga.security.governance.platform.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.models.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
