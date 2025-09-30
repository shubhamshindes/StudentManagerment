package com.sm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.DTO.StudentDTO;
import com.sm.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService stdservice;

	@PostMapping
	public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
		try {
			StudentDTO savedStudent = stdservice.saveStudents(studentDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	

}
