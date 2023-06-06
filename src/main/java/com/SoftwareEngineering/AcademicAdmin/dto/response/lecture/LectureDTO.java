package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.util.HashSet;
import java.util.Set;

import com.SoftwareEngineering.AcademicAdmin.entity.Semester;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureDTO {

	private String semester;

	private Set<SubjectDTO> classes = new HashSet<>();

	private LectureDTO(Semester semester, Set<SubjectDTO> subjectDTOS){
		this.semester = semester.getName();
		this.classes = subjectDTOS;
	}

	public static LectureDTO of(Semester semester, Set<SubjectDTO> subjectDTOS){
		return new LectureDTO(semester, subjectDTOS);
	}
}
