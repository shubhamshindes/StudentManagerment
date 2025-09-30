package com.sm.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDTO {

	 private Long id;
	    
	    @NotBlank(message = "First name is required")
	    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	    private String firstName;
	    
	    @NotBlank(message = "Last name is required")
	    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	    private String lastName;
	    
	    @NotBlank(message = "Email is required")
	    @Email(message = "Email should be valid")
	    private String email;
	    
	    @Min(value = 16, message = "Age must be at least 16")
	    @Max(value = 100, message = "Age must be less than 100")
	    private Integer age;
	    
	    @Past(message = "Date of birth must be in the past")
	    private LocalDate dateOfBirth;
	    
	    @NotBlank(message = "Department is required")
	    private String department;

		public String getEmail() {
			
			return email;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	
	
}
