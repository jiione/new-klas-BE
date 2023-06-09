package com.SoftwareEngineering.AcademicAdmin.dto.response;

import com.SoftwareEngineering.AcademicAdmin.entity.User;

import lombok.Getter;

@Getter
public class UserDTO {
	private String department;
	private String role;
	private String name;
	private Long studentId;

	private UserDTO(User user){
		this.department = user.getDepartment().getName();
		this.role = user.getRole();
		this.name = user.getName();
		this.studentId = user.getStudentId();
	}

	public static UserDTO from(User user){
		return new UserDTO(user);
	}
}
