package com.prnx.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.prnx.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private RestTemplate restTemplate;

	public List<Object> getEmployees() {
		String url = "http://localhost:8081/allEmployees";
		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url);

		List<Object> employees = restTemplate.getForObject(uri.toUriString(), List.class);

		return employees;
	}

	public List<Employee> saveEmployee(Employee emp) {
		String url = "http://localhost:8081/save";

		restTemplate.postForObject(url, emp, Employee.class);

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url);

		List<Employee> employees = restTemplate.getForObject(uri.toUriString(), List.class);
		return employees;
	}
}
