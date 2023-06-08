package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class DataResDTO {

	private List<DataDTO> data;

	private DataResDTO (List<DataDTO> data){
		this.data = data;
	}

	public static DataResDTO from(List<DataDTO>data){
		return new DataResDTO(data);
	}

}
