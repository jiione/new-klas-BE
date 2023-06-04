package com.SoftwareEngineering.AcademicAdmin.dto.response.registration;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchResDTO {
	List<SearchDTO> classes = new ArrayList<>();

	private SearchResDTO (List<SearchDTO> classes){
		this.classes = classes;
	}

	public static SearchResDTO from(List<SearchDTO> classes){
		return new SearchResDTO(classes);
	}
}
