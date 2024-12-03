package com.prnx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prnx.entity.Student;
import com.prnx.service.StudentService;


@RestController
@RequestMapping("/std")
public class StudentRestController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/getAll")
	public List<Student> getAllStudents()
	{
		return service.getStudents();
	}
	
	@PostMapping("/save")
	public Student saveStudent(@RequestBody Student std)
	{
		return service.saveStudentDetails(std);
	}
	
	@GetMapping("/getById/{id}")
	public Student getStudentById(@PathVariable Long id)
	{
		return service.getStudentById(id);
	}
	
	@PutMapping("/update/{id}")
	public List<Student> updateStudentDetails(@PathVariable Long id, @RequestBody Student std) {
		return service.updateStudentDetails(id, std);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public List<Student> deleteStudentDetails(@PathVariable Long id)
	{
		service.deleteStudentDetails(id);
		return service.getStudents();
	}
}
