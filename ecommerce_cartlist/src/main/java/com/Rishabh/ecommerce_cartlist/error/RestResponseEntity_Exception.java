package com.Rishabh.ecommerce_cartlist.error;

import com.Rishabh.ecommerce_cartlist.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@ResponseStatus
public class RestResponseEntity_Exception extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CartItemNotFound_Exception.class)
    public ResponseEntity<ErrorMessage> cartIdNotFoundException(CartItemNotFound_Exception exception, WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(CartNotFound_Exception.class)
    public ResponseEntity<ErrorMessage> cartIdNotFoundException(CartNotFound_Exception exception, WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(WishListNotFound_Exception.class)
    public ResponseEntity<ErrorMessage> cartIdNotFoundException(WishListNotFound_Exception exception, WebRequest request){
        ErrorMessage message=new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
