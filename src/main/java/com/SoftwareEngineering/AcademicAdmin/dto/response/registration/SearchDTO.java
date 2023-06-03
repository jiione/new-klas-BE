package com.SoftwareEngineering.AcademicAdmin.dto.response.registration;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchDTO {

	private Long classId;
	private String className;
	private String professor;
	private List<TimeDTO> when = new ArrayList<>();

	private SearchDTO(Long classId, String className, String professor, List<TimeDTO> when){
		this.classId = classId;
		this.className = className;
		this.professor = professor;
		this.when = when;
	}

	public static SearchDTO of(Long classId, String className, String professor, List<TimeDTO> when){
		return new SearchDTO(classId, className, professor, when);
	}
}
