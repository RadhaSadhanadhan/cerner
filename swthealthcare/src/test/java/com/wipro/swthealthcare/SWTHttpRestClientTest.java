package com.wipro.swthealthcare;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.Test;

import com.wipro.swthealthcare.model.Patient;


public class SWTHttpRestClientTest {



	@Test
	public void testFecthAll() {
		try {
			List<Patient> list =SWTHttpRestClient.fecthAllPatients();
			assertNotNull(list);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void fetchPatientByIdTest() {
		try {
			Patient pat =SWTHttpRestClient.fetchPatientById(1L);
			assertNotNull(pat);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void savePatientTest() {
		try {
			Patient pat = new Patient();
			HttpResponse<String> response =SWTHttpRestClient.savePatient(pat);
			assertNotNull(response);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}



	@Test
	public void updatePatientTest() {
		try {
			Patient pat = new Patient();
			HttpResponse<String> response =	SWTHttpRestClient.updatePatient(pat);
			assertNotNull(response);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void deletePatientstest() {
		try {
			SWTHttpRestClient.deletePatient(1l);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
