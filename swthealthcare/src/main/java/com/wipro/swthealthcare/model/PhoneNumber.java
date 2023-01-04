package com.wipro.swthealthcare.model;


/**
* Phonenumber class for storing phonenNumber details from the api 
* using the values in swt .we can store phonenumber details and use in display
* @author  Radha 
* @version 1.0
* @since   29/12/2022
*/
public class PhoneNumber {
	
	private Integer phId;
	
	
	private String phoneNumber;
	
	/*
	 *  get phoneNumberId of the patient
	 */
	public Integer getPhId() {
		return phId;
	}
	/*
	 *  setting  phoneNumberId of the patient
	 */
	public void setPhId(Integer phId) {
		this.phId = phId;
	}
	
	/*
	 *  getting  phoneNumber of the patient
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/*
	 *  setting  phoneNumber of the patient
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	@Override
	public String toString() {
		return "PhoneNumber [phId=" + phId + ", phoneNumber=" + phoneNumber + "]";
	}
}
