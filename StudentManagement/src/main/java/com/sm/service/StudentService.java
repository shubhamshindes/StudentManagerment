package com.sm.service;

import org.springframework.stereotype.Repository;

import com.sm.DTO.StudentDTO;
@Repository
public interface StudentService {

	StudentDTO saveStudent(StudentDTO studentDTO);

	
}
	
