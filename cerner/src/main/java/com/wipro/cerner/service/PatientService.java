package com.wipro.cerner.service;

import java.util.List;

import com.wipro.cerner.entity.Patient;

public interface PatientService {
	
	Patient savePatient(Patient patient);
	 
    List<Patient> fetchPatientList();
    
    Patient findPatientById(Long patientId);
 
    Patient updatePatient(Patient patient,
                                Long patientId);
 
    void deletePatientById(Long patientId);
    
    

}
