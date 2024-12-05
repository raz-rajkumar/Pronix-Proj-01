package com.prnx.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeErrorResponse {
	
	private Integer status;
	private String message;
	private Long timeStamp;
	
}
