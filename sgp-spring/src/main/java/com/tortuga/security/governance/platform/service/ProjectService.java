package com.tortuga.security.governance.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.models.Project;
import com.tortuga.security.governance.platform.models.Role;
import com.tortuga.security.governance.platform.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	 public List<Project> getAll(){
			return projectRepository.findAll();
		 }

}
