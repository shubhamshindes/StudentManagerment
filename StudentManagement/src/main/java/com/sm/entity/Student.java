package com.sm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name= "first_name",nullable=false)
	String fname;
	@Column(name= "last_name",nullable=false)
	String lname;
	@Column(name= "email",nullable=false)
	String email;
	@Column
	Integer age;
	@Column
	String department;
}
