package com.tortuga.security.governance.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.models.HardwareDesign;
import com.tortuga.security.governance.platform.repository.HardwareDesignRepository;

@Service
public class HardwareDesignService {

	@Autowired
	HardwareDesignRepository hardwareDesignRepository;
	
	 public List<HardwareDesign> getAll(){
			return hardwareDesignRepository.findAll();
		 }
}
