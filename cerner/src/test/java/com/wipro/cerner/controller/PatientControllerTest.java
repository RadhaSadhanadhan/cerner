package com.wipro.cerner.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wipro.cerner.entity.Patient;
import com.wipro.cerner.exception.BussinessValidationException;
import com.wipro.cerner.exception.ResourceNotFoundException;
import com.wipro.cerner.service.PatientService;


/*
 * test class to test the controller class 
 */
@WebMvcTest(PatientController.class)

public class PatientControllerTest{
	
	@Autowired
	private PatientController  patientController;
	
	@MockBean 
	private PatientService patientService; 
	
	@Test
	public void readPatientList() throws ResourceNotFoundException {
		List<Patient> patientList = new ArrayList<>();
		Patient patient = new Patient();
		patient.setPatientId(1l);
		patient.setPatientName("radha");
		patient.setPatientDob(new Date());
		patient.setPatientAddressList(new ArrayList<>());
		patient.setPatientAddressList(new ArrayList<>());
		patientList.add(patient);
		Mockito.when(patientService.fetchPatientList()).thenReturn(patientList);
		ResponseEntity<List<Patient>> patientListResult ;
		patientListResult = patientController.readPatientList();
		assertEquals(HttpStatus.OK,patientListResult.getStatusCode());
		}
	
	
	@Test
	public void readPatientListEmpty() {
		Mockito.when(patientService.fetchPatientList()).thenReturn(null);
		ResponseEntity<List<Patient>> patientListResult ;
		try {
			patientListResult = patientController.readPatientList();
		} catch (ResourceNotFoundException e) {
			assertEquals("No Patient Present", e.getMessage());
			assertEquals("ER001", e.getErrorMsgCode());
		}
		}
	 
	
	
	
	@Test
	public void readPatientByIdTest() throws ResourceNotFoundException {
		Patient patient = new Patient();
		patient.setPatientId(1l);
		patient.setPatientName("radha");
		patient.setPatientDob(new Date());
		patient.setPatientAddressList(new ArrayList<>());
		patient.setPatientAddressList(new ArrayList<>());
		Mockito.when(patientService.findPatientById(1l)).thenReturn(patient);
		ResponseEntity<Patient> patientRes=patientController.readPatientById(1l) ;
		}
	
	@Test
	public void readPatientByIdErrorTest()  {
		
		Mockito.when(patientService.findPatientById(1l)).thenReturn(null);
		ResponseEntity<Patient> patientRes;
		try {
			patientRes = patientController.readPatientById(1l);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
}
	
	@Test
	public void savePatientTest() throws BussinessValidationException  {
		Patient patient = new Patient();
		patient.setPatientId(1l);
		patient.setPatientName("radha");
		patient.setPatientDob(new Date());
		patient.setPatientAddressList(new ArrayList<>());
		patient.setPatientAddressList(new ArrayList<>());
		Mockito.when(patientService.savePatient(patient)).thenReturn(patient);
		ResponseEntity<Patient> patientRes = patientController.savePatient(patient);
		assertEquals(HttpStatus.OK,patientRes.getStatusCode());
		
}
	
	
	@Test
	public void updatePatientTest() throws BussinessValidationException  {
		Patient patient = new Patient();
		patient.setPatientId(1l);
		patient.setPatientName("radha");
		patient.setPatientDob(new Date());
		patient.setPatientAddressList(new ArrayList<>());
		patient.setPatientAddressList(new ArrayList<>());
		Mockito.when(patientService.updatePatient(patient, 1l)).thenReturn(patient);
		ResponseEntity<Patient> patientRes = patientController.updatePatient(1l,patient);
		assertEquals(HttpStatus.OK,patientRes.getStatusCode());
}
	
	@Test
	public void deletePatientTest() throws BussinessValidationException  {
		Patient patient = new Patient();
		patient.setPatientId(1l);
		patient.setPatientName("radha");
		patient.setPatientDob(new Date());
		patient.setPatientAddressList(new ArrayList<>());
		patient.setPatientAddressList(new ArrayList<>());
		//Mockito.when(patientService.delete(patient, 1l)).thenReturn(patient);
		doNothing().when(patientService).deletePatientById(1l);
		ResponseEntity<Patient> patientRes = patientController.deletePatient(1l);
		assertEquals(HttpStatus.OK,patientRes.getStatusCode());
}
	

	
}