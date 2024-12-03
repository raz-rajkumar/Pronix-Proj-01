package com.prnx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prnx.entity.Student;
import com.prnx.repository.StudentRepository;
@Service
public class StudentService implements StudentServiceInterface {
	
	@Autowired
	private StudentRepository repository;
	
	@Override
	public Student saveStudentDetails(Student std) {
		return repository.save(std);
	}

	@Override
	public List<Student> getStudents() {
		
		return repository.findAll();
	}

	@Override
	public Student getStudentById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Student> updateStudentDetails(Long id,Student std) {
		Optional<Student> student=repository.findById(id);
		if(student.isPresent())
		{
			Student s1=student.get();
			s1.setName(std.getName());
			s1.setStream(std.getStream());
			s1.setMail(std.getMail());
			repository.save(s1);
		}
		return repository.findAll();
		
	}

	@Override
	public List<Student> deleteStudentDetails(Long id) {
		repository.deleteById(id);
		return repository.findAll();
	}

}
