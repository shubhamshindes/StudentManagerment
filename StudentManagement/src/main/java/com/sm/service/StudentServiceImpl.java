package com.sm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sm.DTO.StudentDTO;
import com.sm.entity.Student;
import com.sm.repository.StudentRepository;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public StudentDTO saveStudent(StudentDTO studentDTO) {

		if (studentRepository.existsByEmail(studentDTO.getEmail())) {

			throw new RuntimeException("Email already exist " + studentDTO.getEmail());

		}

		Student student = convertToEntity(studentDTO);// (JpaRepository) only works with Entities.
		Student savedStudent = studentRepository.save(student);// so in DB we save Student
		return convertToDTO(savedStudent);
	}

	@Override
	public List<StudentDTO> saveAllStudents(List<StudentDTO> students) {
		validateStudentBatch(students);//if dup email found throw exception below
		List<Student> stds = students.stream().map(this::convertToEntity).collect(Collectors.toList());
		List<Student>savedStds= studentRepository.saveAll(stds);
		
		
		return savedStds.stream().map(this::convertToDTO).collect(Collectors.toList());
		
	}
	 @Override
	 @Transactional(readOnly = true)
	   	public List<StudentDTO> getListOfStudents() {
	        return studentRepository.findAll().stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
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

	// Private helper methods
	private void validateStudentBatch(List<StudentDTO> studentDTOs) {
	        List<String> emails = studentDTOs.stream()
	                .map(StudentDTO::getEmail)
	                .collect(Collectors.toList());
	        
	        long uniqueEmails = emails.stream().distinct().count();
	        if (uniqueEmails != emails.size()) {
	            throw new RuntimeException("Duplicate emails found in the request");
	        }
	
	 
	}

	@Override
	public StudentDTO getStudentById(Long id) {
	    Student student = studentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));//findById(id) returns Optional<Student>, not Student.
	    //so either we make Student optional or handle by Exception
	    return convertToDTO(student);
	    
	    
	}
	
	@Override
	public void deleteStudent(Long id) {
		if(!studentRepository.existsById(id)) {
			
			throw new RuntimeException("Student not found by id "+id);
		}
		studentRepository.deleteById(id);
		
	}
	
	@Override
	public StudentDTO updateStudent(Long id,StudentDTO studentDTO) {
		Student existingStudent=studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student not found by id :"+ id));
		if(!existingStudent.getEmail().equals(studentDTO.getEmail())&&studentRepository.existsByEmail(studentDTO.getEmail())) {
			throw new RuntimeException("Email already exist :"+studentDTO.getEmail());
		}
		 existingStudent.setFname(studentDTO.getFirstName());
	        existingStudent.setLname(studentDTO.getLastName());
	        existingStudent.setEmail(studentDTO.getEmail());
	        existingStudent.setAge(studentDTO.getAge());
	        existingStudent.setDob(studentDTO.getDateOfBirth());
	        existingStudent.setDepartment(studentDTO.getDepartment());
	        Student updatedStudent = studentRepository.save(existingStudent);
	        return convertToDTO(updatedStudent);
	}

}
