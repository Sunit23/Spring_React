package com.tortuga.security.governance.platform.phase2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.phase2.models.ProjectCore;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;

@Repository
public interface SecurityRuleRepository extends MongoRepository<SecurityRule, String> {
	
	List<SecurityRule> findAll();

	@Query(value="{'Project ID':?0,'Rule ID':?1}")
	Optional<SecurityRule> getRuleData(String projectId, String ruleId);

	List<SecurityRule> findByRuleID(String ruleId);

	List<SecurityRule> findAllByProjectId(String projectId);

	List<SecurityRule> findAllByRuleID(String ruleID);

	@Query(value="{'Project ID':?0,'Rule ID':?1}")
	Page<SecurityRule> findSecurityRuleRevision(String projectId, String ruleId, PageRequest request);

	SecurityRule findByProjectIdAndRuleID(String projectId, String ruleID);


}
