package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDTO {
	Long userId;

	private LoginResDTO(Long id){
		this.userId = id;
	}

	public static LoginResDTO from(Long id){
		return new LoginResDTO(id);
	}
}
