package com.wipro.cerner.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cerner.entity.Patient;
import com.wipro.cerner.exception.BussinessValidationException;
import com.wipro.cerner.repository.PatientRepository;
import com.wipro.cerner.validation.PatientDataValidator;

@Service
public class PatientServiceImpl implements PatientService {
	
	private static Logger logger  = LogManager.getLogger(PatientServiceImpl.class);
	@Autowired
	PatientRepository patientRepository;
	
	
	@Override
	public Patient savePatient(Patient patient) throws BussinessValidationException {
		PatientDataValidator.validatePatientData(patient);
		return patientRepository.save(patient);
	}

	
	@Override
	public List<Patient> fetchPatientList() {
		logger.debug("Inside fecth patientList");
		return patientRepository.findAll();
	}

	
	
	@Override
	public Patient findPatientById(Long patientId) {
		Optional<Patient> patientById= patientRepository.findById(patientId);
		return  patientById.isPresent() ? patientById.get() : null;
	}

	
	
	@Override
	public Patient updatePatient(Patient patient, Long patientId)  throws BussinessValidationException{
		patient.setPatientId(patientId);
		PatientDataValidator.validatePatientData(patient);
		return patientRepository.save(patient);
	}

	
	
	@Override
	public void deletePatientById(Long patientId) {
		patientRepository.deleteById(patientId);
	}
}
