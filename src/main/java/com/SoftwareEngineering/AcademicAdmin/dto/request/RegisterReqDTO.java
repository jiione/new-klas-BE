package com.SoftwareEngineering.AcademicAdmin.dto.request;

import javax.validation.constraints.NotNull;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterReqDTO {

	@NotNull(message = ExceptionMessage.STUDENT_ID_IS_EMPTY)
	private Long studentId;

	@NotNull(message = ExceptionMessage.CLASS_ID_IS_EMPTY)
	private Long classId;

	private Long year;

	private Long semester;
}
