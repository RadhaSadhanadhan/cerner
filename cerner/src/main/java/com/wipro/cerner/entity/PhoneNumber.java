package com.wipro.cerner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * phone number Entity mapping with db
 */
@Entity
@Table(name="cerner_patient_phonenumb")
public class PhoneNumber {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer phId;
	
	public Integer getPhId() {
		return phId;
	}
	public void setPhId(Integer phId) {
		this.phId = phId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name= "cerner_patient_Phone")
	private String phoneNumber;
}
