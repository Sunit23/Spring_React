package com.tortuga.security.governance.platform.phase2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.payload.request.SecurityRuleRevisionRequest;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleResultResponse;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleRevisionDao;
import com.tortuga.security.governance.platform.payload.response.SimCoreDao;
import com.tortuga.security.governance.platform.phase2.models.ProjectCore;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;
import com.tortuga.security.governance.platform.phase2.models.SimCore;
import com.tortuga.security.governance.platform.phase2.models.helper.Rule;
import com.tortuga.security.governance.platform.phase2.models.helper.RuleFileset;
import com.tortuga.security.governance.platform.phase2.models.helper.RuleResult;
import com.tortuga.security.governance.platform.phase2.repository.ProjectCoreRepository;
import com.tortuga.security.governance.platform.phase2.repository.SimCoreRepository;

@Service
public class SimCoreService {
	
	@Autowired
	SimCoreRepository simCoreRepository;
	
	@Autowired
	SecurityRuleService securityRuleService;
	
	@Autowired
	ProjectCoreRepository projectCoreRepository;
	
	 public List<SimCore> getAll(){
			return simCoreRepository.findAll();
		 }

	 public List<SimCore> getDetailsByRuleId(String ruleId){
		 return simCoreRepository.findRuleResult(ruleId);
	 }
	 
	 public List<SimCoreDao> getSecurityRuleStatusDetails(String projectId, String ruleId, Integer unit){
			PageRequest request = PageRequest.of(0, unit,Sort.by("simStart").descending());
			List<SimCore> simCores = simCoreRepository.findSecurityRuleStatus(projectId,ruleId, request);
			
			List<SimCoreDao> simCoreRuleHistory = new ArrayList<>();
			for(SimCore item: simCores) {
				
				for(RuleResult rule : item.getRuleResults()) {
					List<SecurityRule> securityRules = securityRuleService.findByRuleId(rule.getRule_id());
					for(SecurityRule secRule : securityRules){
						SimCoreDao simDao = new SimCoreDao();
						simDao.setModifiedDate(secRule.getModifiedDate());
						simDao.setRuleChecksum(secRule.getRuleChecksum());
						simDao.setStatus(secRule.getStatus());
						simDao.setStatusDate(secRule.getStatusDate());
						simDao.setTest(secRule.getTest());
						simCoreRuleHistory.add(simDao);
					}
				}
				
			}
//			return simCoreRepository.findSecurityRuleStatus(projectId,ruleId, request).getContent();
			return simCoreRuleHistory;
		}
	 
	 public List<String> getDetailSecurityRuleStatus(String projectId, String ruleId, Integer unit){
		 PageRequest request = PageRequest.of(0, unit,Sort.by("simStart").descending());
			List<SimCore> pcore =	simCoreRepository.findSecurityRuleStatus(projectId,ruleId, request);
			List<String> ruleIdStatus=	pcore.stream()
					.map(s -> s.getRuleResults())
					.flatMap(List::stream)
					.filter(s -> s.rule_id.equalsIgnoreCase(ruleId))
					.map(s->s.result)
					.collect(Collectors.toList());
			return ruleIdStatus;
		}

	public List<SecurityRuleResultResponse> getSecurityRuleResult(String projectId, String ruleId, Integer unit) {

		List<SecurityRuleResultResponse> response = new ArrayList<>();
		List<ProjectCore> projectCores = projectCoreRepository.findByProjectIdAndRuleId(projectId, ruleId);
		projectCores.sort((o1, o2) -> o2.getLastModified().compareTo(o1.getLastModified()));
		for(ProjectCore pr : projectCores) {
			
			//check latest 5 records for checksum projectName ruleid in simCore
			
			PageRequest request = PageRequest.of(0, unit);
			List<SimCore> simCores = simCoreRepository.findByChecksumProjectNameAndRuleId(pr.getChecksum(), projectId, ruleId, request);
			simCores.sort((o1, o2) -> o2.getSimStart().compareTo(o1.getSimStart()));
			for(SimCore item : simCores) {
			for(RuleResult rule : item.getRuleResults()) {
				SecurityRuleResultResponse simCoreResults = new SecurityRuleResultResponse();
				simCoreResults.setChecksum(pr.getChecksum());
				simCoreResults.setResult(rule.getResult());
				simCoreResults.setResultDate(item.getSimStart());
				simCoreResults.setTest(item.getTest());
				response.add(simCoreResults);
				break;
//				List<SecurityRule> securityRules = securityRuleService.findByRuleId(rule.getRule_id());
//				for(SecurityRule secRule : securityRules){
//					SecurityRuleResultResponse simCoreResults = new SecurityRuleResultResponse();
//					simCoreResults.setChecksum(secRule.getRuleChecksum());
//					simCoreResults.setResult(rule.getResult());
//					simCoreResults.setResultDate(item.getSimStart());
//					simCoreResults.setTest(item.getTest());
//					response.add(simCoreResults);
//				}
			
			}
			}
			if(!response.isEmpty()) {
				break;
			}
		}
		return response;
	}

}
