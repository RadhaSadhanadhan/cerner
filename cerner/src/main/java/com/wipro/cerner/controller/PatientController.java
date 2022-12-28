package com.wipro.cerner.controller;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.cerner.entity.Patient;
import com.wipro.cerner.exception.BussinessValidationException;
import com.wipro.cerner.exception.ResourceNotFoundException;
import com.wipro.cerner.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	private static Logger logger  = LogManager.getLogger(PatientController.class);
	
	@Autowired
	PatientService patientService;
	
	
	/**
     * List all patients 
     * @return the patient details after fecthing 
     */
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> readPatientList() throws ResourceNotFoundException{
		logger.debug("Inside readPatientList");
		List<Patient> patientList=  patientService.fetchPatientList();
		if(CollectionUtils.isEmpty(patientList)) {
			throw new ResourceNotFoundException("No Patient Present", "ER001");
		}
		 return new ResponseEntity<>(patientList, HttpStatus.OK);
	}
	/**
     * find the patient by id
    * @param patientId the identifier of the patient 
     * @return the patient details after fetching 
     */
	@GetMapping("/{patientId}")
	public ResponseEntity<Patient> readPatientById( @PathVariable("patientId") Long patientId) throws ResourceNotFoundException{
		logger.debug("Inside readPatientById");
		Patient patient = patientService.findPatientById(patientId);
		
		if(Objects.isNull(patient)) {
			throw new ResourceNotFoundException("No Patient Present for the id", "ER002");
		}
		return new ResponseEntity<>(patient, HttpStatus.OK);
		
	}
	
	
	/**
     * update  a existing patient 
     * @param patient the details of the new patient
     * @param patientId the identifier of the patient 
     * @return the patient details after updating and doing some basic validation
     */
	@PutMapping("/update/{patientId}")
	public ResponseEntity<Patient> updatePatient( @PathVariable("patientId") Long patientId, @RequestBody Patient patient) throws BussinessValidationException{
		logger.debug("Inside updatePatientById"); 
		Patient patientUpdatedObj = patientService.updatePatient(patient, patientId);
		return new ResponseEntity<>(patientUpdatedObj, HttpStatus.OK);
		
	}
	
	
	/**
     * Creates a new patient 
     * @param patient the details of the new patient
     * @return the patient details after creating and doing some basic validation
     */
	@PostMapping("/save")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) throws BussinessValidationException{
		logger.debug("Inside savepatient");
		Patient patientObj=  patientService.savePatient(patient);
		 return new ResponseEntity<>(patientObj, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{patientId}")
			public ResponseEntity<Patient> deletePatient( @PathVariable("patientId") Long patientId){
				logger.debug("Inside deletePatient");
				patientService.deletePatientById(patientId);
				return new ResponseEntity<>(HttpStatus.OK);
				
	}
	
	
}
