package com.tortuga.security.governance.platform.phase2.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.digester.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.payload.request.SecurityRuleRevisionRequest;
import com.tortuga.security.governance.platform.payload.response.ProjectCoreDao;
import com.tortuga.security.governance.platform.payload.response.RuleHistory;
import com.tortuga.security.governance.platform.payload.response.RuleHistoryStartToEndData;
import com.tortuga.security.governance.platform.payload.response.RuleRevision;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleRevisionDao;
import com.tortuga.security.governance.platform.payload.response.SecurityRuleRevisionHistoryResponseDao;
import com.tortuga.security.governance.platform.payload.response.SimCoreDao;
import com.tortuga.security.governance.platform.payload.response.SingleRuleGanttObject;
import com.tortuga.security.governance.platform.payload.response.StatusChanges;
import com.tortuga.security.governance.platform.phase2.models.ProjectCore;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.SecurityRule;
import com.tortuga.security.governance.platform.phase2.models.SimCore;
import com.tortuga.security.governance.platform.phase2.models.Test;
import com.tortuga.security.governance.platform.phase2.models.helper.DesignFile;
import com.tortuga.security.governance.platform.phase2.models.helper.Rule;
import com.tortuga.security.governance.platform.phase2.models.helper.RuleFileset;
import com.tortuga.security.governance.platform.phase2.models.helper.RuleResult;
import com.tortuga.security.governance.platform.phase2.models.helper.SecurityRuleSet;
import com.tortuga.security.governance.platform.phase2.repository.ProjectCoreRepository;
import com.tortuga.security.governance.platform.phase2.repository.SGPProjectRepository;
import com.tortuga.security.governance.platform.phase2.repository.SimCoreRepository;
import com.tortuga.security.governance.platform.phase2.repository.TestRepository;

@Service
public class ProjectCoreService {

	@Autowired
	ProjectCoreRepository projectCoreRepository;
	
	@Autowired
	SecurityRuleService securityRuleService;
	
	@Autowired
	SimCoreRepository simCoreRepository;

	public List<ProjectCore> getAll(){
		return projectCoreRepository.findAll();
	}

	public List<ProjectCore> getDetailsByRuleId(String ruleId){
		PageRequest request = PageRequest.of(0, 1);
		return projectCoreRepository.findRuleResult(ruleId,request).getContent();
	}
	
	public List<ProjectCoreDao> getDetailsSecurityRuleRevision(String projectId, String ruleId, Integer unit){
		PageRequest request = PageRequest.of(0, unit,Sort.by("lastModified").descending());
		List<ProjectCore> projectCore = projectCoreRepository.findSecurityRuleRevision(projectId,ruleId, request);
		List<ProjectCoreDao> projectCoreRuleHistory = new ArrayList<>();
		for(ProjectCore item: projectCore) {
			for(RuleFileset ruleFile : item.getRuleFileset()) {
				for(Rule rule : ruleFile.getRules()) {
					List<SecurityRule> securityRules = securityRuleService.findByRuleId(rule.getRule_id());
					for(SecurityRule secRule : securityRules){
						ProjectCoreDao projectDao = new ProjectCoreDao();
						projectDao.setModifiedDate(secRule.getModifiedDate());
						projectDao.setRuleChecksum(secRule.getRuleChecksum());
						projectDao.setStatus(secRule.getStatus());
						projectDao.setStatusDate(secRule.getStatusDate());
						projectDao.setTest(secRule.getTest());
						projectCoreRuleHistory.add(projectDao);
					}
				}
				
			}
			
		}
		return projectCoreRuleHistory;
	}
	

