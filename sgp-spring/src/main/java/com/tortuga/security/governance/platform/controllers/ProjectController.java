package com.tortuga.security.governance.platform.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortuga.security.governance.platform.models.HardwareDesign;
import com.tortuga.security.governance.platform.models.PieChart;
import com.tortuga.security.governance.platform.models.Project;
import com.tortuga.security.governance.platform.models.ProjectRevision;
import com.tortuga.security.governance.platform.models.SecurityRequirements;
import com.tortuga.security.governance.platform.payload.response.JwtResponse;
import com.tortuga.security.governance.platform.payload.response.Nodes;
import com.tortuga.security.governance.platform.payload.response.ProjectResponse;
import com.tortuga.security.governance.platform.payload.response.TreeResponse;
import com.tortuga.security.governance.platform.service.HardwareDesignService;
import com.tortuga.security.governance.platform.service.ProjectRevisionService;
import com.tortuga.security.governance.platform.service.ProjectService;
import com.tortuga.security.governance.platform.service.SecurityRequirementsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectRevisionService projectRevisionService;
	
	
	@Autowired
	HardwareDesignService hardwareDesignService;

	@Autowired
	SecurityRequirementsService securityRequirementsService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Project> getAllUser(){
		return projectService.getAll();
	}
	
	
	
	@GetMapping("/getAllProjectRevision")
	@PreAuthorize("hasRole('ADMIN')")
	public List<ProjectRevision> getAllp(){
		return projectRevisionService.getAll();
	}
	
	@GetMapping("/getAllHardwareDesign")
	@PreAuthorize("hasRole('ADMIN')")
	public List<HardwareDesign> getAllh(){
		return hardwareDesignService.getAll();
	}
	
	
	@GetMapping("/getAllSecurityRequirements")
	//@PreAuthorize("hasRole('ADMIN')")
	public List<SecurityRequirements> getAllSecurityRequirements(){
		return securityRequirementsService.getAll();
	}
	
	@GetMapping("/getAlltree")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getFinalTree(){
		
		List<Project> ps = projectService.getAll();
		
		TreeResponse tr = new TreeResponse();
		
		List<ProjectResponse> projectResponse = new ArrayList<>();
		
		ps.forEach(p -> {
			
			ProjectResponse project = new ProjectResponse();
			project.setKey(p.getProjectId());
			project.setLabel(p.getName());
			
			Nodes nd = new Nodes();
			 //List<DesignRevision> designRevision= designRevisionService.getAll();
			
			List<ProjectRevision> projectRevision = projectRevisionService.getAll();
			
			List<HardwareDesign> hardwareDesign = hardwareDesignService.getAll();
			
			List<SecurityRequirements> securityrequirements = securityRequirementsService.getAll();
			
			//List<Rules> allRules  = rules.getAll();
			
		 //	nd.setDesignRevision(designRevision);
			nd.setHardwareDesign(hardwareDesign);
			nd.setProjectRevision(projectRevision);
			nd.setSecurityRequirements(securityrequirements);
		//	nd.setRules(allRules);
			project.setNodes(nd);
			
			projectResponse.add(project);
			
		});
		
		tr.setProjectResponse(projectResponse);
		
		
		return ResponseEntity.ok(tr);
	}
	
	
}
