package com.SoftwareEngineering.AcademicAdmin.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqDTO {
	@NotNull(message = ExceptionMessage.STUDENT_ID_IS_EMPTY)
	private Long studentId;

	@NotBlank(message = ExceptionMessage.PASSWORD_IS_EMPTY)
	private String password;
}
