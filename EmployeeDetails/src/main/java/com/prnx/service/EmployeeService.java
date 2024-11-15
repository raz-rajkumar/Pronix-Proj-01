package com.prnx.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<Employee> update(Long id,Employee emp)
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
			return repository.findAll();
		}
		return repository.findAll();
	}
	
	public List<Employee> deleteEmployee(Long id)
	{
		repository.deleteById(id);
		return repository.findAll();
	}
}
