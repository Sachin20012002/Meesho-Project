package com.codingmart.productmicroservice.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GenericResponse {
        private Integer code;
        private HttpStatus status;
        private Object data;
        private ErrorResponse error;

        public GenericResponse() {
                this.code=HttpStatus.OK.value();
                this.status = HttpStatus.OK;
        }
}
