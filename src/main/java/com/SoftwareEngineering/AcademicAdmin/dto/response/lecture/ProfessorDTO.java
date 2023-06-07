package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import lombok.Getter;

@Getter
public class ProfessorDTO {
	private String professor;

	private ProfessorDTO(String professor){
		this.professor = professor;
	}

	public static ProfessorDTO from(String professor){
		return new ProfessorDTO(professor);
	}
}
