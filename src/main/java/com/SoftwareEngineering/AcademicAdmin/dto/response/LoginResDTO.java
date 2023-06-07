package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDTO {
	Integer role;
	//0 학생
	//1 교수
	private LoginResDTO(Integer role){
		this.role = role;
	}

	public static LoginResDTO from(Integer role){
		return new LoginResDTO(role);
	}
}
