package com.tortuga.security.governance.platform.phase2.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.mongodb.util.JSON;
import com.tortuga.security.governance.platform.payload.request.SecurityRuleRevisionRequest;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleSummary;
import com.tortuga.security.governance.platform.payload.response.SecurityRulesResponse;
import com.tortuga.security.governance.platform.phase2.models.ProjectCore;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;
import com.tortuga.security.governance.platform.phase2.models.SimCore;
import com.tortuga.security.governance.platform.phase2.models.helper.RuleResult;
import com.tortuga.security.governance.platform.phase2.models.helper.SecurityRuleSet;
import com.tortuga.security.governance.platform.phase2.repository.ProjectCoreRepository;
import com.tortuga.security.governance.platform.phase2.repository.SecurityRuleRepository;
import com.tortuga.security.governance.platform.phase2.repository.SimCoreRepository;

@Service
public class SecurityRuleService {
	
	@Autowired
	SecurityRuleRepository securityRuleRepository;
	
	@Autowired
	ProjectCoreRepository projectCoreRepository;
	
	@Autowired
	SecurityRuleService securityRuleService;
	
	@Autowired
	SimCoreRepository simCoreRepository;
	
	
	 public List<SecurityRule> getAll(){
		List<SecurityRule> SecRules = securityRuleRepository.findAll();
		return SecRules;
	}


	 
	 public List<SecurityRule> getAllByProjectId(String projectId){
		 List<SecurityRule> SecRules = securityRuleRepository.findAllByProjectId(projectId);
		 return SecRules;
	 }
	 
//	 public Optional<SecurityRule> getRuleData(String projectId,String ruleId) {
//		 return securityRuleRepository.getRuleData(projectId, ruleId);
//	 }
	 public List<SecurityRulesResponse> getAllByRuleId(String ruleId){
		 List<SecurityRulesResponse> res = new ArrayList<>();
		 List<SecurityRule> secRules = securityRuleRepository.findAllByRuleID(ruleId);
		 for(SecurityRule item: secRules) {
			 SecurityRulesResponse ruleRes = new SecurityRulesResponse();
			 ruleRes.setId(item.getId());
			 ruleRes.setChecksum(item.getRuleChecksum());
			 ruleRes.setDescription(item.getDescription());
			 ruleRes.setFile(item.getFile());
			 ruleRes.setModifiedDate(item.getModifiedDate());
			 ruleRes.setProjectId(item.getProjectId());
			 ruleRes.setStatus(item.getStatus());
			 ruleRes.setRuleID(item.getRuleID());
			 ruleRes.setRuleText(item.getRuleText());
			 ruleRes.setTest(item.getTest());
			 ruleRes.setResultsDate(item.getStatusDate());
			 res.add(ruleRes);
		 }
			return res;
	}

	public List<SecurityRule> findByRuleId(String rule) {
		return securityRuleRepository.findByRuleID(rule);
	}

