package com.SoftwareEngineering.AcademicAdmin.dto.response;

import java.util.List;

import lombok.Getter;

@Getter
public class AdminResDTO {
	List<UserDTO> users;

	private AdminResDTO(List<UserDTO> users){
		this.users = users;
	}

	public static AdminResDTO from(List<UserDTO> users){
		return new AdminResDTO(users);
	}
}
