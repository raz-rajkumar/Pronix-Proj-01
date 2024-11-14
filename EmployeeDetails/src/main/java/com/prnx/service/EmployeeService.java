package com.prnx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prnx.entity.Employee;
import com.prnx.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> getEmployees()
	{
		return repository.findAll();
	}
	
	public void save(Employee emp)
	{
		repository.save(emp);
	}
	
	public Employee findEmployee(Long id)
	{
		return repository.findById(id).get();
	}
}
