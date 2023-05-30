package com.SoftwareEngineering.AcademicAdmin.dto.response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		this.semester = semester.getYear() + "학년도 " + semester.getSemester() + "학기";
		this.classes = subjectDTOS;
	}

	public static LectureDTO from(Semester semester, Set<SubjectDTO> subjectDTOS){
		return new LectureDTO(semester, subjectDTOS);
	}
}
