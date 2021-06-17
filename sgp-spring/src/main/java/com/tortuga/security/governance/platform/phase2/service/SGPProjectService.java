package com.tortuga.security.governance.platform.phase2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.payload.response.SGPProjectResponse;
import com.tortuga.security.governance.platform.payload.response.SecurityRequirementsResponse;
import com.tortuga.security.governance.platform.payload.response.SecurityRulesDao;
import com.tortuga.security.governance.platform.payload.response.TestsDao;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;
import com.tortuga.security.governance.platform.phase2.models.Test;
import com.tortuga.security.governance.platform.phase2.repository.SGPProjectRepository;
import com.tortuga.security.governance.platform.service.SecurityRequirementsService;


@Service
public class SGPProjectService {
	
	@Autowired
	SGPProjectRepository sGPProjectRepository;
	
	@Autowired
	TestService testService;
	
	@Autowired
	SecurityRuleService securityRuleService;
	
	@Autowired
	SecurityRequirementsService securityReqService;
	
//	 public List<SGPProject> getAll(){
//			return sGPProjectRepository.findAll();
//		 }
	 
	 public List<SGPProjectResponse> getAll(){
		 List<SGPProjectResponse> sgpProjectResponses = new ArrayList<>();
		 List<SGPProject> sgpProject = sGPProjectRepository.findAll();
		 if(!sgpProject.isEmpty()) {
			 for(SGPProject item: sgpProject) {
				 SGPProjectResponse res = new SGPProjectResponse();
				 res.setId(item.getId());
				 res.setProjectName(item.getProjectName());
				 
				 //get tests data by name
					 List<TestsDao> resTests = new ArrayList<>();
					 for(String test: item.getTests()) {
						 List<Test> tests = testService.findByName(test);
						 for(Test testData: tests) {
							 TestsDao testDao= new TestsDao();
							 testDao.setId(testData.getName());
							 testDao.setDate(testData.getStartTime());
							 resTests.add(testDao);
						 }
					 }
					 res.setTests(resTests);
				
				//get SecurityRules by RuleId
					 List<SecurityRulesDao> resRules = new ArrayList<>();
					 for(String rulename: item.getSecurityRules()) {
						 List<SecurityRule> securityRules = securityRuleService.findByRuleId(rulename);
						for(SecurityRule securityRule : securityRules) {
							SecurityRulesDao rule = new SecurityRulesDao();
							rule.setId(securityRule.getRuleID());
							rule.setDate(securityRule.getModifiedDate());
							rule.setStatus(securityRule.getStatus());
							resRules.add(rule);
						}
					 }
					 res.setSecurityRules(resRules);
					 
				//get security requirements by project id
					 List<SecurityRequirementsResponse> resRequirement = securityReqService.findByProjectId(item.getProjectName());
					 res.setSecurityRequirements(resRequirement);
				
				//get design 
				 sgpProjectResponses.add(res); 
			 }
		 }
			return sgpProjectResponses;
	 }

}
