package com.Rishabh.ecommerce_cartlist.common;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ApiResponse {
    private Integer status;
    private Object data;
    private Object error;

    public ApiResponse() {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.error = error;
    }
}