	public Page<SecurityRule> findSecurityRuleRevision(String projectId, String ruleId, PageRequest request) {
		return securityRuleRepository.findSecurityRuleRevision(projectId, ruleId, request);
	}
	 public SecurityRule getAllByRuleIdAndProjectId(String projectId, String ruleId){
		 return securityRuleRepository.findByProjectIdAndRuleID(projectId, ruleId);
//		 List<ProjectCore> projectCores = projectCoreRepository.findByProjectIdAndRuleId(projectId, ruleId);
//		 ProjectCore projectCore;
//		 if(projectCores.size()>=1) {
//			 projectCores.sort((o1, o2) -> o2.getLastModified().compareTo(o1.getLastModified()));
//			 projectCore = projectCores.get(0);
//			 for(SecurityRuleSet rule : projectCore.getSecurityRules()) {
//				 if(rule.getRuleID().equals(ruleId)) {
//					 List<SimCore> simCores =  simCoreRepository.findByProjectNameAndRuleId(projectId, rule.getRuleID());
//					 simCores.sort((o1, o2) -> o2.getSimStart().compareTo(o1.getSimStart()));
//					 if(simCores.size()>=1) {
//						 SimCore simCore = simCores.get(0);
//						 
//						 
//						 item.setId(projectCore.get_id());
//						 item.setProjectId(projectId);
//						 item.setChecksum(rule.getChecksum());
//						 item.setRuleID(rule.getRuleID());
//						 item.setRuleText(rule.getRule_text());
//						 item.setFile(rule.getFile());
//						 item.setModifiedDate(rule.getModified());
//						 item.setDescription(rule.getDescription());
//							 for(RuleResult secRule : simCore.getRuleResults()) {
//									if(secRule.getRule_id().equals(rule.getRuleID())) {
//										item.setResultsDate(simCore.getSimStart());
//										item.setTest(simCore.getTest());
//										try {
//											SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
//											Date date1 = dateFormat.parse(rule.getModified());
//											Date date2 = dateFormat.parse(simCore.getSimStart());
//		
//											 if(date2.before(date1)){
//												 item.setStatus("out-of-date");
//									            } 
//											 else {
//												 item.setStatus(secRule.getResult());
//											 	}
//											
//										} catch (ParseException e) {
//											
//											e.printStackTrace();
//										}
//										break;
//									}
//								}
//					 }
//					 
//					 break; 
//				 }
//
//			 }
//			 
//		 }
//		 
//		 
//		 return item;
	 }
	 
//	public List<SecurityRulesResponse> getAllByRuleIdAndProjectId(String projectId, String ruleId) {
//		List<SecurityRulesResponse> res = new ArrayList<>();
//		List<ProjectCore> projectCores = projectCoreRepository.findByProjectIdAndRuleId(projectId, ruleId);
//		projectCores.sort((o1, o2) -> o2.getLastModified().compareTo(o1.getLastModified()));
//		for(ProjectCore pr : projectCores) {
//			
//			//check latest 5 records for checksum projectName ruleid in simCore
//			
//			List<SimCore> simCores = simCoreRepository.findByChecksumProjectNameAndRuleId(pr.getChecksum(), projectId, ruleId);
//			simCores.sort((o1, o2) -> o2.getSimStart().compareTo(o1.getSimStart()));
//			for(SimCore lsimCore : simCores) {
////				SimCore lsimCore = simCores.get(0);
//				SecurityRulesResponse secResponse = new SecurityRulesResponse();
//				secResponse.setId(pr.get_id());
//				secResponse.setChecksum(pr.getChecksum());
//				secResponse.setProjectId(pr.getProjectName());
//				String modifiedDate = null;
//				for(SecurityRuleSet rule : pr.getSecurityRules()) {
//					if(rule.getRuleID().equals(ruleId)) {
//						secResponse.setRuleID(rule.getRuleID());
//						secResponse.setRuleText(rule.getRuleText());
//						secResponse.setFile(rule.getFile());
//						secResponse.setModifiedDate(rule.getmodified());
//						modifiedDate = rule.getmodified();
//						secResponse.setDescription(rule.getDescription());
//						break;
//					}
//				}
//				for(RuleResult rule : lsimCore.getRuleResults()) {
//					if(rule.getRule_id().equals(ruleId)) {
//						secResponse.setResultsDate(lsimCore.getSimStart());
//						secResponse.setTest(lsimCore.getTest());
//						try {
//							SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
//							Date date1 = dateFormat.parse(modifiedDate);
//							Date date2 = dateFormat.parse(lsimCore.getSimStart());
//
//							 if(date2.before(date1)){
//								 secResponse.setStatus("out-of-date");
//					            } 
//							 else {
//								 secResponse.setStatus(rule.getResult());
//							 	}
//							
//						} catch (ParseException e) {
//							
//							e.printStackTrace();
//						}
//						break;
//					}
//				}
//				res.add(secResponse);
//				break;
//			}
//			if(!res.isEmpty()) {
//				break;
//			}
//		}
//		return res;
//	}
//
	public SecurityRuleSummary getSecurityRuleSummary() {
		List<SecurityRulesResponse> res = new ArrayList<>();
		List<ProjectCore> projectCores = projectCoreRepository.findAll();
		projectCores.sort((o1, o2) -> o2.getLastModified().compareTo(o1.getLastModified()));
		SecurityRuleSummary ruleSummary = new SecurityRuleSummary();
		int passed = 0;
		int failed = 0;
		int outOfDate = 0;
		int total = 0;
		ArrayList<HashMap<String, String>> uniqueRules = new ArrayList<>();
		for(ProjectCore pr : projectCores) {
			if(pr.getSecurityRules() !=null) {
			for(SecurityRuleSet ruleset : pr.getSecurityRules()) {
				HashMap<String, String> map = new HashMap<>();
				map.put(ruleset.getRuleID(), pr.getProjectName());
			if(uniqueRules.contains(map)) break;
			List<SimCore> simCores = simCoreRepository.findByChecksumProjectNameAndRuleId(pr.getChecksum(), pr.getProjectName(), ruleset.getRuleID());
			simCores.sort((o1, o2) -> o2.getSimStart().compareTo(o1.getSimStart()));
			for(SimCore lsimCore : simCores) {
				String modifiedDate = null;
				for(SecurityRuleSet rule : pr.getSecurityRules()) {
					if(rule.getRuleID().equals(ruleset.getRuleID())) {
						modifiedDate = rule.getModified();
						break;
					}
				}
				for(RuleResult rule : lsimCore.getRuleResults()) {
					if(rule.getRule_id().equals(ruleset.getRuleID())) {
					
						try {
							SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
							Date date1 = dateFormat.parse(modifiedDate);
							Date date2 = dateFormat.parse(lsimCore.getSimStart());

							 if(date2.before(date1)){
								 outOfDate++;
					            } 
							 else if(rule.getResult().equals("PASS")) {
								  passed++;
							 	}
							 else if(rule.getResult().equals("FAIL")) {
								 failed++;
							 }
							
						} catch (ParseException e) {
							
							e.printStackTrace();
						}
						break;
					}
				}
				total++;
				HashMap<String, String> map1 = new HashMap<>();
				map1.put(ruleset.getRuleID(), pr.getProjectName());
				uniqueRules.add(map1);
				break;
			}
			}
			}
		}
		ruleSummary.setFailed(failed);
		ruleSummary.setOutOfDate(outOfDate);
		ruleSummary.setPassed(passed);
		ruleSummary.setTotal(total);
		return ruleSummary;
		
	}



