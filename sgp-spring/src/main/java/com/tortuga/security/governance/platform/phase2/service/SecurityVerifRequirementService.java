package com.tortuga.security.governance.platform.phase2.service;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.payload.response.SecurityRuleSummary;
import com.tortuga.security.governance.platform.payload.response.SecurityVerifyRequirementDao;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;
import com.tortuga.security.governance.platform.phase2.models.SecurityVerifRequirement;
import com.tortuga.security.governance.platform.phase2.models.Test;
import com.tortuga.security.governance.platform.phase2.repository.SGPProjectRepository;
import com.tortuga.security.governance.platform.phase2.repository.SecurityRuleRepository;
import com.tortuga.security.governance.platform.phase2.repository.SecurityVerifRequirementRepository;
import com.tortuga.security.governance.platform.phase2.repository.TestRepository;

@Service
public class SecurityVerifRequirementService {
	
	@Autowired
	SecurityVerifRequirementRepository securityVerifRequirementRepository;
	
	@Autowired 
	SecurityRuleRepository secRuleRepo;
	
	 public List<SecurityVerifRequirement> getAll(){
		 List<SecurityVerifRequirement> secReqsResponse  = new ArrayList<>();
		 List<SecurityVerifRequirement> secReqs = securityVerifRequirementRepository.findAll();
		 for(SecurityVerifRequirement secReq : secReqs) {
			 if(secReq.getRuleId().size()>0) {
				 secReq.setStatus("PASS");
				 
			 }
			 boolean flag= false;
			 for(String ruleId : secReq.getRuleId()) {
				 
				 List<SecurityRule> secRules = secRuleRepo.findAllByRuleID(ruleId);
				 
				 for(SecurityRule rule : secRules) {
					 flag = true;
					 secReq.setStatusDate(rule.getStatusDate());
					 if(rule.getStatus().equals("FAIL")) {
						 secReq.setStatus("FAIL");
					 }
					 if(rule.getStatus().equals("OUT-OF-DATE")) {
						 secReq.setStatus("OUT-OF-DATE");
					 }
				 }
			 }
			 if(!flag) secReq.setStatus("");
			 secReqsResponse.add(secReq);
		 }
		 
		 return secReqsResponse;
	 }

	public SecurityRuleSummary getSecurityRequirementsRecords() {
		List<SecurityVerifRequirement> secuirtyReqs =  securityVerifRequirementRepository.findAll();
		SecurityRuleSummary ruleSummary = new SecurityRuleSummary();
		int passed = 0;
		int failed = 0;
		int outOfDate = 0;
		int total = 0;
		for(SecurityVerifRequirement item : secuirtyReqs) {
			int temp=1;
			for(String rule : item.getRuleId()) {
				List<SecurityRule> secRules = secRuleRepo.findAllByRuleID(rule);
				if(secRules.size()>0)temp=1;
				
				for(SecurityRule sec : secRules) {
					if(sec.getStatus().equals("FAIL"))temp=0;
					
					if(sec.getStatus().equals("OUT-OF-DATE"))temp=2;
					
				}
			}
			if(temp==0) failed++;
			if(temp==1) passed++;
			if(temp==2) outOfDate++;
			total++;
		}
		ruleSummary.setFailed(failed);
		ruleSummary.setOutOfDate(outOfDate);
		ruleSummary.setPassed(passed);
		ruleSummary.setTotal(total);
		return ruleSummary;
	}

	public void addOrUpdateSecurity(SecurityVerifyRequirementDao secReq) {

		if(secReq.getRequirementName()!=null) {
			SecurityVerifRequirement securityReq = securityVerifRequirementRepository.findByRequirementName(secReq.getRequirementName());  
			if(securityReq==null) {
				
				String modifiedDate= null;
				String createdDate = null;
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
				   LocalDateTime now = LocalDateTime.now();
				   modifiedDate = dtf.format(now).toString();
				   createdDate = dtf.format(now).toString();
//				DateTimeFormatter FULL_ISO_DATE_FORMAT = DateTimeFormatter. ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
//				try {
//					LocalDateTime now = LocalDateTime.now(); 
//					LocalDateTime cdateTime = LocalDateTime.now();
//					cdateTime.format(FULL_ISO_DATE_FORMAT);
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//					Date cDate = new Date();
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				SecurityVerifRequirement sreq = new SecurityVerifRequirement(
						secReq.getRequirementName(), 
						secReq.getDescription(),
						secReq.getAsset(),
						secReq.getObjective(),
						secReq.getBoundary(),
						secReq.getOwner(),
						secReq.getWorkspaces(),
						secReq.getSecurityRules(),
						secReq.getcwes(),
						secReq.getLinkedSecurityRuleToCWE(),
						modifiedDate,
						createdDate
						);
				securityVerifRequirementRepository.save(sreq);
			}
			else {
			
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
				   LocalDateTime now = LocalDateTime.now();
				   String modifiedDate = dtf.format(now).toString();
				securityReq.setAsset(secReq.getAsset());
				securityReq.setDescription(secReq.getDescription());
				securityReq.setObjective(secReq.getObjective());
				securityReq.setBoundary(secReq.getBoundary());
				securityReq.setOwner(secReq.getOwner());
				securityReq.setWorkspaces(secReq.getWorkspaces());
				securityReq.setRuleId(secReq.getSecurityRules());
				securityReq.setCweId(secReq.getcwes());
				securityReq.setRuleToCwe(secReq.getLinkedSecurityRuleToCWE());
				securityReq.setModifiedDate(modifiedDate.toString());
				securityVerifRequirementRepository.save(securityReq);
			}
		}
	}

}
