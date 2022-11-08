package com.codingmart.categorymicroservice.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApiResponse {
	private Integer code;
	private HttpStatus status;
	private Object data;
	private ErrorResponse error;

	public ApiResponse() {
		this.code=HttpStatus.OK.value();
		this.status = HttpStatus.OK;
	}

	public void resetResponse(){
		System.out.println(data);
		this.code=HttpStatus.OK.value();
		this.status=HttpStatus.OK;
		this.data=null;
		this.error=null;
		System.out.println(data);

	}
}