	public Object getSecurityRuleHistory1(String projectId, String ruleId, Integer unit) {
		PageRequest request = PageRequest.of(0, unit, Sort.Direction.DESC, "simStart");
		List<SimCore> simCores = simCoreRepository.getByProjectNameAndRuleId( projectId, ruleId, request);
		
		List<ProjectCore> projectCores = projectCoreRepository.findByProjectIdAndRuleId(projectId, ruleId);
		projectCores.sort((o1, o2) -> o2.getLastModified().compareTo(o1.getLastModified()));
		SecurityRuleRevisionDao ruleRevisionHistory = new SecurityRuleRevisionDao();
		ArrayList<ProjectCore> projectCore = projectCoreRepository.findSecurityRuleRevision(projectId,ruleId, request);
		RuleHistoryStartToEndData startAndEndDate = new RuleHistoryStartToEndData();
		List<RuleRevision> ruleRevisions = new ArrayList<>();
		
		//setting start and end date in response
		if(simCores.size()>0) {
			startAndEndDate.setStartDate(simCores.get(simCores.size()-1).getSimStart());
			startAndEndDate.setEndDate(simCores.get(0).getSimStart());
			ruleRevisionHistory.setRuleHistoryStartToEndData(startAndEndDate);	
		}
		if(projectCore.size()>=1) {
			ProjectCore pr = projectCores.get(0);
			String modifiedDate = null;
			modifiedDate = pr.getLastModified();
			for(int i = simCores.size()-1; i>=0;i--) {
				for(int j=0; j<projectCore.size(); j++) {
					if(j==0 && projectCore.get(j).getChecksum().equals(simCores.get(i).getChecksum())) {
						modifiedDate = projectCore.get(j).getLastModified();
						break;
					}
					else if(projectCore.get(j).getChecksum().equals(simCores.get(i).getChecksum())){
						modifiedDate = projectCore.get(j-1).getLastModified();
						break;
					}					
				}
				RuleRevision ruleRevision = new RuleRevision();
				ruleRevision.setInitialModifiedDate(simCores.get(i).getSimStart());
				if(i!=0)ruleRevision.setModificationEndDate(simCores.get(i-1).getSimStart());
		
				if(i==0)ruleRevision.setModificationEndDate(simCores.get(i).getSimStart());
				
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
					Date date1 = dateFormat.parse(modifiedDate);
					Date date2 = dateFormat.parse(simCores.get(i).getSimStart());
					if(!projectCores.get(0).getChecksum().equals(simCores.get(i).getChecksum())){
						 if(date1.before(date2) || date1.equals(date2)){
								 ruleRevision.setInitialStatus("OUT-OF-DATE");
				            } 
						 else {
							 for(RuleResult result : simCores.get(i).getRuleResults()) {
									if(result.getRule_id().equals(ruleId)) {
											ruleRevision.setInitialStatus(result.getResult());
										break;
									}
							 }
							 
						 }
					}
					 else {
						 for(RuleResult result : simCores.get(i).getRuleResults()) {
								if(result.getRule_id().equals(ruleId)) {
										ruleRevision.setInitialStatus(result.getResult());
									break;
								}
						 }
					 }
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ruleRevisions.add(ruleRevision);
			}
			ruleRevisionHistory.setRuleRevision(ruleRevisions);
		}
		return ruleRevisionHistory;
	}
	
