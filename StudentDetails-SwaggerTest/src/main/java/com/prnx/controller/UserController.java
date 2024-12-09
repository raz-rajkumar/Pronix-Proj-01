package com.prnx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prnx.entity.Users;
import com.prnx.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired 
	private UserService service;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user)
	{
		
		return service.register(user);
	}
	
}
