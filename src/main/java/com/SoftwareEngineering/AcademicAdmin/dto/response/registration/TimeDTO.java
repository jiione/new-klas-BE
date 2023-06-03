package com.SoftwareEngineering.AcademicAdmin.dto.response.registration;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TimeDTO {
	private String day;
	private List<Long> time = new ArrayList<>();

	private TimeDTO(String day, List<Long> time){
		this.day = day;
		this.time = time;
	}
	public static TimeDTO of(String day, List<Long> time){
		return new TimeDTO(day, time);
	}
}