	public Object getSecurityRuleHistory(String projectId, String ruleId, Integer unit) {
		SecurityRuleRevisionDao ruleRevisionHistory = new SecurityRuleRevisionDao();
		PageRequest request = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "lastModified");
		ArrayList<ProjectCore> projectCore = projectCoreRepository.findSecurityRuleRevision(projectId,ruleId, request);
		RuleHistoryStartToEndData startAndEndDate = new RuleHistoryStartToEndData();
		List<RuleRevision> ruleRevisions = new ArrayList<>();
		int count = 0;
		for(int i=0; i<projectCore.size(); i++) {
			PageRequest simRequest = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "simStart");
			List<SimCore> simCores = simCoreRepository.findByChecksumProjectNameAndRuleId(projectCore.get(i).getChecksum(), projectId, ruleId, simRequest);
				for(SimCore sim: simCores) {
					if(count>=unit)break;
					
					if(count<unit) {
						for(RuleResult rule: sim.getRuleResults()) {
							if(rule.getRule_id().equals(ruleId)) {
								RuleRevision ruleRevision = new RuleRevision();
								ruleRevision.setInitialModifiedDate(sim.getSimStart());
								ruleRevision.setInitialStatus(rule.getResult());
								if(ruleRevisions.size()==0) {
									ruleRevision.setModificationEndDate(sim.getSimStart());
								}
								else {
									ruleRevision.setModificationEndDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
								}
								count++;
								ruleRevisions.add(ruleRevision);
								break;
							}
							
						}
					}
				}
				if(count<unit) {
					RuleRevision ruleRevision = new RuleRevision();
					ruleRevision.setInitialModifiedDate(projectCore.get(i).getLastModified());
					ruleRevision.setInitialStatus("OUT-OF-DATE");
					if(ruleRevisions.size()==0) {
						ruleRevision.setModificationEndDate(projectCore.get(i).getLastModified());
					}
					else {
						ruleRevision.setModificationEndDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
					}
					count++;
					ruleRevisions.add(ruleRevision);
				}
		}
		List<RuleRevision> orderedRuleRevisions = new ArrayList<>();
		for(int j=ruleRevisions.size()-1; j>=0; j--) {
			orderedRuleRevisions.add(ruleRevisions.get(j));
		}
		ruleRevisionHistory.setRuleRevision(orderedRuleRevisions);
		if(ruleRevisions.size()>0) {
			startAndEndDate.setEndDate(ruleRevisions.get(0).getModificationEndDate());
			startAndEndDate.setStartDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
			ruleRevisionHistory.setRuleHistoryStartToEndData(startAndEndDate);
		}
		return ruleRevisionHistory;
	}
	
	public SecurityRuleRevisionDao getSecurityRuleRevision(String projectId, String ruleId, Integer unit){
		SecurityRuleRevisionDao ruleRevisionHistory = new SecurityRuleRevisionDao();
		PageRequest request = PageRequest.of(0, unit, Sort.Direction.DESC, "lastModified");
		ArrayList<ProjectCore> projectCore = projectCoreRepository.findSecurityRuleRevision(projectId,ruleId, request);
		RuleHistoryStartToEndData startAndEndDate = new RuleHistoryStartToEndData();
		List<RuleRevision> ruleRevisions = new ArrayList<>();
		String endDateforNext= null;
		for(int i=0; i<projectCore.size(); i++) {
			RuleRevision ruleRevision = new RuleRevision();
			List<StatusChanges> statusChanges = new ArrayList<>();
			PageRequest simRequest = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "simStart");
			List<SimCore> simCores = simCoreRepository.findByChecksumProjectNameAndRuleId(projectCore.get(i).getChecksum(), projectId, ruleId, simRequest);
				for(SimCore sim: simCores) {
					StatusChanges statusChange = new StatusChanges();
					statusChange.setChangeDate(sim.getSimStart());
					for(RuleResult rule: sim.getRuleResults()) {
						if(rule.getRule_id().equals(ruleId)) {
							statusChange.setStatus(rule.getResult());
							break;
						}
					}
					statusChanges.add(statusChange);
				}
				endDateforNext = projectCore.get(i).getLastModified();
				ruleRevision.setInitialModifiedDate(endDateforNext);
				if(i==0) {
					if(simCores.size()>0) {
						ruleRevision.setModificationEndDate(simCores.get(0).getSimStart());
					}
					else {
						ruleRevision.setModificationEndDate(projectCore.get(i).getLastModified());
					}
				}
				else {
					ruleRevision.setModificationEndDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
				}
				if(i<projectCore.size()-1) {
					ruleRevision.setInitialStatus("OUT-OF-DATE");
				}
				else {
					ruleRevision.setInitialStatus("OUT-OF-DATE");
				}
				List<StatusChanges> statusChangesRev = new ArrayList<>();
				for(int j=statusChanges.size()-1; j>=0; j--) {
					statusChangesRev.add(statusChanges.get(j));
				}
				ruleRevision.setStatusChanges(statusChangesRev);
				ruleRevisions.add(ruleRevision);
		}
		List<RuleRevision> orderedRuleRevisions = new ArrayList<>();
		for(int j=ruleRevisions.size()-1; j>=0; j--) {
			orderedRuleRevisions.add(ruleRevisions.get(j));
		}
		ruleRevisionHistory.setRuleRevision(orderedRuleRevisions);
		if(ruleRevisions.size()>0) {
			startAndEndDate.setEndDate(ruleRevisions.get(0).getModificationEndDate());
			startAndEndDate.setStartDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
			ruleRevisionHistory.setRuleHistoryStartToEndData(startAndEndDate);
		}
		return ruleRevisionHistory;
	}
	public SecurityRuleRevisionDao getSecurityRuleRevision2(String projectId, String ruleId, Integer unit){
		SecurityRuleRevisionDao ruleRevisionHistory = new SecurityRuleRevisionDao();
		PageRequest request = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "lastModified");
		ArrayList<ProjectCore> projectCore = projectCoreRepository.findSecurityRuleRevision(projectId,ruleId, request);
		RuleHistoryStartToEndData startAndEndDate = new RuleHistoryStartToEndData();
		List<RuleRevision> ruleRevisions = new ArrayList<>();
		int count = 0;
		for(int i=0; i<projectCore.size(); i++) {
			List<StatusChanges> statusChanges = new ArrayList<>();
			RuleRevision ruleRevision = new RuleRevision();
			PageRequest simRequest = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "simStart");
			List<SimCore> simCores = simCoreRepository.findByChecksumProjectNameAndRuleId(projectCore.get(i).getChecksum(), projectId, ruleId, simRequest);
				for(SimCore sim: simCores) {
					if(count>=unit)break;
					
					if(count<unit) {
						for(RuleResult rule: sim.getRuleResults()) {
							if(rule.getRule_id().equals(ruleId)) {
								StatusChanges statusChange = new StatusChanges();
								statusChange.setChangeDate(sim.getSimStart());
								statusChange.setStatus(rule.getResult());
								count++;
								statusChanges.add(statusChange);
//								ruleRevision.setStatusChanges(statusChanges);
								break;
//								ruleRevision.setInitialModifiedDate(sim.getSimStart());
//								ruleRevision.setInitialStatus(rule.getResult());
//								if(ruleRevisions.size()==0) {
//									ruleRevision.setModificationEndDate(sim.getSimStart());
//								}
//								else {
//									ruleRevision.setModificationEndDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
//								}
//								count++;
//								ruleRevisions.add(ruleRevision);
//								break;
							}
							
						}
					}
					
				}
				List<StatusChanges> statusChangesRev = new ArrayList<>();
				for(int j=statusChanges.size()-1; j>=0; j--) {
					statusChangesRev.add(statusChanges.get(j));
				}
				ruleRevision.setStatusChanges(statusChangesRev);
				if(count<=unit) {
					ruleRevision.setInitialModifiedDate(projectCore.get(i).getLastModified());
					if(count<unit) {
						ruleRevision.setInitialStatus("OUT-OF-DATE");
					}
					else {
						ruleRevision.setInitialStatus("");
					}
					if(ruleRevisions.size()==0) {
						List<StatusChanges> status = ruleRevision.getStatusChanges();
						if(status.size()>0) {
							ruleRevision.setModificationEndDate(status.get(status.size()-1).getChangeDate());
						}
						else {
							ruleRevision.setModificationEndDate(projectCore.get(i).getLastModified());
						}
						
					}
					else {
						ruleRevision.setModificationEndDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
					}
					count++;
					ruleRevisions.add(ruleRevision);
				}
				
		}
		List<RuleRevision> orderedRuleRevisions = new ArrayList<>();
		for(int j=ruleRevisions.size()-1; j>=0; j--) {
			orderedRuleRevisions.add(ruleRevisions.get(j));
		}
		ruleRevisionHistory.setRuleRevision(orderedRuleRevisions);
		if(ruleRevisions.size()>0) {
			startAndEndDate.setEndDate(ruleRevisions.get(0).getModificationEndDate());
			startAndEndDate.setStartDate(ruleRevisions.get(ruleRevisions.size()-1).getInitialModifiedDate());
			ruleRevisionHistory.setRuleHistoryStartToEndData(startAndEndDate);
		}
		return ruleRevisionHistory;
	}
