package com.example.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@Autowired
	private ApiResponse apiResponse;
	
	@ExceptionHandler
	public ResponseEntity handleException(Exception e) {
		apiResponse.setError("Internal Server Error");
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
	}
	
//	@ExceptionHandler
//	public ResponseEntity IdNotFound() {
//		apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(apiResponse);
//	}
}
