package com.tortuga.security.governance.platform.phase2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.phase2.models.Design;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.repository.DesignRepository;
import com.tortuga.security.governance.platform.phase2.repository.SGPProjectRepository;

@Service
public class DesignService {
	
	@Autowired
	DesignRepository designRepository;
	
	 public List<Design> getAll(){
			return designRepository.findAll();
		 }

}
