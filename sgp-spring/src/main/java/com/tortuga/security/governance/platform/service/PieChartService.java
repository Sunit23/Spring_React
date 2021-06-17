package com.tortuga.security.governance.platform.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.models.PieChart;
import com.tortuga.security.governance.platform.repository.PieChartRepository;

@Service
public class PieChartService {
	
	@Autowired
	PieChartRepository pieChartRepository ;
	
	
	 public List<com.tortuga.security.governance.platform.models.PieChart> getAll(){
			return pieChartRepository.findAll();
		 }


	public Object createPie(@Valid PieChart pieChartRequest) {
		// TODO Auto-generated method stub
		return pieChartRepository.save(pieChartRequest);
	}

}
