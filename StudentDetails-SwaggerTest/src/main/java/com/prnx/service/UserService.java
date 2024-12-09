package com.prnx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prnx.entity.Users;
import com.prnx.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repository;
	
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public Users register(Users user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}
}
