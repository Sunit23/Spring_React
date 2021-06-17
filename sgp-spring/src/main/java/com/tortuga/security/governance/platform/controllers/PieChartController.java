package com.tortuga.security.governance.platform.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortuga.security.governance.platform.models.PieChart;
import com.tortuga.security.governance.platform.payload.request.PieChartRequest;
import com.tortuga.security.governance.platform.service.PieChartService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pie-chart")
public class PieChartController {

	@Autowired
	PieChartService pieChartService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<PieChart> getAllUser(){
		return pieChartService.getAll();
	}
	
	@PostMapping("/insertValuePie")
	public ResponseEntity<?> createPie(@Valid @RequestBody PieChartRequest pieChartRequest){
		PieChart pie = new PieChart(pieChartRequest.getTitle(), pieChartRequest.getColor(), pieChartRequest.getValue());
		return ResponseEntity.ok(pieChartService.createPie(pie)); 
	}
	
}
