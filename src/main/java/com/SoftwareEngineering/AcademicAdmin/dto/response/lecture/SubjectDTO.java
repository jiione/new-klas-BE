package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectDTO {
	private String className;
	private Long classId;

	private SubjectDTO(Subjects subjects){
		this.className = subjects.getName();
		this.classId = subjects.getId();
	}

	public static SubjectDTO from(Subjects subjects){
		return new SubjectDTO(subjects);
	}
}
