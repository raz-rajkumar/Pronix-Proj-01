package com.prnx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prnx.entity.Employee;
import com.prnx.exceptionHandling.EmployeeErrorResponse;
import com.prnx.exceptionHandling.EmployeeNotFoundException;
import com.prnx.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/home")
	public String home(HttpServletRequest req)
	{
		return "WELCOME TO PRONIX IT SOLUTIONS"+"\n"+req.getSession().getId();
	}
	
	
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
		Optional<Employee> emp=service.findEmployee(id);
		if(emp.isPresent())
			return emp.get();
		else
			throw new EmployeeNotFoundException("Employee Not Found for id:"+id);
	}
	
	
	@PutMapping("updateEmployee/{id}")
	public Employee udateEmployee(@PathVariable Long id, @RequestBody Employee emp)
	{
		return service.update(id, emp);
	}
	
	
	@DeleteMapping("/deleteEmployee/{id}")
	public List<Employee> deleteEmployee(@PathVariable Long id)
	{
		return service.deleteEmployee(id);
	}
	
	
	
	
	
}
