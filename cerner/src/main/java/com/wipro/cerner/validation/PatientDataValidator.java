package com.wipro.cerner.validation;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;

import com.wipro.cerner.entity.Address;
import com.wipro.cerner.entity.Patient;
import com.wipro.cerner.entity.PhoneNumber;
import com.wipro.cerner.exception.BussinessValidationException;


/*
 * validate patiernt details
 */
public class PatientDataValidator {

	/*
	 * validate patiernt name with regex of alaphhets
	 */
	private static  void validatePatientName(String patientName) throws BussinessValidationException {
		String regex = "^[a-zA-Z\\\\s]*$";
		boolean isValidInput = false;
        Pattern p = Pattern.compile(regex);
        if (!Objects.isNull(patientName)) {
        	Matcher m = p.matcher(patientName);
        	isValidInput = m.matches() ? true : false;
        }
        
  if(!isValidInput) {
	  throw new BussinessValidationException("Invalid Patient Name", "ERROR003");
  }	    
	}
	
	
	/*
	 * validate patiernt gender if is present or not
	 */
	private static  void validatePatientGender(String patientGender) throws BussinessValidationException {
		if(Objects.isNull(patientGender)) {
			throw new BussinessValidationException("Please Enter Patient Gender", "ERROR004");
		}
	}
	/*
	 * validate patiernt dob is present or not
	 */
	
	public static  void validatePatientDob(Date patientDob) throws BussinessValidationException {
		if(Objects.isNull(patientDob)) {
			throw new BussinessValidationException("Please Enter Patient Dob", "ERROR005");
		}
	}
	
	/*
	 * validate patiernt phonenumber is present or not and it contains 10 digit
	 */
	private static  void validatePhoneNumber(List<PhoneNumber> phoneNumberList)  throws BussinessValidationException{
		
		if(CollectionUtils.isEmpty(phoneNumberList)) {
			throw new BussinessValidationException("Please Enter Patient PhoneNumber", "ERROR006");
		}else {
			for(PhoneNumber phneNumb : phoneNumberList) {
				if(!Objects.isNull(phneNumb)) {
					
					String regex = "^\\d{10}$";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(phneNumb.getPhoneNumber());
					boolean isPhoneNmberMatches= matcher.matches() ? true : false;
					if(!isPhoneNmberMatches) {
						throw new BussinessValidationException("Please Enter valid PhoneNumber", "ERROR010");
					}
				}
			}
		}
		
		
		
		
		
		
	}
		
	/*
	 * validate patiernt address.check it needs to be mandatory
	 */
		private static  void validateAddress(List<Address> addressList) throws BussinessValidationException {
			
			if(CollectionUtils.isEmpty(addressList)) {
				throw new BussinessValidationException("Please Enter Patient address", "ERROR007");
			}
		
	}

		/*
		 * validate patient details method called from service		 */
	
	public   static void validatePatientData(Patient  patient) throws BussinessValidationException {
		validatePatientName(patient.getPatientName());
		validatePatientGender(patient.getPatientGender());
		validatePatientDob(patient.getPatientDob());
		validatePhoneNumber(patient.getPatientPhList());
		validateAddress(patient.getPatientAddressList());
	}
	
	
}
