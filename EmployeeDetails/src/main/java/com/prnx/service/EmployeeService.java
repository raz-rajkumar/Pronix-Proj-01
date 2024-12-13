package com.prnx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
//		if(emp.getEmpName()==null)
//			throw new NotEmptyException("Employee Name should not be empty.");
//		
//		if(emp.getEmpDepartment()==null)
//			throw new NotEmptyException("Employee Department should not be empty.");
//		
//		if(emp.getEmpDesignation()==null)
//			throw new NotEmptyException("Employee Designation should not be empty.");
//		
//		if(emp.getEmpSalary()==0.0)
//			throw new NotEmptyException("Employee Salary should not be empty.");
//		
		repository.save(emp);
	}
	
	public Optional<Employee> findEmployee(Long id)
	{
		return repository.findById(id);
	}
	
	public Employee update(Long id,Employee emp) throws Exception
	{
		Optional<Employee> byId = repository.findById(id);
		if(byId.isPresent())
		{
			Employee emp1=byId.get();
			if(emp.getEmpName()!=null)
				emp1.setEmpName(emp.getEmpName());
			if(emp.getEmpDepartment()!=null)
				emp1.setEmpDepartment(emp.getEmpDepartment());
			if(emp.getEmpDesignation()!=null)
				emp1.setEmpDesignation(emp.getEmpDesignation());
			if(emp.getEmpSalary()!=null)
				emp1.setEmpSalary(emp.getEmpSalary());
			if(emp.getEmpName()=="" ||emp.getEmpDepartment()==""|| emp.getEmpDesignation()==""||emp.getEmpSalary()==0)
			{
				throw new Exception("Field should not be empty!");
			}
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
