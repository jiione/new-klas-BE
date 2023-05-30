package com.SoftwareEngineering.AcademicAdmin.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureResDTO {
	private List<LectureDTO> semesters = new ArrayList<>();

	private LectureResDTO(List<LectureDTO> lectureDTOS){
		this.semesters = lectureDTOS;
	}

	public static LectureResDTO from(List<LectureDTO> lectureDTOS){
		return new LectureResDTO(lectureDTOS);
	}
}
