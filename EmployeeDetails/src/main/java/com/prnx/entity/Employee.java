package com.prnx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	
	@NotBlank(message = "Name Should not be Empty!")
	@NotNull(message = "Name Should not be Empty!")
	private String empName;
	
	@NotBlank(message = "Designation Should not be Empty!")
	@NotNull(message = "Designation Should not be Empty!")
	private String empDesignation;
	
	@NotNull(message = "Salary Should not be Empty!")
	private Double empSalary;
	
	@NotBlank(message = "Department Should not be Empty!")
	@NotNull(message = "Department Should not be Empty!")
	private String empDepartment;
	
}
