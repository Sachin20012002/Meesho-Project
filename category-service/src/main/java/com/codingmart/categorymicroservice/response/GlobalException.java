package com.codingmart.categorymicroservice.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	
	private ApiResponse apiResponse;
    
	public  GlobalException(ApiResponse apiResponse) {
		this.apiResponse=apiResponse;
	}

	@ExceptionHandler
	public ResponseEntity<ApiResponse> handleBadRequestException(IdNotFound e) {
		apiResponse.setCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setStatus(HttpStatus.NOT_FOUND);
		apiResponse.setData(null);
		apiResponse.setError(new ErrorResponse("NOT_FOUND_EXCEPTION",e.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(apiResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse> handleException(Exception e) {
		apiResponse.setError(new ErrorResponse("Internal Server Error",e.getLocalizedMessage()));
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
	}
	
}
