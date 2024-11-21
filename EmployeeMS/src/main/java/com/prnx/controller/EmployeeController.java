package com.prnx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.prnx.model.Employee;
import com.prnx.sevice.EmployeeService;


@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/")
	public String Home()
	{
		return "home";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(Model model)
	{
		model.addAttribute("employee",new Employee());
		return "add_emp";
	}
	
	
	@GetMapping("/getAllEmployees")
	public String getAllEmployees(Model model)
	{
		model.addAttribute("employee",service.getEmployees());
		return "show";		
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee emp, Model model) {
		List<Employee> emps= service.saveEmployee(emp);
		model.addAttribute("employee",emps);
		return "show";
	}
	
}
