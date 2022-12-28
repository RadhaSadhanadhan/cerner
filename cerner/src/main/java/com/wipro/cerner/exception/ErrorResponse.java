package com.wipro.cerner.exception;

import java.io.Serializable;


/**
 * Error response class for sending error details in reponse
 */
public class ErrorResponse implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String errorCode;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
