package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureResDTO {
	private Set<LectureDTO> semesters = new HashSet<>();

	private LectureResDTO(Set<LectureDTO> lectureDTOS){
		this.semesters = lectureDTOS;
	}

	public static LectureResDTO from(Set<LectureDTO> lectureDTOS){
		return new LectureResDTO(lectureDTOS);
	}
}
