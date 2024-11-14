package com.prnx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prnx.entity.Employee;
import com.prnx.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/allEmployees")
	public List<Employee> allEmployees()
	{
		return service.getEmployees();
	}
	
	
	@PostMapping("/save")
	public void save( @RequestBody Employee emp)
	{
		service.save(emp);
	}
	
	@GetMapping("/findEmployee/{id}")
	public Employee allEmployees(@PathVariable Long id )
	{
		return service.findEmployee(id);
	}
	
}
