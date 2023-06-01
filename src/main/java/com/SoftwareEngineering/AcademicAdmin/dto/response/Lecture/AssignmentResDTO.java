package com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AssignmentResDTO {
	private List<AssignmentDTO> subjects = new ArrayList<>();

	private AssignmentResDTO (List<AssignmentDTO> assignmentDTOS){
		this.subjects = assignmentDTOS;
	}

	public static AssignmentResDTO from(List<AssignmentDTO>assignmentDTOS){
		return new AssignmentResDTO(assignmentDTOS);
	}
}
