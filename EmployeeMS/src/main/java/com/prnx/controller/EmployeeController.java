package com.prnx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.prnx.model.Employee;
import com.prnx.sevice.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
		model.addAttribute("Employee",new Employee());
		return "add_emp";
	}
	
	
	@GetMapping("/getAllEmployees")
	public String getAllEmployees(Model model)
	{
		model.addAttribute("employee",service.getEmployees());
		return "show";		
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee emp) {
		service.saveEmployee(emp);
				
		return "redirect:show";
	}
	
}
