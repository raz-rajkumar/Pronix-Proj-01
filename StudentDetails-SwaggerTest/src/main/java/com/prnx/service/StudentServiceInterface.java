package com.prnx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prnx.entity.Student;
@Service
public interface StudentServiceInterface {
	public Student saveStudentDetails(Student std);
	public List<Student> getStudents();
	public Student getStudentById(Long id);
	public List<Student> updateStudentDetails(Long id,Student std);
	public List<Student> deleteStudentDetails(Long id);
	
}
