package com.codingmart.productmicroservice.exceptionhandler;


import com.codingmart.productmicroservice.custom.ErrorResponse;
import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GenericResponse> Exception(Exception exception) {
        ErrorResponse errorResponse;
        GenericResponse genericResponse=new GenericResponse();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        if (exception instanceof NotFoundException){
            errorResponse = new ErrorResponse(exception.getMessage(),stackTrace);
            genericResponse.setStatus(HttpStatus.NOT_FOUND);
            genericResponse.setCode(HttpStatus.NOT_FOUND.value());
        }
        else {

             errorResponse = new ErrorResponse( exception.getMessage(),stackTrace);
             genericResponse.setStatus(HttpStatus.BAD_REQUEST);
            genericResponse.setCode(HttpStatus.BAD_REQUEST.value());
        }

        genericResponse.setError(errorResponse);
        return ResponseEntity.status(genericResponse.getStatus()).body(genericResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericResponse genericResponse=new GenericResponse();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        genericResponse.setError(new ErrorResponse(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(),stackTrace));
        genericResponse.setStatus(HttpStatus.BAD_REQUEST);
        genericResponse.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);

    }
}

