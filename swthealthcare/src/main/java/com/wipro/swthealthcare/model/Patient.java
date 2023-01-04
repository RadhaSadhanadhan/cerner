package com.wipro.swthealthcare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Patient class for storing patient details from the api 
 * using the values in swt .we can store patient details and use in display
 * @author  Radha 
 * @version 1.0
 * @since   29/12/2022
 */

public class Patient{

	private Long patientId;

	private String patientName;

	private String patientGender;

	private Date patientDob;

	private List<Address> patientAddressList = new ArrayList<>();


	private  List<PhoneNumber> patientPhList = new ArrayList<>();

	/*
	 *  get patientId of the patient
	 */
	public Long getPatientId() {
		return patientId;
	}
	/*
	 *  setting patientId of the patient
	 */
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	/*
	 *  get patientName of the patient
	 */
	public String getPatientName() {
		return patientName;
	}
	/*
	 *  setting patientName of the patient
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	/*
	 *  get patient gender of the patient
	 */
	public String getPatientGender() {
		return patientGender;
	}
	/*
	 *  setting patient gender of the patient
	 */
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	/*
	 *  get dob of the patient
	 */
	public Date getPatientDob() {
		return patientDob;
	}
	/*
	 *  setting dob of the patient
	 */
	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}
	/*
	 *  get addressList of the patient
	 */
	public List<Address> getPatientAddressList() {
		return patientAddressList;
	}
	/*setting addressList of the patient */

	public void setPatientAddressList(List<Address> patientAddressList) {
		this.patientAddressList = patientAddressList;
	}
	/*get phoneNumberList of the patient */
	public List<PhoneNumber> getPatientPhList() {
		return patientPhList;
	}
	/*setting phoneNumberList (PhoneNumber object) of the patient */
	public void setPatientPhList(List<PhoneNumber> patientPhList) {
		this.patientPhList = patientPhList;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", patientGender=" + patientGender
				+ ", patientDob=" + patientDob + ", patientAddressList=" + patientAddressList + ", patientPhList="
				+ patientPhList + "]";
	}
}
