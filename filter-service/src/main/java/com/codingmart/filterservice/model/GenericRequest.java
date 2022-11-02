package com.codingmart.filterservice.model;

import com.codingmart.productmicroservice.custom.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericRequest {
    private Integer code;
    private HttpStatus status;
    private Object data;
    private ErrorResponse error;

}
