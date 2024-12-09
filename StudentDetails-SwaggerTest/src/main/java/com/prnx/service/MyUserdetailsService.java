package com.prnx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prnx.entity.UserPrincipal;
import com.prnx.entity.Users;
import com.prnx.repository.UserRepository;
@Service
public class MyUserdetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findByUsername(username);
		if(user==null)
		{
			System.out.println("User not found!");
			throw new UsernameNotFoundException("User Not Found!");
		}
		return new UserPrincipal(user);
	}

}
