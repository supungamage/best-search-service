package com.bestSearch.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> resourceNotFoundException(ResourceNotFoundException ex) {
		log.error(ex.getMessage());
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

}
