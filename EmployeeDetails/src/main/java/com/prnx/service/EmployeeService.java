package com.prnx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prnx.entity.Employee;
import com.prnx.exceptionHandling.EmployeeNotFoundException;
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
	
	public Optional<Employee> findEmployee(Long id)
	{
		return repository.findById(id);
	}
	
	public Employee update(Long id,Employee emp)
	{
		Optional<Employee> byId = repository.findById(id);
		if(byId.isPresent())
		{
			Employee emp1=byId.get();
			emp1.setEmpName(emp.getEmpName());
			emp1.setEmpDepartment(emp.getEmpDepartment());
			emp1.setEmpDesignation(emp.getEmpDesignation());
			emp1.setEmpSalary(emp.getEmpSalary());
			repository.save(emp1);
			return repository.findById(id).get();
		}
		else
			throw new EmployeeNotFoundException("There is no Employee with id "+id+" to update details.");
	}
	
	public List<Employee> deleteEmployee(Long id)
	{
		Optional<Employee> emp=repository.findById(id);
		if(emp.isPresent())
		{
			repository.deleteById(id);
			return repository.findAll();
		}
		else
			throw new EmployeeNotFoundException("There is no Employee details with id "+id+" to delete.");
		
	}
}
