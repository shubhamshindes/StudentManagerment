package com.sm.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sm.DTO.StudentDTO;
@Repository
public interface StudentService {

	StudentDTO saveStudent(StudentDTO studentDTO);

	List<StudentDTO> saveAllStudents(List<StudentDTO> students);

	
}
	
