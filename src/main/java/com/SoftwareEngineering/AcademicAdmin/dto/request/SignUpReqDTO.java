package com.SoftwareEngineering.AcademicAdmin.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpReqDTO {

	@NotNull(message = ExceptionMessage.STUDENT_ID_IS_EMPTY)
	private Long studentId;

	@NotBlank(message = ExceptionMessage.PASSWORD_IS_EMPTY)
	private String password;

	@NotBlank(message = ExceptionMessage.NAME_IS_EMPTY)
	private String name;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDate birth;

	@NotBlank(message = ExceptionMessage.ADDRESS_IS_EMPTY)
	private String address;

	private String phoneNumber;

	@NotBlank(message = ExceptionMessage.ROLE_IS_EMPTY)
	private String role;

	@NotBlank(message = ExceptionMessage.DEPARTMENT_IS_EMPTY)
	private String departmentName;


}
