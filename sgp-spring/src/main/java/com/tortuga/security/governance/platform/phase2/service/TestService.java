package com.tortuga.security.governance.platform.phase2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.phase2.models.SGPProject;
import com.tortuga.security.governance.platform.phase2.models.Test;
import com.tortuga.security.governance.platform.phase2.repository.SGPProjectRepository;
import com.tortuga.security.governance.platform.phase2.repository.TestRepository;

@Service
public class TestService {
	
	@Autowired
	TestRepository testRepository;
	
	 public List<Test> getAll(){
			return testRepository.findAll();
		 }
	 
	 public List<Test> findByName(String name){
		 return testRepository.findByName(name);
	 }

}
