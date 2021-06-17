package com.tortuga.security.governance.platform.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tortuga.security.governance.platform.payload.request.SecurityRuleRevisionRequest;
import com.tortuga.security.governance.platform.payload.request.SimCoreRequest;
import com.tortuga.security.governance.platform.payload.response.MessageResponse;
import com.tortuga.security.governance.platform.payload.response.ProjectCoreDao;
import com.tortuga.security.governance.platform.payload.response.SGPProjectResponse;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleHistoryResponse;

import com.tortuga.security.governance.platform.payload.response.SecurityRuleResultResponse;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleRevisionDao;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleSummary;
import com.tortuga.security.governance.platform.payload.response.SecurityRulesResponse;
import com.tortuga.security.governance.platform.payload.response.SecurityVerifyRequirementDao;
import com.tortuga.security.governance.platform.payload.response.SimCoreDao;
import com.tortuga.security.governance.platform.phase2.models.CWE;
import com.tortuga.security.governance.platform.phase2.models.Design;
import com.tortuga.security.governance.platform.phase2.models.ProjectCore;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;
import com.tortuga.security.governance.platform.phase2.models.SecurityVerifRequirement;
import com.tortuga.security.governance.platform.phase2.models.SimCore;
import com.tortuga.security.governance.platform.phase2.models.Test;
import com.tortuga.security.governance.platform.phase2.service.CweService;
import com.tortuga.security.governance.platform.phase2.service.DesignService;
import com.tortuga.security.governance.platform.phase2.service.ProjectCoreService;
import com.tortuga.security.governance.platform.phase2.service.SGPProjectService;
import com.tortuga.security.governance.platform.phase2.service.SecurityRuleService;
import com.tortuga.security.governance.platform.phase2.service.SecurityVerifRequirementService;
import com.tortuga.security.governance.platform.phase2.service.SimCoreService;
import com.tortuga.security.governance.platform.phase2.service.TestService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sgpproject")
public class SGPProjectController {

	@Autowired
	SGPProjectService sGPProjectService ;
	
	@Autowired
	DesignService designService ;
	
	@Autowired
	SecurityRuleService securityRuleService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	SecurityVerifRequirementService securityVerifRequirementService;
	
	@Autowired
	CweService cweService;
	
	@Autowired
	SimCoreService simCoreService;
	
	@Autowired
	ProjectCoreService projectCoreService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<SGPProjectResponse> getAllSGPProject(){
		return sGPProjectService.getAll();
	}
	
	@GetMapping("/getAllDesign")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Design> getAllDesign(){
		return designService.getAll();
	}
	
	@GetMapping("/getSecurityRuleSummary")
	@PreAuthorize("hasRole('ADMIN')")
	public SecurityRuleSummary getSecurityRuleSummary() {
		return securityRuleService.getSecurityRuleSummary();
	}

	
	@GetMapping("/getAllSecurityRule")
	@PreAuthorize("hasRole('ADMIN')")
	public List<SecurityRule> getAllSecurityRule(@RequestParam(required= false)  String projectId){
		if(projectId == null) {
			return securityRuleService.getAll();
		}
		else {
			return securityRuleService.getAllByProjectId(projectId);
		}
	}
	@PostMapping("updateSecurityRule")
	@PreAuthorize("hasRole('ADMIN')")
	public void updateSecurityRule(@RequestBody SecurityRule securityRule) {
		System.out.println("SecurityRule"+ securityRule.getProjectId());
		securityRuleService.updateSecurityRule(securityRule);
	}
	
	@GetMapping("/getAllTest")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Test> getAllTest(){
		return testService.getAll();
	}
	
	@GetMapping("/getAllSecurityVerifRequirement")
	@PreAuthorize("hasRole('ADMIN')")
	public List<SecurityVerifRequirement> getAllSecurityVerifRequirement(){
		return securityVerifRequirementService.getAll();
	}

	@GetMapping("/getAllCWE")
	@PreAuthorize("hasRole('ADMIN')")
	public List<CWE> getAllCWE(){
		return cweService.getAll();
	}

	@GetMapping("/getSecurityRuleRevision")
	@PreAuthorize("hasRole('ADMIN')")
	public SecurityRuleRevisionDao getSecurityRuleRevision(@RequestParam  String projectId, @RequestParam String ruleId, @RequestParam Integer unit) {
		return projectCoreService.getSecurityRuleRevision(projectId, ruleId, unit);
		
	}
	
	
	@GetMapping("/getSecurityRule")
	@PreAuthorize("hasRole('ADMIN')")
	public SecurityRule getRuleData(@RequestParam String projectId, @RequestParam String ruleId) {
		return securityRuleService.getAllByRuleIdAndProjectId(projectId, ruleId);
	}
	
	@GetMapping("/getSecurityRuleHistory")
	@PreAuthorize("hasRole('ADMIN')")
	public Object getSecurityRuleHistory(@RequestParam  String projectId, @RequestParam String ruleId, @RequestParam Integer unit) {
		return projectCoreService.getSecurityRuleRevision(projectId, ruleId, unit);
		
	}
	
//	@GetMapping("/getSecurityRuleResult")
//	@PreAuthorize("hasRole('ADMIN')")
//	public List<SecurityRuleResultResponse> getSecurityRuleResult(@RequestParam  String projectId, @RequestParam String ruleId, @RequestParam Integer unit){
//		return  simCoreService.getSecurityRuleResult(projectId, ruleId, unit);
//	}
	
	@GetMapping("/getSecurityRequirementsSummary")
	@PreAuthorize("hasRole('ADMIN')")
	public SecurityRuleSummary getSecurityRequirements() {
		return securityVerifRequirementService.getSecurityRequirementsRecords();
	}
	
	@PostMapping("/addOrUpdateSecurityRequirement")
	@PreAuthorize("hasRole('ADMIN')")
	public void addOrUpdateSecurityRequirement(@RequestBody SecurityVerifyRequirementDao securityVerifyRequirement) {
		securityVerifRequirementService.addOrUpdateSecurity(securityVerifyRequirement);
	}
	
}
