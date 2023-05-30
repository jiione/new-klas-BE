package com.SoftwareEngineering.AcademicAdmin.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.SoftwareEngineering.AcademicAdmin.entity.Semester;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureDTO {

	private String semester;

	private List<SubjectDTO> classes = new ArrayList<>();

	private LectureDTO(Semester semester, List<SubjectDTO> subjectDTOS){
		this.semester = semester.getYear() + "학년도 " + semester.getSemester() + "학기";
		this.classes = subjectDTOS;
	}

	public static LectureDTO from(Semester semester, List<SubjectDTO> subjectDTOS){
		return new LectureDTO(semester, subjectDTOS);
	}
}
