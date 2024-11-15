package com.prnx.sevice;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmployeeService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List getEmployees()
	{
		String api="http://localhost:8081/allEmployees";
		UriComponentsBuilder uri=UriComponentsBuilder.fromHttpUrl(api);
		
		List employees=restTemplate.getForObject(uri.toUriString(), List.class);
		return employees;
	}
	
}
