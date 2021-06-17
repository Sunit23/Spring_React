package com.tortuga.security.governance.platform.phase2.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.phase2.models.ProjectCore;

@Repository
public interface ProjectCoreRepository extends MongoRepository<ProjectCore, String> {
	
	List<ProjectCore> findAll();
	
	@Query(value="{'ruleFileset.rules.id':?0}")
	Page<ProjectCore> findRuleResult(String ruleId,Pageable pageable);
	
	@Query(value="{'projectName':?0,'securityRules.ruleID':?1}")
	ArrayList<ProjectCore> findSecurityRuleRevision(String projectId,String ruleId,Pageable pageable);
	
	@Query(value="{'projectName':?0,'securityRules.ruleID':?1}")
	List<ProjectCore> findByProjectIdAndRuleId(String projectId, String ruleId);

	List<ProjectCore> findByProjectName(String projectName);

	ProjectCore findByChecksum(String checksum);
	

	
}
