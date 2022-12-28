package com.wipro.cerner.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wipro.cerner.entity.Address;
import com.wipro.cerner.entity.Patient;
import com.wipro.cerner.entity.PhoneNumber;
import com.wipro.cerner.exception.BussinessValidationException;
import com.wipro.cerner.repository.PatientRepository;


/*
 * test class to test the patient service 
 */
@WebMvcTest(PatientServiceImpl.class)

public class PatientServiceImplTest  {
	
	private static Logger logger  = LogManager.getLogger(PatientServiceImplTest.class);
	
	@Autowired
	private PatientService  patientService;
	
	@MockBean 
	private PatientRepository patientRepository; 
	
	
	
	

	@Test
	public void fetchPatientListTest() {
		logger.debug("Inside fecth patientList");
		List<Patient> patientList = new ArrayList<>();
		Patient patient = new Patient();
		patient.setPatientId(1l);
		patient.setPatientName("radha");
		patient.setPatientDob(new Date());
		patient.setPatientAddressList(new ArrayList<>());
		patient.setPatientAddressList(new ArrayList<>());
		patientList.add(patient);
		Mockito.when(patientRepository.findAll()).thenReturn(patientList) ;
		List<Patient> resultList = patientService.fetchPatientList();
		assertEquals(1, resultList.size());
	}
	


@Test
public void findPatientByIdTest() {
	logger.debug("Inside findPatientByIdTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("radha");
	patient.setPatientDob(new Date());
	patient.setPatientAddressList(new ArrayList<>());
	patient.setPatientAddressList(new ArrayList<>());
	Optional<Patient> pat = Optional.ofNullable(patient);
	Mockito.when(patientRepository.findById(1l)).thenReturn(pat) ;
	Patient patientRes = patientService.findPatientById(1l);
	assertEquals(1l, patientRes.getPatientId());
	assertEquals("radha", patientRes.getPatientName());
}


@Test
public void findPatientByIdNullTest() {
	logger.debug("Inside findPatientByIdNullTest");
	Optional<Patient> pat = Optional.ofNullable(null);
	Mockito.when(patientRepository.findById(1l)).thenReturn(pat) ;
	Patient patientRes = patientService.findPatientById(1l);
	assertEquals(patientRes, null);
}


@Test
public void deletePatientByIdTest() {
	logger.debug("Inside deletePatientByIdTest");
	doNothing().when(patientRepository).deleteById(1l);
	patientService.deletePatientById(1l);
}

@Test
public void savePatientTest() throws BussinessValidationException {
	logger.debug("Inside savePatientTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("radha");
	patient.setPatientGender("Female");
	patient.setPatientDob(new Date());
	List<Address> addresList = new ArrayList<>();
	Address add = new Address();
	add.setAddId(1);
	add.setAddress("address");
	addresList.add(add);
	patient.setPatientAddressList(addresList);
	List<PhoneNumber> phoneNumList = new ArrayList<>();
	PhoneNumber phone = new PhoneNumber();
	phone.setPhId(1);
	phone.setPhoneNumber("1234567890");
	phoneNumList.add(phone);
	patient.setPatientPhList(phoneNumList);
	Mockito.when(patientRepository.save(patient)).thenReturn(patient);
	patientService.savePatient(patient);
	assertEquals(1, patient.getPatientAddressList().get(0).getAddId());
	assertEquals("address", patient.getPatientAddressList().get(0).getAddress());
	assertEquals(1, patient.getPatientPhList().get(0).getPhId());
	assertEquals("1234567890", patient.getPatientPhList().get(0).getPhoneNumber());
}


@Test
public void savePatientEmptyGenderTest()  {
	logger.debug("Inside savePatientTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("radha");
	patient.setPatientDob(new Date());
	List<Address> addresList = new ArrayList<>();
	Address add = new Address();
	add.setAddress("address");
	addresList.add(add);
	patient.setPatientAddressList(addresList);
	List<PhoneNumber> phoneNumList = new ArrayList<>();
	PhoneNumber phone = new PhoneNumber();
	phone.setPhoneNumber("12432454656");
	phoneNumList.add(phone);
	patient.setPatientPhList(phoneNumList);
	Mockito.when(patientRepository.save(patient)).thenReturn(patient);
	try {
		patientService.savePatient(patient);
	} catch (BussinessValidationException e) {
		assertEquals("Please Enter Patient Gender", e.getMessage());
		assertEquals("ERROR004", e.getErrorMsgCode());
	}
}


@Test
public void savePatientEmptyName()  {
	logger.debug("Inside savePatientTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("1234");
	patient.setPatientDob(new Date());
	List<Address> addresList = new ArrayList<>();
	Address add = new Address();
	add.setAddress("address");
	addresList.add(add);
	patient.setPatientAddressList(addresList);
	List<PhoneNumber> phoneNumList = new ArrayList<>();
	PhoneNumber phone = new PhoneNumber();
	phone.setPhoneNumber("12432454656");
	phoneNumList.add(phone);
	patient.setPatientPhList(phoneNumList);
	Mockito.when(patientRepository.save(patient)).thenReturn(patient);
	try {
		patientService.savePatient(patient);
	} catch (BussinessValidationException e) {
		assertEquals("Invalid Patient Name", e.getMessage());
	}
}

@Test
public void savePatientEmptyDobTest()  {
	logger.debug("Inside savePatientTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("radha");
	patient.setPatientGender("Female");
	List<Address> addresList = new ArrayList<>();
	Address add = new Address();
	add.setAddress("address");
	addresList.add(add);
	patient.setPatientAddressList(addresList);
	List<PhoneNumber> phoneNumList = new ArrayList<>();
	PhoneNumber phone = new PhoneNumber();
	phone.setPhoneNumber("12432454656");
	phoneNumList.add(phone);
	patient.setPatientPhList(phoneNumList);
	Mockito.when(patientRepository.save(patient)).thenReturn(patient);
	try {
		patientService.savePatient(patient);
	} catch (BussinessValidationException e) {
		assertEquals("Please Enter Patient Dob", e.getMessage());
	}
}


@Test
public void savePatientTestEmptyAddress()  {
	logger.debug("Inside savePatientTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("radha");
	patient.setPatientGender("Female");
	patient.setPatientDob(new Date());
	List<PhoneNumber> phoneNumList = new ArrayList<>();
	PhoneNumber phone = new PhoneNumber();
	phone.setPhoneNumber("1234567890");
	phoneNumList.add(phone);
	patient.setPatientPhList(phoneNumList);
	Mockito.when(patientRepository.save(patient)).thenReturn(patient);
	try {
		patientService.savePatient(patient);
	} catch (BussinessValidationException e) {
		assertEquals("Please Enter Patient address", e.getMessage());
	}
}

@Test
public void savePatientTestEmptyPhnumber()  {
	logger.debug("Inside savePatientTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("radha");
	patient.setPatientGender("Female");
	patient.setPatientDob(new Date());
	List<Address> addresList = new ArrayList<>();
	Address add = new Address();
	add.setAddress("address");
	addresList.add(add);
	patient.setPatientAddressList(addresList);
	Mockito.when(patientRepository.save(patient)).thenReturn(patient);
	try {
		patientService.savePatient(patient);
	} catch (BussinessValidationException e) {
		assertEquals("Please Enter Patient PhoneNumber", e.getMessage());
	}
}


@Test
public void updatePatientTest() throws BussinessValidationException {
	logger.debug("Inside savePatientTest");
	Patient patient= new Patient();
	patient.setPatientId(1l);
	patient.setPatientName("radha");
	patient.setPatientGender("Female");
	patient.setPatientDob(new Date());
	List<Address> addresList = new ArrayList<>();
	Address add = new Address();
	add.setAddress("address");
	addresList.add(add);
	patient.setPatientAddressList(addresList);
	List<PhoneNumber> phoneNumList = new ArrayList<>();
	PhoneNumber phone = new PhoneNumber();
	phone.setPhoneNumber("1234567890");
	phoneNumList.add(phone);
	patient.setPatientPhList(phoneNumList);
	Mockito.when(patientRepository.save(patient)).thenReturn(patient);
	patientService.updatePatient(patient,1L);
}

}
