package com.wipro.cerner.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.cerner.entity.Patient;
import com.wipro.cerner.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientReadController {
	private static Logger logger  = LogManager.getLogger(PatientReadController.class);
	@Autowired
	PatientService patientservice;
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> readPatientList(){
		logger.debug("Inside readPatientList");
		List<Patient> patientList=  patientservice.fetchPatientList();
		 return new ResponseEntity<>(patientList, HttpStatus.OK);
	}
	
	@GetMapping("/getpatient/{patientId}")
	public ResponseEntity<Patient> readPatientById( @PathVariable("patientId") Long patientId){
		logger.debug("Inside readPatientById");
		Patient patient = patientservice.findPatientById(patientId);
		return new ResponseEntity<>(patient, HttpStatus.OK);
		
	}
	
	@PutMapping("/updatepatient/{patientId}")
	public ResponseEntity<Patient> updatePatient( @PathVariable("patientId") Long patientId, @RequestBody Patient patient){
		logger.debug("Inside updatePatientById"); 
		Patient patientUpdatedObj = patientservice.updatePatient(patient, patientId);
		return new ResponseEntity<>(patientUpdatedObj, HttpStatus.OK);
		
	}
	
	@PostMapping("/savepatient")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
		logger.debug("Inside savepatient");
		Patient patientObj=  patientservice.savePatient(patient);
		 return new ResponseEntity<>(patientObj, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{patientId}")
			public ResponseEntity<Patient> deletePatient( @PathVariable("patientId") Long patientId){
				logger.debug("Inside v");
				patientservice.deletePatientById(patientId);
				return new ResponseEntity<>(HttpStatus.OK);
				
	}
	
}
