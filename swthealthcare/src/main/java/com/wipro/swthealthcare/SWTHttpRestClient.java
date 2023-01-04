package com.wipro.swthealthcare;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.swthealthcare.model.Patient;


/**
* client class for connecting  with rest api
* The SWTHttpRestClient class invoke a rest api and returing response for each rest call
*this can be called from swt and response we can render in swt for display
* @author  Radha 
* @version 1.0
* @since   29/12/2022
*/
public class SWTHttpRestClient {
	 /**
	   * This method is used to get the list of all patients details
	   *  invoking the specific rest api using httprequest and http response
	   * @return List This returns list of all the patients
	   */
	public static List<Patient> fecthAllPatients() throws IOException, InterruptedException {  
		String  serviceUrl = "http://localhost:8080/patient/all" ;
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).
				header("Content-Type", "application/json").GET().build();
		var  client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(response.body());
		System.out.println(response.statusCode());
		return mapper.readValue(response.body(), new TypeReference<List<Patient>>() {});

	}
	
	public static List<Patient> getPatientByName(String name) throws IOException, InterruptedException{
		List<Patient> patientList = fecthAllPatients();
		List<Patient> listFilteresByPatient = patientList.stream().filter(x->name.equalsIgnoreCase(x.getPatientName())).collect(Collectors.toList());
		return listFilteresByPatient;
	}

	   /**
	   * This method is used to get the  patients details  by id
	   * invoking the specific rest api using httprequest and http response
	   * @param Long patientId to get the particular patients 
	   * @return Patient This returns patients object based on id
	   */

	public static  Patient fetchPatientById(Long patientId) throws IOException, InterruptedException { 
		String  serviceUrl = "http://localhost:8080/patient/";
		StringBuilder sb = new StringBuilder();
		sb.append(serviceUrl).append(patientId);
		var request = HttpRequest.newBuilder().uri(URI.create(sb.toString())).
				header("Content-Type", "application/json").GET().build();
		var  client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("response.body()"+response.body());
		System.out.println(response.statusCode());
		return mapper.readValue(response.body(), new TypeReference<Patient>() {});

	}

	   /**
	   * This method is used to save the  patients details  
	   * invoking the specific rest api using httprequest and http response
	   * @param Patient patient details to save 
	   * @return String This returns response code
	   */
	public static HttpResponse<String> savePatient(Patient patient) throws IOException, InterruptedException {  
		String  serviceUrl = "http://localhost:8080/patient/save" ;
		var objectMapper = new ObjectMapper();
		String patientString =objectMapper.writeValueAsString(patient);
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).
				header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(patientString)).build();
		var  client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());
		return response;
	}

	
	   /**
	   * This method is used to update the  patients details  
	   * invoking the specific rest api using httprequest and http response
	   * @param Patient patient object details to modify  
	   * @return String This returns response code
	   */

	public static HttpResponse<String> updatePatient(Patient patient) throws IOException, InterruptedException {  
		String  serviceUrl = "http://localhost:8080/patient/update" ;
		var objectMapper = new ObjectMapper();
		String patientString =objectMapper.writeValueAsString(patient);
		var request = HttpRequest.newBuilder().uri(URI.create(serviceUrl)).
				header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(patientString)).build();
		var  client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		System.out.println(response.statusCode());
		return response;

	}

	
	  /**
	   * This method is used to delete the  patients details  by id
	   * invoking the specific rest api using httprequest and http response
	   * @param Long patientId to delete the specific patients 
	   */

	public static void deletePatient(Long patientId) throws IOException, InterruptedException { 
		String  serviceUrl = "http://localhost:8080/patient/delete/";
		StringBuilder sb = new StringBuilder();
		sb.append(serviceUrl).append(patientId);
		var request = HttpRequest.newBuilder().uri(URI.create(sb.toString())).
				header("Content-Type", "application/json").DELETE().build();
		var  client = HttpClient.newHttpClient();
		client.send(request, HttpResponse.BodyHandlers.ofString());

	}

}

