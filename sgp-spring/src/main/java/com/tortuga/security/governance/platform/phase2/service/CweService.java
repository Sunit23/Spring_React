package com.tortuga.security.governance.platform.phase2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.phase2.models.CWE;
import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.Test;
import com.tortuga.security.governance.platform.phase2.repository.CweRepository;
import com.tortuga.security.governance.platform.phase2.repository.SGPProjectRepository;
import com.tortuga.security.governance.platform.phase2.repository.TestRepository;

@Service
public class CweService {
	
	@Autowired
	CweRepository cweRepository;
	
	 public List<CWE> getAll(){
			return cweRepository.findAll();
		 }

}
