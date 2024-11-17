package com.prnx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	
	
	@GetMapping("/getAllEmployees")
	public String getAllEmployees(Model model)
	{
		model.addAttribute("employee",service.getEmployees());
		return "show";		
	}
}
