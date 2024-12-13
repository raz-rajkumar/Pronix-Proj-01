package com.prnx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prnx.entity.Role;
import com.prnx.entity.Users;
import com.prnx.repository.RoleRepository;
import com.prnx.repository.UserRepository;

@Service
public class UserService implements IUserService{
	
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthenticationManager AuthManager;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private JWTService jwt;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public Users register(Users user)
	{
		Role role = roleRepository.findByRoleName("User");
		if(role==null)
		{
			role= new Role("User");
			roleRepository.save(role);
		}
		user.setRole(role);
		user.setPassword(encoder.encode(user.getPassword()));
		 Users users = repository.save(user);
		 return users;
		
	}
	
	public String verify(Users user)
	{
		Authentication authentication=AuthManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated())
			return jwt.generateToken(user.getUsername());
		else
			return "Fail";
	}
	
	@Override
	public String initRoleAndAdmin() {
 
		if (roleRepository.findByRoleName("User") == null) {
			Role userRole = new Role();
			userRole.setRoleName("User");
			roleRepository.save(userRole);
		}
		if (roleRepository.findByRoleName("Admin") == null) {
			Role userRole = new Role();
			userRole.setRoleName("Admin");
			roleRepository.save(userRole);
		}
 
		return "Success";
	}
}
