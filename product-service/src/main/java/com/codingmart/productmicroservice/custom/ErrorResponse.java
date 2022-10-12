package com.codingmart.productmicroservice.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private String stackTrace;

}
