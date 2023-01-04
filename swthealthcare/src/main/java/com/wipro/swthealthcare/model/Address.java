package com.wipro.swthealthcare.model;


/**
* Address class for storing address details from the api 
* using the values in swt .we can store address details and use in display
* @author  Radha 
* @version 1.0
* @since   29/12/2022
*/
public class Address {
	private Integer addId;
	private String address;
	
	/*
	 *  get addressId of the patient
	 */
	public Integer getAddId() {
		return addId;
	}
	
	/*
	 *  setting addressId of the patient
	 */
	public void setAddId(Integer addId) {
		this.addId = addId;
	}
	
	/*
	 *  get address of the patient
	 */
	public String getAddress() {
		return address;
	}
	/*
	 *  setting the address of the patient
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Address [addId=" + addId + ", address=" + address + "]";
	}	

}
