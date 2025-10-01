package com.sm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
			StudentDTO savedStudent = stdservice.saveStudent(studentDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PostMapping("/batch")
	public ResponseEntity<?> createStudents(@Valid @RequestBody List<StudentDTO> students) {
		try {
			List<StudentDTO> savedStudents = stdservice.saveAllStudents(students);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedStudents);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
		try {
			StudentDTO student = stdservice.getStudentById(id);
			return ResponseEntity.ok(student);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents() {

		List<StudentDTO> students = stdservice.getListOfStudents();
		return ResponseEntity.ok(students);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable long id) {
		try {
			stdservice.deleteStudent(id);

			return ResponseEntity.ok().build();

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO){
		
		
		try {
			StudentDTO updatedStudent=stdservice.updateStudent(id,studentDTO);			
			return ResponseEntity.ok(updatedStudent);
			}catch(RuntimeException e) {
				
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
