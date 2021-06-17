package com.tortuga.security.governance.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.models.ProjectRevision;
import com.tortuga.security.governance.platform.repository.ProjectRevisionRepository;

@Service
public class ProjectRevisionService {
	
	@Autowired
	ProjectRevisionRepository projectRevisionRepository;
	
	
	 public List<ProjectRevision> getAll(){
			return projectRevisionRepository.findAll();
		 }

}
