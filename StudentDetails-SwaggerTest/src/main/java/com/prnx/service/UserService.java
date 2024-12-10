package com.prnx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prnx.entity.Users;
import com.prnx.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthenticationManager AuthManager;
	
	
	@Autowired
	private JWTService jwt;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public Users register(Users user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}
	
	public String verify(Users user)
	{
		Authentication authentication=AuthManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated())
			return jwt.generateToken(user.getUsername());
		else
			return "Fail";
	}
}
