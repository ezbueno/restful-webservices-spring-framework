package com.buenoezandro.app.ws.exceptions;

import static java.util.Objects.isNull;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.buenoezandro.app.ws.ui.model.response.ErrorMessage;

@RestControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { Exception.class })
	private ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String errorMessageDescription = ex.getLocalizedMessage();

		if (isNull(errorMessageDescription)) {
			errorMessageDescription = ex.toString();
		}

		var errorMessage = new ErrorMessage(LocalDate.now(), errorMessageDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { NullPointerException.class })
	private ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
		String errorMessageDescription = ex.getLocalizedMessage();

		if (isNull(errorMessageDescription)) {
			errorMessageDescription = ex.toString();
		}

		var errorMessage = new ErrorMessage(LocalDate.now(), errorMessageDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { UserServiceException.class })
	private ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		String errorMessageDescription = ex.getLocalizedMessage();

		if (isNull(errorMessageDescription)) {
			errorMessageDescription = ex.toString();
		}

		var errorMessage = new ErrorMessage(LocalDate.now(), errorMessageDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
