package com.wipro.cerner.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * any exception thrown from controller is handled
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception exception){

		ErrorResponse error = new ErrorResponse();
		error.setErrorMessage(exception.getMessage());
		return error;
	}

	@ExceptionHandler(BussinessValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleValidationException(final BussinessValidationException exception,
			final HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(exception.getErrorMsgCode());
		return error;
	}


	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(exception.getErrorMsgCode());
		return error;
	}





}