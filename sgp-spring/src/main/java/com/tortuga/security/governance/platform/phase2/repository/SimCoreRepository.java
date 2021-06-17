package com.tortuga.security.governance.platform.phase2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tortuga.security.governance.platform.phase2.models.SimCore;

@Repository
public interface SimCoreRepository extends MongoRepository<SimCore, String> {
	
	List<SimCore> findAll();
	
	@Query("{ ruleResults: { $elemMatch: { id : ?0 } } }")
	List<SimCore> findRuleResult(String ruleId);
	
	@Query(value="{'projectName':?0,'ruleResults.id':?1}")
	List<SimCore> findSecurityRuleStatus(String projectId, String ruleId, PageRequest request);

	@Query(value="{'projectName':?0,'ruleResults.id':?1,'checksum':?2}")
	Page<SimCore> findSecurityRuleHistory(String projectId, String ruleId, String checksum, PageRequest request);
	
	@Query(value="{'projectName':?1,'ruleResults.id':?2,'checksum':?0}")
	List<SimCore> findByChecksumProjectNameAndRuleId(String checksum, String projectId, String ruleId, PageRequest request);
	
	@Query(value="{'projectName':?1,'ruleResults.id':?2,'checksum':?0}")
	List<SimCore> findByChecksumProjectNameAndRuleId(String checksum, String projectId, String ruleId);
	
	@Query(value="{'projectName':?0,'ruleResults.id':?1}")
	List<SimCore> findByProjectNameAndRuleId(String projectId, String ruleId);
	
	@Query(value="{'projectName':?0,'ruleResults.id':?1}")
	List<SimCore> getByProjectNameAndRuleId(String projectId, String ruleId, PageRequest request);

	@Query(value="{'projectName':?0,'ruleResults.id':?1}")
	List<SimCore> findByProjectNameAndRuleIdByOrderBySimStartAsc(String projectId, String ruleId);
	
}
