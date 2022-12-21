package com.wipro.cerner.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientReadController {
	private static Logger logger  = LogManager.getLogger(PatientReadController.class);
	@GetMapping("/getAllPatients")
	public List<Object> readPatientList(){
		logger.debug("Inside readPatientList");
		return null;
	}
	
	@GetMapping("/getAllPatients/{patientId}")
	public Object readPatientById( @PathVariable("patientId") Long patientId){
		logger.debug("Inside readPatientList");
		return null;
		
		
	}
}
