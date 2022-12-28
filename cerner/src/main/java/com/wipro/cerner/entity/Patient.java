package com.wipro.cerner.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/*
 * patient Entity mapping with db
 */

@Entity
@Table(name="cerner_patient")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	
	@Column(name= "cerner_patient_name")
	private String patientName;
	
	@Column(name= "cerner_patient_gender")
	private String patientGender;
	
	@Column(name= "cerner_patient_dob")
	private Date patientDob;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "cerner_patient_id", referencedColumnName = "patientId")
	private List<Address> patientAddressList = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "cerner_patient_id", referencedColumnName = "patientId")
	private  List<PhoneNumber> patientPhList = new ArrayList<>();

	
	
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public Date getPatientDob() {
		return patientDob;
	}
	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}
	public List<Address> getPatientAddressList() {
		return patientAddressList;
	}
	public void setPatientAddressList(List<Address> patientAddressList) {
		this.patientAddressList = patientAddressList;
	}
	public List<PhoneNumber> getPatientPhList() {
		return patientPhList;
	}
	public void setPatientPhList(List<PhoneNumber> patientPhList) {
		this.patientPhList = patientPhList;
	}

	
}