//	public SecurityRuleRevisionDao getSecurityRuleRevision1(String projectId, String ruleId, Integer unit){
//		PageRequest request = PageRequest.of(0, unit+1, Sort.Direction.DESC, "lastModified");
//		SecurityRuleRevisionDao ruleRevisionHistory = new SecurityRuleRevisionDao();
//		ArrayList<ProjectCore> projectCore = projectCoreRepository.findSecurityRuleRevision(projectId,ruleId, request);
//		RuleHistoryStartToEndData startAndEndDate = new RuleHistoryStartToEndData();
//		//setting start and end date in response
//		if(projectCore.size()>0) {
//			startAndEndDate.setStartDate(projectCore.get(projectCore.size()-1).getLastModified());
//			startAndEndDate.setEndDate(projectCore.get(0).getLastModified());
//			ruleRevisionHistory.setRuleHistoryStartToEndData(startAndEndDate);
//		}
//		List<RuleRevision> ruleRevisions = new ArrayList<>();
//		for(int i=projectCore.size()-1;i>0;i--) {
//			List<StatusChanges> statusChanges = new ArrayList<>();
//			RuleRevision ruleRevision = new RuleRevision();
//			ruleRevision.setInitialModifiedDate(projectCore.get(i).getLastModified());
//			if(i==projectCore.size()-1) {
//				ruleRevision.setInitialStatus("PASS");
//			}
//			else {
//				ruleRevision.setInitialStatus("OUT-OF-DATE");
//			}
//			String modificationEndDate = projectCore.get(i-1).getLastModified();
//			ruleRevision.setModificationEndDate(modificationEndDate);
//			modificationEndDate = projectCore.get(i-1).getLastModified();
//			PageRequest simRequest = PageRequest.of(0, unit, Sort.Direction.ASC, "simStart");
//			List<SimCore> simCores = simCoreRepository.findByChecksumProjectNameAndRuleId(projectCore.get(i).getChecksum(), projectId, ruleId, simRequest);
//			for(SimCore simCore : simCores) {
//				List<RuleResult> ruleResults = simCore.getRuleResults();
//				for(RuleResult ruleResult : ruleResults) {
//					if(ruleResult.getRule_id().equals(ruleId)) {
//						StatusChanges statusChange = new StatusChanges();
//						statusChange.setChangeDate(simCore.getSimStart());
//						try {
//							SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS");
//							Date date1 = dateFormat.parse(modificationEndDate);
//							Date date2 = dateFormat.parse(simCore.getSimStart());
//							 if(date1.before(date2)){
//								 statusChange.setStatus("OUT-OF-DATE");
//								 break;
//					            } 
//							 else if(date1.equals(date2)) {
//								 break;
//							 }
//							 else {
//								 statusChange.setStatus(ruleResult.getResult());
//							 }
//							
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//						statusChanges.add(statusChange);
//						ruleRevision.setStatusChanges(statusChanges);
//						break;
//					}
//				}
//			}
//			ruleRevisions.add(ruleRevision);
//		}
//		ruleRevisionHistory.setRuleRevision(ruleRevisions);
//		return ruleRevisionHistory;
//	}
}