	public void updateSecurityRule(SecurityRule securityRule) {
		System.out.println(securityRule.getProjectId());
		if(securityRule.getProjectId()!=null && securityRule.getRuleID()!=null) {
			SecurityRule secRule = securityRuleRepository.findByProjectIdAndRuleID(securityRule.getProjectId(), securityRule.getRuleID());
			System.out.println(securityRule.getDescription());
			if(secRule != null) {
				if(securityRule.getRuleChecksum()!=null)secRule.setRuleChecksum(securityRule.getRuleChecksum());
				 
				 if(securityRule.getRuleText()!=null)secRule.setRuleText(securityRule.getRuleText());
				 
				 if(securityRule.getFile()!=null)secRule.setFile(securityRule.getFile());
				 
				 if(securityRule.getModifiedDate()!=null)secRule.setModifiedDate(securityRule.getModifiedDate());
				 
				 if(securityRule.getStatus()!=null)secRule.setStatus(securityRule.getStatus());
				 
				 if(securityRule.getStatusDate()!=null)secRule.setStatusDate(securityRule.getStatusDate());
				 
				 if(securityRule.getTest()!=null)secRule.setTest(securityRule.getTest());
				 
				 if(securityRule.getDescription()!=null)secRule.setDescription(securityRule.getDescription());
				 securityRuleRepository.save(secRule);
			}
		}
	}
}
