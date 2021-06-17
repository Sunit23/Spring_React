package com.tortuga.security.governance.platform.phase2.scheduler;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tortuga.security.governance.platform.payload.response.SecurityRulesResponse;
import com.tortuga.security.governance.platform.phase2.models.ProjectCore;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;
import com.tortuga.security.governance.platform.phase2.models.SimCore;
import com.tortuga.security.governance.platform.phase2.models.helper.RuleResult;
import com.tortuga.security.governance.platform.phase2.models.helper.SecurityRuleSet;
import com.tortuga.security.governance.platform.phase2.repository.ProjectCoreRepository;
import com.tortuga.security.governance.platform.phase2.repository.SecurityRuleRepository;
import com.tortuga.security.governance.platform.phase2.repository.SimCoreRepository;
import com.tortuga.security.governance.platform.phase2.service.SecurityRuleService;

@Component
public class Scheduler {
	
	
	@Autowired
	SecurityRuleRepository securityRuleRepository;
	
	@Autowired
	ProjectCoreRepository projectCoreRepository;
	
	@Autowired
	SecurityRuleService securityRuleService;
	
	@Autowired
	SimCoreRepository simCoreRepository;
	
	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

	@Scheduled(fixedRate = 30*10000)
	public void reportCurrentTime() {
		log.info("Generating SecurityRules from ProjectCore and SimCore");
		List<SecurityRulesResponse> res = new ArrayList<>();
		List<ProjectCore> projectCores = projectCoreRepository.findAll();
		projectCores.sort((o1, o2) -> o2.getLastModified().compareTo(o1.getLastModified()));
		ArrayList<String> projects = new ArrayList<>();
		if(projectCores.size()>=1) {
			for(ProjectCore pr : projectCores) {
				if(!projects.contains(pr.getProjectName())) {
					projects.add(pr.getProjectName());
					 for(SecurityRuleSet rule : pr.getSecurityRules()) {
						 SecurityRulesResponse item = new SecurityRulesResponse();
						 List<SimCore> simCores =  simCoreRepository.findByChecksumProjectNameAndRuleId(pr.getChecksum(), pr.getProjectName(), rule.getRuleID());
						 simCores.sort((o1, o2) -> o2.getSimStart().compareTo(o1.getSimStart()));
						 item.setId(pr.get_id());
						 item.setProjectId(pr.getProjectName());
						 item.setChecksum(rule.getChecksum());
						 item.setRuleID(rule.getRuleID());
						 item.setRuleText(rule.getRule_text());
						 item.setFile(rule.getFile());
						 item.setModifiedDate(pr.getLastModified());
						 item.setDescription(rule.getDescription());
						 if(simCores.size()>=1) {
							 SimCore simCore = simCores.get(0);
								 for(RuleResult secRule : simCore.getRuleResults()) {
										if(secRule.getRule_id().equals(rule.getRuleID())) {
											item.setResultsDate(simCore.getSimStart());
											item.setTest(simCore.getTest());
											item.setStatus(secRule.getResult());
											break;
										}
									}
						 }
						 else {
											item.setResultsDate(pr.getLastModified());
											item.setStatus("OUT-OF-DATE");											
						 }
						 res.add(item);
						 SecurityRule secRule = securityRuleRepository.findByProjectIdAndRuleID(item.getProjectId(), item.getRuleID());
						 if(secRule == null) {
							 SecurityRule sec = new SecurityRule(item.getProjectId(),
									 item.getChecksum(),
									 item.getRuleID(),
									 item.getRuleText(),
									 item.getFile(),
									 item.getModifiedDate(),
									 item.getStatus(),
									 item.getResultsDate(),
									 item.getTest(),
									 item.getDescription());
							 securityRuleRepository.save(sec);
						 }
						 else {
							 if(item.getChecksum()!=null)secRule.setRuleChecksum(item.getChecksum());
							 
							 if(item.getRuleText()!=null)secRule.setRuleText(item.getRuleText());
							 
							 if(item.getFile()!=null)secRule.setFile(item.getFile());
							 
							 if(item.getModifiedDate()!=null)secRule.setModifiedDate(item.getModifiedDate());
							 
							 if(item.getStatus()!=null)secRule.setStatus(item.getStatus());
							 
							 if(item.getResultsDate()!=null)secRule.setStatusDate(item.getResultsDate());
							 
							 if(item.getTest()!=null)secRule.setTest(item.getTest());
							 
							 if(item.getDescription()!=null)secRule.setDescription(item.getDescription());
							 securityRuleRepository.save(secRule);
						 }
					 }
				}
			}
		}
	   log.info("Delete SecurityRules from collection if rule doest not exits in corresponding ProjectCore");
	   List<SecurityRule> secRules = securityRuleRepository.findAll();
	   	for(SecurityRule secRule: secRules) {
	   		PageRequest request = PageRequest.of(0, 1, Sort.Direction.DESC, "lastModified");
	   		List<ProjectCore> pr = projectCoreRepository.findSecurityRuleRevision(secRule.getProjectId(), secRule.getRuleID(), request);
	   		if(pr.size()==0) {
	   			securityRuleRepository.delete(secRule);
	   		}
	   	}
	}
}
