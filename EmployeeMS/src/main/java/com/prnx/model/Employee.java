package com.prnx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	private Long empId;
	private String empName;
	private String empDesignation;
	private Double empSalary;
	private String empDepartment;
}
