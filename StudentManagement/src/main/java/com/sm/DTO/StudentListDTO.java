package com.sm.DTO;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class StudentListDTO {
	@NotEmpty(message = "Student List Cannot be emoty")
	private List<StudentDTO> students;

}
