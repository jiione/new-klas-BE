package com.SoftwareEngineering.AcademicAdmin.dto.response.registration;

import java.util.ArrayList;
import java.util.List;

import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class SearchDTO {

	private Long classId;
	private String className;
	private String professor;
	private List<ScheduleDetailDTO> when;

	private SearchDTO(Subjects subjects, List<ScheduleDetailDTO> when){
		this.classId = subjects.getId();
		this.className = subjects.getName();
		this.professor = subjects.getProfessor();
		this.when = when;
	}

	public static SearchDTO of(Subjects subjects, List<ScheduleDetailDTO> when){
		return new SearchDTO(subjects, when);
	}
}
