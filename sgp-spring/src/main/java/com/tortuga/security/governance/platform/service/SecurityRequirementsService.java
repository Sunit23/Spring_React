package com.tortuga.security.governance.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.models.SecurityRequirements;
import com.tortuga.security.governance.platform.payload.response.SecurityRequirementsResponse;
import com.tortuga.security.governance.platform.repository.SecurityRequirementsRepository;

@Service
public class SecurityRequirementsService {
	
	@Autowired
	SecurityRequirementsRepository securityRequirementsRepository;
	
	
	 public List<SecurityRequirements> getAll(){
			return securityRequirementsRepository.findAll();
		 }


	public List<SecurityRequirementsResponse> findByProjectId(String projectName) {
		
		return securityRequirementsRepository.findByProjectId(projectName);
	}

}
