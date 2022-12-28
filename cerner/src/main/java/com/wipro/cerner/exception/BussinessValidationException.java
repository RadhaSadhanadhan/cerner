package com.wipro.cerner.exception;


/*
 * valiadtion exception thrown from service during api operation with errorcode and errormessage
 */
public class BussinessValidationException  extends Exception{
	
	private static final long serialVersionUID = 1L;
	public String errorMsgCode ;

	public String getErrorMsgCode() {
		return this.errorMsgCode;
	}

	public void setErrorMsgCode(String errorMsgCode) {
		this.errorMsgCode = errorMsgCode;
	}

	public BussinessValidationException( String message, String errorCode) {
		super(message);
		this.errorMsgCode = errorCode;
	}

}
