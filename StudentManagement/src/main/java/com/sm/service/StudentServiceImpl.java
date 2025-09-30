package com.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.DTO.StudentDTO;
import com.sm.entity.Student;
import com.sm.repository.StudentRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public StudentDTO saveStudent(StudentDTO studentDTO) {
		
		if(studentRepository.existsByEmail(studentDTO.getEmail())) {
			
			throw new RuntimeException("Email already exist "+studentDTO.getEmail());
			
		}
		
		Student student = convertToEntity(studentDTO);
		Student savedStudent = studentRepository.save(student);
		return convertToDTO(savedStudent);
	}
	
	 private StudentDTO convertToDTO(Student student) {
	        StudentDTO dto = new StudentDTO();
	        dto.setId(student.getId());
	        dto.setFirstName(student.getFname());
	        dto.setLastName(student.getLname());
	        dto.setEmail(student.getEmail());
	        dto.setAge(student.getAge());
	        dto.setDateOfBirth(student.getDob());
	        dto.setDepartment(student.getDepartment());
	        return dto;
	    }
	 private Student convertToEntity(StudentDTO dto) {
	        Student student = new Student();
	        student.setId(dto.getId());
	        student.setFname(dto.getFirstName());
	        student.setLname(dto.getLastName());
	        student.setEmail(dto.getEmail());
	        student.setAge(dto.getAge());
	        student.setDob(dto.getDateOfBirth());
	        student.setDepartment(dto.getDepartment());
	        return student;
	    }
	 
	
}
