package com.wipro.cerner.service;

import java.util.List;

import com.wipro.cerner.entity.Patient;
import com.wipro.cerner.exception.BussinessValidationException;

public interface PatientService {
	
	
	/**
     * Creates a new patient 
     * @param patient the details of the new patient
     * @return the patient details after creating and doing some basic validation
     */
	Patient savePatient(Patient patient) throws BussinessValidationException;
	 
	
	/**
     * List all patients 
     * @return the patient details after fecthing 
     */
    List<Patient> fetchPatientList();
    
    /**
     * find the patient by id
    * @param patientId the identifier of the patient 
     * @return the patient details after fetching 
     */
    Patient findPatientById(Long patientId);
 
    /**
     * update  a existing patient 
     * @param patient the details of the new patient
     * @param patientId the identifier of the patient 
     * @return the patient details after updating and doing some basic validation
     */
    Patient updatePatient(Patient patient,
                                Long patientId) throws BussinessValidationException;
 
    
    /**
     * delete  a existing patient 
     * @param patientId the identifier of the patient 
     */
    void deletePatientById(Long patientId);
    
    

}
