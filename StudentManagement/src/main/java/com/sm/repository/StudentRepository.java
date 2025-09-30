package com.sm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	boolean existsByEmail(String email);

}
