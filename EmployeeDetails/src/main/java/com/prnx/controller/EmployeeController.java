package com.prnx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<Employee> save( @RequestBody Employee emp)
	{
		service.save(emp);
		return service.getEmployees();
	}
	
	@GetMapping("/findEmployee/{id}")
	public Employee findEmployee(@PathVariable Long id )
	{
		return service.findEmployee(id);
	}
	
	
	@PutMapping("updateEmployee/{id}")
	public List<Employee> udateEmployee(@PathVariable Long id, @RequestBody Employee emp)
	{
		return service.update(id, emp);
	}
	
	
	@DeleteMapping("/deleteEmployee/{id}")
	public List<Employee> deleteEmployee(@PathVariable Long id)
	{
		return service.deleteEmployee(id);
	}
	
}
