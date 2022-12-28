package com.wipro.cerner.exception;


/*
 * generic user defined exception for resources is not found thrown from api operation with errormessage and errorcode
 */
public class ResourceNotFoundException extends Exception {

		private static final long serialVersionUID = -9079454849611061074L;
		
		public String errorMsgCode ;

		
		public String getErrorMsgCode() {
			return this.errorMsgCode;
		}

		public void setErrorMsgCode(String errorMsgCode) {
			this.errorMsgCode = errorMsgCode;
		}

		public ResourceNotFoundException( String message, String errorCode) {
			super(message);
			this.errorMsgCode = errorCode;
		}
}
